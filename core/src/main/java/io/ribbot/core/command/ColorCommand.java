package io.ribbot.core.command;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.SlashCommandEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.command.Interaction;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Role;
import discord4j.discordjson.json.*;
import discord4j.rest.interaction.InteractionResponse;
import discord4j.rest.util.Color;
import io.quarkiverse.discord4j.GatewayEvent;
import io.ribbot.core.ColorConverter;
import reactor.core.publisher.Mono;

public class ColorCommand {
    private static final Logger LOGGER = Logger.getLogger(ColorCommand.class);
    private final ColorConverter colorConverter;

    @ConfigProperty(name = "ribbot.game-corner.villager")
    Snowflake villager;

    ColorCommand(ColorConverter colorConverter) {
        this.colorConverter = colorConverter;
    }

    public Mono<Void> onSlashCommand(@GatewayEvent SlashCommandEvent slashCommand) {
        if (!slashCommand.getCommandName().equals("color")) {
            return Mono.empty();
        }

        String colorOption = slashCommand.getOption("color")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        Interaction interaction = slashCommand.getInteraction();
        Member member = interaction.getMember().get();
        InteractionResponse response = slashCommand.getInteractionResponse();

        return slashCommand.acknowledgeEphemeral().then(interaction.getGuild())
                .zipWith(Mono.fromSupplier(() -> colorConverter.convert(colorOption)))
                .flatMap(tuple -> {
                    Guild guild = tuple.getT1();
                    Color color = tuple.getT2();

                    return guild.getRoles().filter(role -> role.getName().equals(colorOption))
                            .switchIfEmpty(guild.createRole(spec -> spec.setName(colorOption).setColor(color))
                                    .zipWith(guild.getRoleById(villager))
                                    .flatMap(t2 -> {
                                        Role role = t2.getT1();
                                        Role villager = t2.getT2();
                                        return role.changePosition(villager.getRawPosition()).then().thenReturn(role);
                                    }))
                            .next()
                            .flatMap(role -> {
                                if (member.getRoleIds().contains(role.getId())) {
                                    return Mono.error(new IllegalArgumentException(
                                            String.format("Your name is already %s", colorOption)));
                                } else {
                                    return member.getRoles()
                                            .filter(memberRole -> memberRole.getName().matches(ColorConverter.COLOR_REGEX))
                                            .flatMap(colorRole -> guild.getMembers()
                                                    .collectList()
                                                    .flatMap(members -> {
                                                        for (Member guildMember : members) {
                                                            if (!guildMember.equals(member) &&
                                                                    guildMember.getRoleIds().contains(colorRole.getId())) {
                                                                return member.removeRole(colorRole.getId());
                                                            }
                                                        }
                                                        return colorRole.delete();
                                                    }))
                                            .then(member.addRole(role.getId()));
                                }
                            });
                }).then(response.editInitialResponse(WebhookMessageEditRequest.builder()
                        .content(String.format("Your name is now %s", colorOption))
                        .build()))
                .onErrorResume(IllegalArgumentException.class,
                        e -> response.editInitialResponse(WebhookMessageEditRequest.builder().content(e.getMessage()).build()))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }
}
