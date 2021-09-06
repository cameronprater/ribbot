package io.ribbot.core;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.spi.Converter;

import discord4j.rest.util.Color;

@ApplicationScoped
public class ColorConverter implements Converter<Color> {
    public static final String COLOR_REGEX = "#[0-9A-Fa-f]{6}";

    private Color convert(int value) {
        return Color.of((value & 0xFF0000) >> 16, (value & 0xFF00) >> 8, (value & 0xFF));
    }

    @Override
    public Color convert(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        if (value.matches(COLOR_REGEX)) {
            return value.equalsIgnoreCase("#ffffff") ? Color.DISCORD_WHITE
                    : value.equals("#000000") ? Color.DISCORD_BLACK : convert(Integer.decode(value));
        }
        throw new IllegalArgumentException(String.format("%s isn't a color code", value));
    }
}
