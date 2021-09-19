package io.ribbot.core.youtube;

import discord4j.rest.RestClient;
import discord4j.rest.RestResources;
import discord4j.rest.http.JacksonReaderStrategy;
import discord4j.rest.http.ReaderStrategy;
import discord4j.rest.json.response.ErrorResponse;
import discord4j.rest.util.Multimap;
import discord4j.rest.util.RouteUtils;
import io.smallrye.common.constraint.Nullable;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;
import reactor.netty.http.client.HttpClient;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class YouTubeDataApi {
    private static final String YOUTUBE_API_URL = "https://googleapis.com/youtube/v3";
    private final HttpClient httpClient;
    private final ReaderStrategy<?> reader;
    private final String apiKey;

    YouTubeDataApi(RestClient discord, @ConfigProperty(name = "ribbot.youtube.api-key") String apiKey) {
        RestResources restResources = discord.getRestResources();
        this.httpClient = restResources.getReactorResources().getHttpClient().baseUrl(YOUTUBE_API_URL);
        this.reader = new JacksonReaderStrategy<>(restResources.getJacksonResources().getObjectMapper());
        this.apiKey = apiKey;
    }

    @SuppressWarnings("unchecked")
    private <T> Mono<T> read(ByteBufMono body, Class<T> type) {
        return ((ReaderStrategy<T>)reader).read(body, type);
    }

    private Mono<ListPlaylistItemsResponse> requestPlaylistItems(String id, @Nullable String pageToken) {
        Multimap<String, Object> values = new Multimap<>();
        values.setAll(Map.of(
                "key", apiKey,
                "part", "snippet",
                "fields", "nextPageToken, items/snippet/resourceId/*",
                "playlistId", id,
                "maxResults", 50)
        );
        if (pageToken != null) {
            values.add("pageToken", pageToken);
        }
        return httpClient.get()
                .uri(RouteUtils.expandQuery("/playlistItems", values))
                .responseSingle((response, body) -> {
                    int statusCode = response.status().code();
                    if (statusCode >= 400) {
                        return read(body, ErrorResponse.class)
                                .flatMap(error -> Mono.error(new YouTubeDataApiException(statusCode, error)));
                    } else {
                        return read(body, ListPlaylistItemsResponse.class);
                    }
                });
    }

    private Flux<PlaylistItem> getPlaylistItems(String id) {
        return requestPlaylistItems(id, null)
                .expand(response -> {
                    String pageToken = response.getNextPageToken();
                    if (pageToken != null) {
                        return requestPlaylistItems(id, pageToken);
                    } else {
                        return Mono.empty();
                    }
                })
                .map(ListPlaylistItemsResponse::getItems)
                .flatMap(Flux::fromIterable);
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
                    int statusCode = response.status().code();
                    if (statusCode >= 400) {
                        return read(body, ErrorResponse.class)
                                .flatMap(error -> Mono.error(new YouTubeDataApiException(statusCode, error)));
                    } else {
                        return read(body, SearchResult.class);
                    }
                });
    }

    public Flux<Video> getPlaylistVideos(String id) {
        return getPlaylistItems(id)
                .filter(playlistItem -> playlistItem.getKind().equals("video"))
                .map(PlaylistItem::getVideoId)
                .flatMap(this::getVideo);
    }

    public Mono<Video> getVideo(String id) {
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
                    int statusCode = response.status().code();
                    if (statusCode >= 400) {
                        return read(body, ErrorResponse.class)
                                .flatMap(error -> Mono.error(new YouTubeDataApiException(statusCode, error)));
                    } else {
                        return read(body, Video.class);
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
}
