package io.ribbot.core;

import org.eclipse.microprofile.config.spi.Converter;

import discord4j.common.util.Snowflake;

public class SnowflakeConverter implements Converter<Snowflake> {

    @Override
    public Snowflake convert(String value) {
        return value == null || value.isBlank() ? null : Snowflake.of(value);
    }
}
