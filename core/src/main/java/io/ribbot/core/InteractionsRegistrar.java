package io.ribbot.core;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import discord4j.discordjson.Id;
import discord4j.discordjson.json.ApplicationCommandPermissionsData;
import discord4j.discordjson.json.ApplicationCommandPermissionsRequest;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.discordjson.json.GuildApplicationCommandPermissionsData;
import discord4j.rest.RestClient;
import discord4j.rest.http.client.ClientException;
import discord4j.rest.service.ApplicationService;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.util.ClassPathUtils;
import reactor.core.publisher.Mono;

public class InteractionsRegistrar {
    private static final Logger LOGGER = Logger.getLogger(InteractionsRegistrar.class);

    @ConfigProperty(name = "ribbot.game-corner")
    long gameCorner;

    @ConfigProperty(name = "ribbot.game-corner.island-representative")
    long islandRepresentative;

    void onStartup(@Observes StartupEvent startup, ObjectMapper objectMapper, RestClient discord) throws IOException {
        List<ApplicationCommandRequest> commands = new ArrayList<>();
        ClassPathUtils.consumeAsPaths("META-INF/commands/", resource -> {
            try {
                Files.walkFileTree(resource, new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path p, BasicFileAttributes attrs) throws IOException {
                        commands.add(objectMapper.readValue(Files.newInputStream(p), ApplicationCommandRequest.class));
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });

        List<Mono<?>> monos = new ArrayList<>();
        Mono<Long> applicationId = discord.getApplicationId();
        ApplicationService applicationService = discord.getApplicationService();

        for (ApplicationCommandRequest command : commands) {
            monos.add(applicationId.flatMap(
                    id -> applicationService.createGuildApplicationCommand(id, gameCorner, command)
                            .doOnNext(cmd -> LOGGER.infof("Upserted command %s in guild %d", cmd.name(), gameCorner))));
        }

        Mono<Void> updateCommands = applicationId
                .flatMapMany(id -> applicationService.getGuildApplicationCommands(id, gameCorner)
                        .delayUntil(command -> {
                            if (!command.defaultPermission().toOptional().orElse(true)) {
                                long commandId = Long.parseLong(command.id());
                                ApplicationCommandPermissionsRequest req = ApplicationCommandPermissionsRequest.builder()
                                        .addPermission(ApplicationCommandPermissionsData.builder()
                                                .id(islandRepresentative)
                                                .type(2)
                                                .permission(true)
                                                .build())
                                        .build();

                                return applicationService.getApplicationCommandPermissions(id, gameCorner, commandId)
                                        .map(GuildApplicationCommandPermissionsData::permissions)
                                        .filter(permissions -> permissions.stream()
                                                .map(ApplicationCommandPermissionsData::id)
                                                .noneMatch(Id.of(islandRepresentative)::equals))
                                        .switchIfEmpty(Mono.error(
                                                new UnsupportedOperationException(
                                                        String.format("%s's permissions don't need modified", command.name()))))
                                        .onErrorResume(ClientException.isStatusCode(404), e -> Mono.empty())
                                        .then(applicationService.modifyApplicationCommandPermissions(id, gameCorner, commandId,
                                                req))
                                        .then(Mono.fromRunnable(
                                                () -> LOGGER.infof("Modified permissions for %s", command.name())))
                                        .onErrorResume(UnsupportedOperationException.class,
                                                e -> Mono.fromRunnable(() -> LOGGER.warn(e)));
                            }
                            return Mono.empty();
                        })
                        .filter(command -> commands.stream().map(ApplicationCommandRequest::name)
                                .noneMatch(command.name()::equals))
                        .delayUntil(command -> applicationService.deleteGuildApplicationCommand(id, gameCorner,
                                Long.parseLong(command.id())))
                        .doOnNext(command -> LOGGER.infof("Deleted command %s", command.name())))
                .then();
        monos.add(updateCommands);

        Mono.when(monos).block();
    }
}
