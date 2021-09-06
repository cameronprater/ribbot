package io.ribbot.core.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import discord4j.common.util.Snowflake;

public class SnowflakeMapper implements ColumnMapper<Snowflake> {

    @Override
    public Snowflake map(ResultSet rs, int col, StatementContext ctx) throws SQLException {
        return Snowflake.of(rs.getLong(col));
    }
}
