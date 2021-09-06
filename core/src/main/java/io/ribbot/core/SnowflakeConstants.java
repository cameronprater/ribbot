package io.ribbot.core;

public class SnowflakeConstants {
    public static final int LENGTH = 18;
    public static final String REGEX = String.format("\\d{%d}", LENGTH);

    private SnowflakeConstants() {
    }
}
