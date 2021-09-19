package io.ribbot.core.command;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.SlashCommandEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.VoiceChannel;
import discord4j.discordjson.json.WebhookMessageEditRequest;
import discord4j.rest.interaction.InteractionResponse;
import discord4j.voice.VoiceConnection;
import io.quarkiverse.discord4j.GatewayEvent;
import io.ribbot.core.CpalApi;
import io.ribbot.core.youtube.Video;
import io.ribbot.core.youtube.YouTubeDataApi;
import org.jboss.logging.Logger;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// TODO look at all ofType operators, consider switching to cast
public class VoiceChannelCommand {
    private static final Logger LOGGER = Logger.getLogger(VoiceChannelCommand.class);
    private static final Map<Snowflake, Tuple2<Instant, Video>> nowPlaying = new ConcurrentHashMap<>();
    private static final Map<Guild, VideoQueue> videoQueue = new ConcurrentHashMap<>();
    private final YouTubeDataApi youtubeDataApi;
    private final CpalApi cpalApi;

    VoiceChannelCommand(YouTubeDataApi youtubeDataApi, CpalApi cpalApi) {
        this.youtubeDataApi = youtubeDataApi;
        this.cpalApi = cpalApi;
    }

    private Mono<VoiceChannel> handleVoiceChannelState(Member member) {
        return member.getGuild()
                .flatMap(Guild::getVoiceConnection)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("")))
                .flatMap(VoiceConnection::getChannelId)
                .flatMap(channelId -> member.getClient().getChannelById(channelId))
                .cast(VoiceChannel.class)
                .filterWhen(vc -> vc.isMemberConnected(member.getId()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("")));
    }

    private Mono<Void> onPlay(Member member, InteractionResponse response) {
    }

    private Mono<Void> onPlaying(Member member, InteractionResponse response) {

    }

    private Mono<Void> onLeave(Member member) {
        return handleVoiceChannelState(member)
                .flatMap(VoiceChannel::getVoiceConnection)
                .flatMap(VoiceConnection::disconnect)
                .then(cpalApi.stopStreaming().and(Mono.fromRunnable(() -> {
                    nowPlaying.clear();
                    videoQueue.clear();
                })));
    }

    private Mono<Void> onSkip(Member member, InteractionResponse response) {
        return handleVoiceChannelState(member)
                .then();
    }

    private Mono<Void> onQueue(Member member, InteractionResponse response) {

    }

    public Mono<Void> onSlashCommand(@GatewayEvent SlashCommandEvent slashCommand) {
        if (!slashCommand.getCommandName().equals("vc")) {
            return Mono.empty();
        }

        ApplicationCommandInteractionOption subcommandGroup = slashCommand.getOptions().get(0);
        InteractionResponse response = slashCommand.getInteractionResponse();

        return slashCommand.acknowledgeEphemeral().then(Mono.defer(() -> {
                    switch (subcommandGroup.getName()) {
                        case "play":
                            // i must not be in a voice channel
                            // invoking user must be in a voice channel
                            // ...
                            // the user can enter a video link, playlist link, or search query
                            // first verify the entered content. if an error is encountered display it then do nothing
                            // if im not in the voice channel, join it and start streaming the song
                            // if im in the voice channel and something else is playing, queue the song
                            // if im in the voice channel and nothing is playing, play the song (SHOULDN'T HAPPEN)
                        case "playing":
                            // i must be in a voice channel
                            // ...
                            // if im not playing anything (SHOULDN'T HAPPEN), show not playing anything
                            // if im playing something, show its details
                        case "leave":
                            // i must be in a voice channel
                            // invoking user must be in my voice channel
                            // ...
                            // leave the voice channel, empty the guild's queue, immediately stop streaming all songs
                        case "skip":
                            // i must be in a voice channel
                            // invoking user must be in my voice channel
                            // ...
                            // if a song is playing, stop streaming it and stream the next song in the queue
                            // if the queue is empty, i leave
                        case "queue":
                            // i must be in a voice channel
                            // to remove, user must be in my voice channel
                            // ...
                            // get
                            // (paginated) message showing queue of songs
                            // if queue is empty, display empty queue
                            // remove
                            // if queue is empty or index is less than 1 or greater than max, error
                            // remove song from queue
                        default:
                            return Mono.error(IllegalStateException::new);
                    }
                })).onErrorResume(IllegalArgumentException.class,
                        e -> response.editInitialResponse(WebhookMessageEditRequest.builder().content(e.getMessage()).build()))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }

    private static class VideoQueue {

    }
}
