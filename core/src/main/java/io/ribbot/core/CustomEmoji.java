package io.ribbot.core;

import discord4j.common.util.Snowflake;

public class CustomEmoji {
    public static final String REGEX = String.format("<(a)?:\\w+:%s>", SnowflakeConstants.REGEX);

    private final boolean animated;
    private final String name;
    private final Snowflake id;

    public static CustomEmoji from(String s) {
        if (!s.matches(REGEX)) {
            throw new IllegalArgumentException(String.format("%s isn't an emoji", s));
        }

        boolean animated = s.startsWith("<a");
        String name = s.substring(s.indexOf(':') + 1, s.lastIndexOf(':'));
        Snowflake id = Snowflake.of(s.substring(s.lastIndexOf(':') + 1, s.indexOf('>')));

        return new CustomEmoji(animated, name, id);
    }

    private CustomEmoji(boolean animated, String name, Snowflake id) {
        this.animated = animated;
        this.name = name;
        this.id = id;
    }

    public boolean isAnimated() {
        return animated;
    }

    public String getName() {
        return name;
    }

    public Snowflake getId() {
        return id;
    }
}
