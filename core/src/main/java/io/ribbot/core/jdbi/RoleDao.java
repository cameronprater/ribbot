package io.ribbot.core.jdbi;

import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import discord4j.common.util.Snowflake;

@RegisterArgumentFactory(SnowflakeArgumentFactory.class)
public interface RoleDao {

    @SqlUpdate("INSERT INTO role VALUES (?, ?)")
    void insert(Snowflake id, Snowflake guildId);

    @SqlUpdate("DELETE FROM role WHERE id = ?")
    void deleteById(Snowflake id);
}
