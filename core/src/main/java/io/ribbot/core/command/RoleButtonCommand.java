package io.ribbot.core.command;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.jboss.logging.Logger;
import org.jdbi.v3.core.Jdbi;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ButtonInteractEvent;
import discord4j.core.event.domain.interaction.SlashCommandEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.component.Button;
import discord4j.core.object.entity.*;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.discordjson.json.WebhookMessageEditRequest;
import discord4j.rest.http.client.ClientException;
import discord4j.rest.interaction.InteractionResponse;
import io.quarkiverse.discord4j.GatewayEvent;
import io.ribbot.core.MessageComponentHelper;
import io.ribbot.core.MessageLink;
import io.ribbot.core.jdbi.RoleButtonDao;
import reactor.core.publisher.Mono;

public class RoleButtonCommand {
    private static final Logger LOGGER = Logger.getLogger(RoleButtonCommand.class);

    @Inject
    Jdbi jdbi;

    @Inject
    MessageComponentHelper messageComponentHelper;

    private Mono<Void> onAdd(ApplicationCommandInteractionOption subcommand) {
        GatewayDiscordClient gateway = subcommand.getClient();

        Mono<Role> roleMono = subcommand.getOption("role")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asRole)
                .get();
        Mono<Message> messageMono = subcommand.getOption("msg")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .map(msg -> Mono.fromCallable(() -> MessageLink.from(msg))
                        .flatMap(messageLink -> gateway.getGuildById(messageLink.getGuildId())
                                .flatMap(guild -> guild.getChannelById(messageLink.getChannelId()))
                                .ofType(MessageChannel.class)
                                .flatMap(channel -> channel.getMessageById(messageLink.getMessageId())))
                        .onErrorMap(ClientException.isStatusCode(404, 403),
                                e -> new IllegalArgumentException(String.format("%s doesn't exist or is inaccessible", msg)))
                        .filter(message -> message.getAuthor().map(User::getId).map(gateway.getSelfId()::equals).orElse(false))
                        .switchIfEmpty(
                                Mono.error(new IllegalArgumentException(String.format("%s isn't a Ribbot message", msg)))))
                .get();
        String colorOption = subcommand.getOption("color")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse("blurple");
        String label = subcommand.getOption("label")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse(null);
        String emoji = subcommand.getOption("emoji")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse(null);

        return messageMono
                .zipWith(Mono.fromCallable(() -> messageComponentHelper.getButton(colorOption, label, emoji)))
                .delayUntil(tuple -> {
                    Message message = tuple.getT1();

                    return message.getGuild().map(Guild::getId)
                            .zipWith(roleMono.map(Role::getId))
                            .flatMap(t2 -> Mono.fromRunnable(() -> jdbi.useExtension(RoleButtonDao.class,
                                    roleButton -> roleButton.insert(
                                            t2.getT1(), message.getChannelId(), message.getId(),
                                            t2.getT2(), tuple.getT2().getCustomId().get()))));
                })
                .flatMap(tuple -> Mono.defer(() -> {
                    Message message = tuple.getT1();
                    Button button = tuple.getT2();
                    return message.edit(
                            messageSpec -> messageSpec.setComponents(messageComponentHelper.addComponent(message, button)));
                }))
                .then();
    }

    public Mono<Void> onSlashCommand(@GatewayEvent SlashCommandEvent slashCommand) {
        if (!slashCommand.getCommandName().equals("rb")) {
            return Mono.empty();
        }

        ApplicationCommandInteractionOption subcommand = slashCommand.getOptions().get(0);
        InteractionResponse response = slashCommand.getInteractionResponse();

        return slashCommand.acknowledgeEphemeral().then(Mono.defer(() -> {
            if (subcommand.getName().equals("add")) {
                return onAdd(subcommand);
            } else {
                return Mono.error(IllegalStateException::new);
            }
        })).then(response.editInitialResponse(WebhookMessageEditRequest.builder().content("Done").build()))
                .onErrorMap(ConstraintViolationException.class,
                        e -> new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage()))
                .onErrorResume(IllegalArgumentException.class,
                        e -> response.editInitialResponse(WebhookMessageEditRequest.builder().content(e.getMessage()).build()))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }

    public Mono<Void> onButtonInteract(@GatewayEvent ButtonInteractEvent buttonInteract) {
        Member member = buttonInteract.getInteraction().getMember().get();
        InteractionResponse response = buttonInteract.getInteractionResponse();

        return buttonInteract.acknowledgeEphemeral().then(Mono.fromCallable(() -> jdbi.withExtension(RoleButtonDao.class,
                roleButton -> roleButton.getById(buttonInteract.getCustomId(), buttonInteract.getMessage().getId())))
                .flatMap(roleId -> {
                    String role = String.format("<@&%d>", roleId.asLong());
                    if (member.getRoleIds().contains(roleId)) {
                        return member.removeRole(roleId)
                                .then(response.createFollowupMessageEphemeral(String.format("Removed the %s role", role)));
                    } else {
                        return member.addRole(roleId)
                                .then(response.createFollowupMessageEphemeral(String.format("Added the %s role", role)));
                    }
                }))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }
}
