package io.ribbot.core.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {
    @JsonProperty("id.kind")
    private final Kind kind;
    @JsonProperty("id.videoId")
    private final String videoId;
    @JsonProperty("id.playlistId")
    private final String playlistId;

    public SearchResult(Kind kind, String videoId, String playlistId) {
        this.kind = kind;
        this.videoId = videoId;
        this.playlistId = playlistId;
    }

    public Kind getKind() {
        return kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public enum Kind {
        VIDEO,
        PLAYLIST,
        CHANNEL
    }
}
