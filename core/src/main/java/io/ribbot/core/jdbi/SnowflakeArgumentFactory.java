package io.ribbot.core.jdbi;

import java.sql.Types;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

import discord4j.common.util.Snowflake;

public class SnowflakeArgumentFactory extends AbstractArgumentFactory<Snowflake> {

    public SnowflakeArgumentFactory() {
        super(Types.BIGINT);
    }

    @Override
    protected Argument build(Snowflake value, ConfigRegistry config) {
        return (position, statement, ctx) -> statement.setLong(position, value.asLong());
    }
}
