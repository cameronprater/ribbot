package io.ribbot.core.command;

import discord4j.core.event.domain.interaction.SlashCommandEvent;
import io.quarkiverse.discord4j.GatewayEvent;
import reactor.core.publisher.Mono;

public class VoiceChannelCommand {

    private Mono<Void> onPlay() {

    }

    private Mono<Void> onPlaying() {

    }

    private Mono<Void> onLeave() {

    }

    private Mono<Void> onSkip() {

    }

    private Mono<Void> onQueue() {

    }

    public Mono<Void> onSlashCommand(@GatewayEvent SlashCommandEvent slashCommand) {
        if (!slashCommand.getCommandName().equals("vc")) {
            return Mono.empty();
        }
    }
}
