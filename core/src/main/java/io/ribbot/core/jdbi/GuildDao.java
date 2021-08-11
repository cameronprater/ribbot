package io.ribbot.core.jdbi;

import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import discord4j.common.util.Snowflake;

@RegisterArgumentFactory(SnowflakeArgumentFactory.class)
public interface GuildDao {

    @SqlUpdate("INSERT INTO guild VALUES (?)")
    void insert(Snowflake id);

    @SqlUpdate("DELETE FROM guild WHERE id = ?")
    void deleteById(Snowflake id);
}
