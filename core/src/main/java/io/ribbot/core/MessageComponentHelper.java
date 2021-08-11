package io.ribbot.core;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;

import com.cameronprater.emoji.EmojiManager;
import com.cameronprater.emoji.EmojiParser;

import discord4j.common.util.Snowflake;
import discord4j.core.object.component.*;
import discord4j.core.object.component.MessageComponent.Type;
import discord4j.core.object.component.SelectMenu.Option;
import discord4j.core.object.entity.Message;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.discordjson.json.ComponentData;
import discord4j.discordjson.json.ImmutableComponentData;
import discord4j.discordjson.json.SelectOptionData;
import discord4j.discordjson.possible.Possible;
import reactor.util.annotation.Nullable;

// TODO some input validation here could be abstracted out into hibernate validator.
@Dependent
public class MessageComponentHelper {
    private static final int MAX_ACTION_ROWS = 5;
    private static final int MAX_BUTTONS = 5;

    private SelectOptionData getData(Option option) {
        return SelectOptionData.builder()
                .description(option.getDescription().map(Possible::of).orElse(Possible.absent()))
                .emoji(option.getEmoji().map(ReactionEmoji::asEmojiData).map(Possible::of).orElse(Possible.absent()))
                .isDefault(option.isDefault())
                .label(option.getLabel())
                .value(option.getValue())
                .build();
    }

    // https://github.com/Discord4J/Discord4J/blob/master/core/src/main/java/discord4j/core/object/entity/Message.java#L494
    private List<LayoutComponent> getLayoutComponents(List<MessageComponent> components) {
        return components.stream()
                .filter(component -> component instanceof LayoutComponent)
                .map(component -> (LayoutComponent) component)
                .collect(Collectors.toList());
    }

    private ReactionEmoji getReactionEmoji(String s) {
        if (EmojiManager.isEmoji(s)) {
            return ReactionEmoji.unicode(s);
        } else {
            CustomEmoji customEmoji = CustomEmoji.from(s);
            return ReactionEmoji.custom(customEmoji.getId(), customEmoji.getName(), customEmoji.isAnimated());
        }
    }

    private String requireNoEmojis(String s) {
        List<String> emojis = EmojiParser.extractEmojis(s);
        Matcher matcher = Pattern.compile(CustomEmoji.REGEX).matcher(s);
        if (!emojis.isEmpty()) {
            throw new IllegalArgumentException(String.format("Text can't contain emojis: %s", String.join(", ", emojis)));
        } else if (matcher.find()) {
            throw new IllegalArgumentException(String.format("Text can't contain emojis: %s", matcher.group()));
        } else {
            return s;
        }
    }

    // TODO check each row for non-full when the last row is full and more can't be created
    public List<LayoutComponent> addComponent(Message message, MessageComponent component) {
        List<LayoutComponent> components = getLayoutComponents(message.getComponents());
        switch (component.getType()) {
            case BUTTON:
                if (components.isEmpty()) {
                    components.add(ActionRow.of((Button) component));
                } else {
                    int i = components.size() - 1;
                    LayoutComponent lastComponent = components.get(i);
                    List<MessageComponent> childComponents = lastComponent.getChildren();

                    if (childComponents.size() < MAX_BUTTONS) {
                        ComponentData data = ComponentData.builder()
                                .from(lastComponent.getData())
                                .addComponent(component.getData())
                                .build();
                        components.set(i, (LayoutComponent) LayoutComponent.fromData(data));
                    } else if (i < MAX_ACTION_ROWS) {
                        components.add(ActionRow.of((Button) component));
                    } else {
                        throw new IllegalArgumentException("Message can't fit any more buttons");
                    }
                }
                return components;
            case SELECT_MENU:
                if (components.size() < MAX_ACTION_ROWS) {
                    components.add(ActionRow.of((SelectMenu) component));
                } else {
                    throw new IllegalArgumentException("Message can't fit any more select menus");
                }
                return components;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public List<LayoutComponent> addOption(Message message, Option option, int index, @Nullable Integer min,
            @Nullable Integer max) {
        if (min != null && min < 0) {
            throw new IllegalArgumentException("Min must be greater than 0");
        }
        if (max != null && max < 0) {
            throw new IllegalArgumentException("Max must be greater than 0");
        }

        List<LayoutComponent> components = getLayoutComponents(message.getComponents());
        for (int i = 0, j = 0; i < components.size(); i++) {
            LayoutComponent component = components.get(i);
            MessageComponent childComponent = component.getChildren().get(0);
            if (j == index && childComponent.getType() == Type.SELECT_MENU) {
                ComponentData childComponentData = childComponent.getData();
                if (min != null && childComponentData.options().toOptional().map(List::size).get() + 1 < min) {
                    throw new IllegalArgumentException("Min must not exceed the number of options present");
                }
                if (max != null && childComponentData.options().toOptional().map(List::size).get() + 1 < max) {
                    throw new IllegalArgumentException("Max must not exceed the number of options present");
                }

                ComponentData data = component.getData();
                List<ComponentData> childComponents = data.components().get();
                childComponents.set(0, ComponentData.builder()
                        .from(childComponentData)
                        .addOption(getData(option))
                        .minValues(min != null ? Possible.of(min) : Possible.absent())
                        .maxValues(max != null ? Possible.of(max) : Possible.absent())
                        .build());

                ImmutableComponentData.Builder builder = ComponentData.builder()
                        .from(data)
                        .components(childComponents);

                components.set(i, (LayoutComponent) LayoutComponent.fromData(builder.build()));
                return components;
            } else if (childComponent.getType() == Type.SELECT_MENU) {
                j++;
            }
        }
        throw new IllegalArgumentException(
                String.format("Message doesn't have %s select menu%s", index == 0 ? "a" : index + 1, index == 0 ? "" : "s"));
    }

    public Button getButton(String color, @Nullable String label, @Nullable String emoji) {
        if (label == null && emoji == null) {
            throw new IllegalArgumentException("Label or emoji must be provided");
        }

        ImmutableComponentData.Builder builder = ComponentData.builder()
                .customId(Snowflake.of(Instant.now()).asString())
                .type(MessageComponent.Type.BUTTON.getValue());

        if (label != null) {
            builder.label(requireNoEmojis(label));
        }
        if (emoji != null) {
            builder.emoji(getReactionEmoji(emoji).asEmojiData());
        }

        switch (color) {
            case "blurple":
                return (Button) Button.fromData(builder.style(Button.Style.PRIMARY.getValue()).build());
            case "green":
                return (Button) Button.fromData(builder.style(Button.Style.SUCCESS.getValue()).build());
            case "grey":
                return (Button) Button.fromData(builder.style(Button.Style.SECONDARY.getValue()).build());
            case "red":
                return (Button) Button.fromData(builder.style(Button.Style.DANGER.getValue()).build());
            default:
                throw new IllegalStateException();
        }
    }

    public String getSelectMenuId(Message message, int index) {
        List<LayoutComponent> components = getLayoutComponents(message.getComponents());
        for (int i = 0, j = 0; i < components.size(); i++) {
            LayoutComponent component = components.get(i);
            MessageComponent childComponent = component.getChildren().get(0);
            if (j == index && childComponent.getType() == Type.SELECT_MENU) {
                return childComponent.getData().customId().get();
            } else if (childComponent.getType() == Type.SELECT_MENU) {
                j++;
            }
        }
        throw new IllegalArgumentException(
                String.format("Message doesn't have %s select menu%s", index == 0 ? "a" : index + 1, index == 0 ? "" : "s"));
    }

    public Option getOption(String label, String value, @Nullable String description, @Nullable String emoji) {
        Option option = Option.of(requireNoEmojis(label), value);
        if (description != null) {
            option = option.withDescription(requireNoEmojis(description));
        }
        if (emoji != null) {
            option = option.withEmoji(getReactionEmoji(emoji));
        }
        return option;
    }

    public SelectMenu getSelectMenu(String label, String value, @Nullable String description,
            @Nullable String emoji, @Nullable String placeholder) {
        SelectMenu selectMenu = SelectMenu.of(Snowflake.of(Instant.now()).asString(),
                getOption(label, value, description, emoji));
        if (placeholder != null) {
            selectMenu = selectMenu.withPlaceholder(placeholder);
        }
        return selectMenu;
    }

    public List<LayoutComponent> removeComponent(Message message, String customId) {
        List<LayoutComponent> components = getLayoutComponents(message.getComponents());
        for (int i = 0; i < components.size(); i++) {
            LayoutComponent component = components.get(i);
            List<MessageComponent> children = component.getChildren();

            for (int j = 0; j < children.size(); j++) {
                ComponentData childComponent = children.get(j).getData();
                if (childComponent.customId().toOptional().map(customId::equals).orElse(false)) {
                    ComponentData data = component.getData();
                    List<ComponentData> childComponents = data.components().get();
                    childComponents.remove(j);

                    if (childComponents.isEmpty()) {
                        components.remove(i);
                    } else {
                        ImmutableComponentData.Builder builder = ComponentData.builder()
                                .from(data)
                                .components(childComponents);
                        components.set(i, (LayoutComponent) LayoutComponent.fromData(builder.build()));
                    }
                    return components;
                }
            }
        }
        throw new IllegalStateException(String.format("Message doesn't have a component with an id of %s", customId));
    }

    public List<LayoutComponent> removeOption(Message message, String optionValue) {
        List<LayoutComponent> components = getLayoutComponents(message.getComponents());
        for (int i = 0; i < components.size(); i++) {
            LayoutComponent component = components.get(i);
            MessageComponent childComponent = component.getChildren().get(0);
            if (childComponent.getType() == Type.SELECT_MENU) {
                ComponentData childComponentData = childComponent.getData();
                List<SelectOptionData> options = childComponentData.options().get();

                for (int j = 0; j < options.size(); j++) {
                    if (options.get(j).value().equals(optionValue)) {
                        List<SelectOptionData> newOptions = new ArrayList<>(options);
                        newOptions.remove(j);

                        if (newOptions.isEmpty()) {
                            // TODO also need to delete the empty menu from the db
                            components.remove(i);
                        } else {
                            // adjust min and max if needed
                            Possible<Integer> minValues = childComponentData.minValues().toOptional()
                                    .filter(min -> min > options.size() - 1)
                                    .map(min -> min - 1)
                                    .map(Possible::of)
                                    .orElse(Possible.absent());
                            Possible<Integer> maxValues = childComponentData.maxValues().toOptional()
                                    .filter(max -> max > options.size() - 1)
                                    .map(max -> max - 1)
                                    .map(Possible::of)
                                    .orElse(Possible.absent());

                            ComponentData data = component.getData();
                            List<ComponentData> childComponents = data.components().get();
                            childComponents.set(0, ComponentData.builder()
                                    .from(childComponentData)
                                    .options(newOptions)
                                    .minValues(minValues)
                                    .maxValues(maxValues)
                                    .build());
                            ImmutableComponentData.Builder builder = ComponentData.builder()
                                    .from(data)
                                    .components(childComponents);

                            components.set(i, (LayoutComponent) LayoutComponent.fromData(builder.build()));
                        }
                        return components;
                    }
                }
            }
        }
        throw new IllegalStateException(
                String.format("Message doesn't have a select menu with an option whose value is %s", optionValue));
    }
}
