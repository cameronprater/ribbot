package io.ribbot.genshin.impact.dao;

import io.ribbot.genshin.impact.entity.Character;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterMapper implements RowMapper<Character> {

    @Override
    public Character map(ResultSet rs, StatementContext ctx) throws SQLException {
        return null;
    }
}
