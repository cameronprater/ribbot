package io.ribbot.core.youtube;

import java.util.List;

public class ListPlaylistItemsResponse {
    private final String nextPageToken;
    private final List<PlaylistItem> items;

    public ListPlaylistItemsResponse(String nextPageToken, List<PlaylistItem> items) {
        this.nextPageToken = nextPageToken;
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public List<PlaylistItem> getItems() {
        return items;
    }
}
