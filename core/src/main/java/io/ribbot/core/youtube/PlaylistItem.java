package io.ribbot.core.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaylistItem {
    @JsonProperty("snippet.resourceId.kind")
    private final String kind;
    @JsonProperty("snippet.resourceId.videoId")
    private final String videoId;

    public PlaylistItem(String kind, String videoId) {
        this.kind = kind;
        this.videoId = videoId;
    }

    public String getKind() {
        return kind;
    }

    public String getVideoId() {
        return videoId;
    }
}
