package io.ribbot.core.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.util.List;

public class Video {
    private final String id;
    @JsonProperty("snippet.title")
    private final String title;
    @JsonProperty("snippet.channelTitle")
    private final String channelTitle;
    @JsonProperty("contentDetails.duration")
    private final Duration duration;
    @JsonProperty("contentDetails.regionRestriction.allowed")
    private final List<String> allowedRegions;
    @JsonProperty("contentDetails.regionRestriction.blocked")
    private final List<String> blockedRegions;
    @JsonProperty("contentDetails.contentRating.ytRating")
    private final boolean ageRestricted;

    public Video(String id, String title, String channelTitle, Duration duration, List<String> allowedRegions, List<String> blockedRegions, boolean ageRestricted) {
        this.id = id;
        this.title = title;
        this.channelTitle = channelTitle;
        this.duration = duration;
        this.allowedRegions = allowedRegions;
        this.blockedRegions = blockedRegions;
        this.ageRestricted = ageRestricted;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public Duration getDuration() {
        return duration;
    }

    public List<String> getAllowedRegions() {
        return allowedRegions;
    }

    public List<String> getBlockedRegions() {
        return blockedRegions;
    }

    public boolean isAgeRestricted() {
        return ageRestricted;
    }
}
