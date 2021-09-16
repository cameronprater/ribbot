package io.ribbot.core.youtube;

import discord4j.rest.RestClient;
import discord4j.rest.RestResources;
import discord4j.rest.http.JacksonReaderStrategy;
import discord4j.rest.http.ReaderStrategy;
import discord4j.rest.util.Multimap;
import discord4j.rest.util.RouteUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.Map;

public class YouTubeService {
    private static final String YOUTUBE_API_URL = "https://googleapis.com/youtube/v3";
    private final HttpClient httpClient;
    private final ReaderStrategy<?> reader;
    private final String apiKey;

    YouTubeService(RestClient discord, @ConfigProperty(name = "ribbot.youtube.api-key") String apiKey) {
        RestResources restResources = discord.getRestResources();
        this.httpClient = restResources.getReactorResources().getHttpClient().baseUrl(YOUTUBE_API_URL);
        this.reader = new JacksonReaderStrategy<>(restResources.getJacksonResources().getObjectMapper());
        this.apiKey = apiKey;
    }

    private Flux<PlaylistItem> getPlaylistItems(String id, String pageToken) {
        Multimap<String, Object> values = new Multimap<>();
        values.setAll(Map.of(
                "key", apiKey,
                "part", "snippet",
                "fields", "nextPageToken, pageInfo/*, items/snippet/resourceId/*",
                "playlistId", id,
                "maxResults", 50)
        );
        return httpClient.get()
                .uri(RouteUtils.expandQuery("/playlistItems", values))
                .responseSingle((response, body) -> {
                    if (response.status().code() >= 400) {
                        return null;
                    } else {
                        return ((ReaderStrategy<PlaylistItem[]>)reader).read(body, PlaylistItem[].class);
                    }
                })
                .flatMapMany(Flux::fromArray);
    }

    private Mono<Video> getVideo(String id) {
        Multimap<String, Object> values = new Multimap<>();
        values.setAll(Map.of(
                "key", apiKey,
                "part", "snippet,contentDetails",
                "fields", "items/snippet(title,channelTitle),contentDetails(duration, regionRestriction/*, contentRating/*)",
                "id", id,
                "maxResults", 1)
        );
        return httpClient.get()
                .uri(RouteUtils.expandQuery("/videos", values))
                .responseSingle((response, body) -> {
                    if (response.status().code() >= 400) {
                        return null;
                    } else {
                        return ((ReaderStrategy<Video>)reader).read(body, Video.class);
                    }
                });
    }

    private Mono<SearchResult> search(String query) {
        Multimap<String, Object> values = new Multimap<>();
        values.setAll(Map.of(
                "key", apiKey,
                "part", "id",
                "fields", "items/id(kind,videoId,playlistId)",
                "maxResults", 1,
                "q", query,
                "type", "video,playlist")
        );
        return httpClient.get()
                .uri(RouteUtils.expandQuery("/search", values))
                .responseSingle((response, body) -> {
                    if (response.status().code() >= 400) {
                        return null;
                    } else {
                        return ((ReaderStrategy<SearchResult>)reader).read(body, SearchResult.class);
                    }
                });
    }

    public Flux<Video> getVideos(String query) {
        return search(query).flatMapMany(searchResult -> {
            switch (searchResult.getKind()) {
                case VIDEO:
                    return getVideo(searchResult.getVideoId()).flux();
                case PLAYLIST:
                    return getPlaylistVideos(searchResult.getPlaylistId());
                default:
                    return Mono.error(IllegalStateException::new);
            }
        });
    }

    public Flux<Video> getPlaylistVideos(String id) {
        return getPlaylistItems(id).flatMap(playlistItem -> playlistItem.);
    }
}
