package io.ribbot.core;

import org.jboss.logging.Logger;
import org.jdbi.v3.core.Jdbi;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.channel.TextChannelDeleteEvent;
import discord4j.core.event.domain.guild.GuildDeleteEvent;
import discord4j.core.event.domain.message.MessageBulkDeleteEvent;
import discord4j.core.event.domain.message.MessageDeleteEvent;
import discord4j.core.event.domain.role.RoleDeleteEvent;
import io.quarkiverse.discord4j.GatewayEvent;
import io.ribbot.core.command.RoleButtonCommand;
import io.ribbot.core.jdbi.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ResourceDeleteObserver {
    private static final Logger LOGGER = Logger.getLogger(RoleButtonCommand.class);
    private final Jdbi jdbi;

    ResourceDeleteObserver(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Mono<Void> onGuildDelete(@GatewayEvent GuildDeleteEvent guildDelete) {
        if (guildDelete.isUnavailable()) {
            return Mono.empty();
        } else {
            return Mono.fromRunnable(
                    () -> jdbi.useExtension(GuildDao.class, guild -> guild.deleteById(guildDelete.getGuildId())))
                    .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                    .then();
        }
    }

    public Mono<Void> onChannelDelete(@GatewayEvent TextChannelDeleteEvent channelDelete) {
        return Mono.fromRunnable(
                () -> jdbi.useExtension(ChannelDao.class, channel -> channel.deleteById(channelDelete.getChannel().getId())))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }

    public Mono<Void> onMessageDelete(@GatewayEvent MessageDeleteEvent messageDelete) {
        return Mono.fromRunnable(
                () -> jdbi.useExtension(MessageDao.class, message -> message.deleteById(messageDelete.getMessageId())))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }

    public Mono<Void> onMessageBulkDelete(@GatewayEvent MessageBulkDeleteEvent messageBulkDelete) {
        return Flux.fromIterable(messageBulkDelete.getMessageIds())
                .flatMap(id -> Mono.fromRunnable(() -> jdbi.useExtension(MessageDao.class, message -> message.deleteById(id))))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }

    public Mono<Void> onRoleDelete(@GatewayEvent RoleDeleteEvent roleDelete, MessageComponentHelper messageComponentHelper) {
        GatewayDiscordClient gateway = roleDelete.getClient();
        Snowflake roleId = roleDelete.getRoleId();

        Mono<Void> removeButtons = Mono.fromCallable(() -> jdbi.withExtension(RoleButtonDao.class,
                roleButton -> roleButton.getByRoleId(roleId)))
                .flatMapMany(Flux::fromIterable)
                .flatMap(roleButton -> gateway.getMessageById(roleButton.getChannelId(), roleButton.getMessageId())
                        .flatMap(message -> message.edit(messageSpec -> messageSpec
                                .setComponents(messageComponentHelper.removeComponent(message, roleButton.getButtonId())))))
                .then();

        Mono<Void> removeSelectMenuOptions = Mono.fromCallable(() -> jdbi.withExtension(RoleSelectOptionDao.class,
                roleSelectOption -> roleSelectOption.getByRoleId(roleId)))
                .flatMapMany(Flux::fromIterable)
                .flatMap(roleSelectOption -> gateway.getMessageById(roleSelectOption.getChannelId(),
                        roleSelectOption.getMessageId()))
                .flatMap(message -> message.edit(messageSpec -> messageSpec
                        .setComponents(messageComponentHelper.removeOption(message, roleId.asString(), jdbi))))
                .then();

        return Mono.when(removeButtons, removeSelectMenuOptions)
                .then(Mono.fromRunnable(() -> jdbi.useExtension(RoleDao.class, role -> role.deleteById(roleDelete.getRoleId()))))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }
}
