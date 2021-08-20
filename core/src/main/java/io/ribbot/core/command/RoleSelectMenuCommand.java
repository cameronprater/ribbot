package io.ribbot.core.command;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.jdbi.v3.core.Jdbi;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.SelectMenuInteractEvent;
import discord4j.core.event.domain.interaction.SlashCommandEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.component.SelectMenu;
import discord4j.core.object.component.SelectMenu.Option;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.discordjson.json.WebhookMessageEditRequest;
import discord4j.rest.http.client.ClientException;
import discord4j.rest.interaction.InteractionResponse;
import io.quarkiverse.discord4j.GatewayEvent;
import io.ribbot.core.MessageComponentHelper;
import io.ribbot.core.MessageLink;
import io.ribbot.core.jdbi.RoleSelectOptionDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.Nullable;

public class RoleSelectMenuCommand {
    private static final Logger LOGGER = Logger.getLogger(RoleSelectMenuCommand.class);
    private final Jdbi jdbi;
    private final MessageComponentHelper messageComponentHelper;

    RoleSelectMenuCommand(Jdbi jdbi, MessageComponentHelper messageComponentHelper) {
        this.jdbi = jdbi;
        this.messageComponentHelper = messageComponentHelper;
    }

    private Mono<Message> onAdd(ApplicationCommandInteractionOption subcommand, Snowflake roleId, String label,
            Mono<Message> messageMono, @Nullable String description, @Nullable String emoji) {
        Optional<Integer> index = subcommand.getOption("index")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .map(Math::toIntExact);
        Optional<Integer> min = subcommand.getOption("min")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .map(Math::toIntExact);
        Optional<Integer> max = subcommand.getOption("max")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .map(Math::toIntExact);

        return messageMono.delayUntil(message -> message.getGuild()
                .map(Guild::getId)
                .flatMap(guildId -> Mono.fromRunnable(() -> jdbi.useExtension(RoleSelectOptionDao.class,
                        roleSelectOption -> roleSelectOption.insert(
                                guildId, message.getChannelId(), message.getId(),
                                messageComponentHelper.getSelectMenuId(message, index.orElse(0)), roleId)))))
                .flatMap(message -> message.edit(messageSpec -> {
                    Option option = messageComponentHelper.getOption(label, roleId.asString(), description, emoji);
                    messageSpec.setComponents(messageComponentHelper.addOption(message, option, index.orElse(0),
                            min.orElse(null), max.orElse(null)));
                }));
    }

    private Mono<Message> onNew(ApplicationCommandInteractionOption subcommand, Snowflake roleId, String label,
            Mono<Message> messageMono, @Nullable String description, @Nullable String emoji) {
        Optional<String> placeholder = subcommand.getOption("placeholder")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString);

        return messageMono.zipWith(Mono.fromCallable(() -> messageComponentHelper.getSelectMenu(
                label, roleId.asString(), description, emoji, placeholder.orElse(null))))
                .flatMap(tuple -> {
                    Message message = tuple.getT1();
                    SelectMenu selectMenu = tuple.getT2();

                    return message.getGuild().map(Guild::getId)
                            .flatMap(guildId -> Mono.fromRunnable(() -> jdbi.useExtension(RoleSelectOptionDao.class,
                                    roleSelectOption -> roleSelectOption.insert(
                                            guildId, message.getChannelId(),
                                            message.getId(), selectMenu.getCustomId(), roleId)))
                                    .then(message.edit(messageSpec -> messageSpec
                                            .setComponents(messageComponentHelper.addComponent(message, selectMenu)))));
                });
    }

    public Mono<Void> onSlashCommand(@GatewayEvent SlashCommandEvent slashCommand) {
        if (!slashCommand.getCommandName().equals("rsm")) {
            return Mono.empty();
        }

        ApplicationCommandInteractionOption subcommand = slashCommand.getOptions().get(0);
        InteractionResponse response = slashCommand.getInteractionResponse();

        Snowflake roleId = subcommand.getOption("role")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asSnowflake)
                .get();
        String label = subcommand.getOption("label")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();
        Optional<String> messageOption = subcommand.getOption("msg")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString);
        GatewayDiscordClient gateway = subcommand.getClient();
        Mono<Message> messageMono = messageOption.map(msg -> Mono.fromCallable(() -> MessageLink.from(msg))
                .flatMap(messageLink -> gateway.getGuildById(messageLink.getGuildId())
                        .flatMap(guild -> guild.getChannelById(messageLink.getChannelId()))
                        .ofType(MessageChannel.class)
                        .flatMap(channel -> channel.getMessageById(messageLink.getMessageId())))
                .onErrorMap(ClientException.isStatusCode(404, 403),
                        e -> new IllegalArgumentException(String.format("%s doesn't exist or is inaccessible", msg)))
                .filter(message -> message.getAuthor().map(User::getId).map(gateway.getSelfId()::equals).orElse(false))
                .switchIfEmpty(Mono.error(
                        new IllegalArgumentException(String.format("%s isn't a Ribbot message", msg)))))
                .get();
        Optional<String> description = subcommand.getOption("desc")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString);
        Optional<String> emoji = subcommand.getOption("emoji")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString);

        return slashCommand.acknowledgeEphemeral().then(Mono.defer(() -> {
            switch (subcommand.getName()) {
                case "add":
                    return onAdd(subcommand, roleId, label, messageMono, description.orElse(null), emoji.orElse(null))
                            .then(response.editInitialResponse(WebhookMessageEditRequest.builder()
                                    .content(String.format("Option added to role select menu at %s", messageOption.get()))
                                    .build()));
                case "new":
                    return onNew(subcommand, roleId, label, messageMono, description.orElse(null), emoji.orElse(null))
                            .then(response.editInitialResponse(WebhookMessageEditRequest.builder()
                                    .content(String.format("Role select menu created at %s", messageOption.get()))
                                    .build()));
                default:
                    return Mono.error(IllegalStateException::new);
            }
        })).onErrorResume(IllegalArgumentException.class,
                e -> response.editInitialResponse(WebhookMessageEditRequest.builder().content(e.getMessage()).build()))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }

    public Mono<Void> onSelectMenuInteract(@GatewayEvent SelectMenuInteractEvent selectMenuInteract) {
        Member member = selectMenuInteract.getInteraction().getMember().get();
        InteractionResponse response = selectMenuInteract.getInteractionResponse();

        return selectMenuInteract.acknowledgeEphemeral().thenReturn(selectMenuInteract.getValues())
                .flatMapMany(values -> Mono.fromCallable(() -> jdbi.withExtension(RoleSelectOptionDao.class,
                        roleSelectOption -> roleSelectOption.getBySelectMenuId(selectMenuInteract.getCustomId())))
                        .flatMapMany(Flux::fromIterable)
                        .flatMap(roleId -> {
                            if (values.contains(roleId.asString()) && !member.getRoleIds().contains(roleId)) {
                                return member.addRole(roleId);
                            } else if (!values.contains(roleId.asString()) && member.getRoleIds().contains(roleId)) {
                                return member.removeRole(roleId);
                            } else {
                                return Mono.empty();
                            }
                        }))
                .then(response.createFollowupMessageEphemeral("Roles updated"))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }
}
