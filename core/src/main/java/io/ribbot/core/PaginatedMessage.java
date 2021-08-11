package io.ribbot.core;

import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import discord4j.core.event.domain.interaction.ButtonInteractEvent;
import discord4j.core.object.Embed;
import discord4j.core.object.Embed.Field;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.MessageEditSpec;
import discord4j.discordjson.json.EmbedData;
import discord4j.discordjson.possible.Possible;
import reactor.core.publisher.Mono;

public class PaginatedMessage {
    private final List<Page> pages;
    private final Message message;

    public static Builder from(Message message) {
        return new Builder(message);
    }

    private PaginatedMessage(List<Page> pages, Message message) {
        this.pages = Collections.unmodifiableList(pages);
        this.message = message;
    }

    private Mono<Map<String, PageTurnDirection>> addButtons() {
        String firstButtonId = "f";
        String previousButtonId = "p";
        String cancelButtonId = "c";
        String nextButtonId = "n";
        String lastButtonId = "l";

        return message.edit(messageSpec -> messageSpec.setComponents(
                ActionRow.of(
                        Button.primary(firstButtonId, "First"),
                        Button.primary(previousButtonId, "Previous"),
                        Button.danger(cancelButtonId, "Cancel"),
                        Button.primary(nextButtonId, "Next"),
                        Button.primary(lastButtonId, "Last"))))
                .thenReturn(Map.of(
                        firstButtonId, PageTurnDirection.FIRST,
                        previousButtonId, PageTurnDirection.PREVIOUS,
                        cancelButtonId, PageTurnDirection.CLOSE,
                        nextButtonId, PageTurnDirection.NEXT,
                        lastButtonId, PageTurnDirection.LAST));
    }

    private Page getCurrentPage() {
        Embed embed = message.getEmbeds().get(0);
        return Page.from(embed);
    }

    private Page getNextPage() {
        for (int i = 0; i < pages.size(); i++) {
            Page page = pages.get(i);
            if (page.equals(getCurrentPage())) {
                return pages.get(i == pages.size() - 1 ? i : i + 1);
            }
        }
        throw new IllegalStateException();
    }

    private Page getPreviousPage() {
        for (int i = 0; i < pages.size(); i++) {
            Page page = pages.get(i);
            if (page.equals(getCurrentPage())) {
                return pages.get(i == 0 ? i : i - 1);
            }
        }
        throw new IllegalStateException();
    }

    private Mono<Void> paginate() {
        return addButtons().delayUntil(buttonMap -> {
            Mono<Void> onButtonInteract = message.getClient().on(ButtonInteractEvent.class)
                    .filter(buttonInteract -> buttonInteract.getMessage().equals(message))
                    .map(ButtonInteractEvent::getCustomId)
                    .map(buttonMap::get)
                    .flatMap(pageTurnDirection -> {
                        switch (pageTurnDirection) {
                            case FIRST:
                                return message.edit(messageSpec -> pages.get(0).accept(messageSpec));
                            case PREVIOUS:
                                return message.edit(messageSpec -> getPreviousPage().accept(messageSpec));
                            case CLOSE:
                                return message.delete();
                            case NEXT:
                                return message.edit(messageSpec -> getNextPage().accept(messageSpec));
                            case LAST:
                                return message.edit(messageSpec -> pages.get(pages.size() - 1).accept(messageSpec));
                            default:
                                return Mono.error(IllegalStateException::new);
                        }
                    })
                    .then();
            Mono<Void> timeout = Mono.delay(Duration.ofMinutes(3)).then();
            return Mono.first(onButtonInteract, timeout);
        }).then();
    }

    public abstract static class Page implements Consumer<MessageEditSpec> {
        public static Page from(Embed embed) {
            return new Page() {
                @Override
                public void accept(MessageEditSpec messageSpec) {
                    messageSpec.addEmbed(embedSpec -> {
                        embed.getColor().ifPresent(embedSpec::setColor);
                        embed.getDescription().ifPresent(embedSpec::setDescription);
                        embed.getTitle().ifPresent(embedSpec::setTitle);

                        for (Field field : embed.getFields()) {
                            embedSpec.addField(field.getName(), field.getValue(), field.isInline());
                        }
                    });
                }
            };
        }

        private List<Map<String, String>> getEmbedFields(MessageEditSpec messageSpec) {
            return messageSpec.asRequest().embeds().toOptional()
                    .flatMap(Function.identity())
                    .map(List::iterator)
                    .map(Iterator::next)
                    .map(EmbedData::fields)
                    .flatMap(Possible::toOptional)
                    .get()
                    .stream()
                    .map(field -> Collections.singletonMap(field.name(), field.value()))
                    .collect(Collectors.toList());
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Page)) {
                return false;
            }
            Page that = (Page) o;

            MessageEditSpec messageSpec1 = new MessageEditSpec();
            MessageEditSpec messageSpec2 = new MessageEditSpec();
            this.accept(messageSpec1);
            that.accept(messageSpec2);
            // TODO
            return Objects.equals(getEmbedFields(messageSpec1), getEmbedFields(messageSpec2));
        }

        @Override
        public int hashCode() {
            MessageEditSpec messageSpec = new MessageEditSpec();
            accept(messageSpec);
            return getEmbedFields(messageSpec).hashCode();
        }
    }

    private enum PageTurnDirection {
        FIRST,
        PREVIOUS,
        CLOSE,
        NEXT,
        LAST
    }

    public static final class Builder {
        private static final Function<Page, Page> embedRemover = page -> new Page() {
            @Override
            public void accept(MessageEditSpec messageSpec) {
                messageSpec.removeEmbeds();
                page.accept(messageSpec);
            }
        };
        private final List<Page> pages = new ArrayList<>();
        private final Message message;

        private Builder(Message message) {
            this.message = message;
        }

        public Builder addPage(Page page) {
            pages.add(embedRemover.apply(page));
            return this;
        }

        public Mono<Void> paginate() {
            return new PaginatedMessage(pages, message).paginate();
        }
    }
}
