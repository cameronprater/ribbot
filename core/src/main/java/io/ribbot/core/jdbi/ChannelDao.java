package io.ribbot.core.jdbi;

import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import discord4j.common.util.Snowflake;

@RegisterArgumentFactory(SnowflakeArgumentFactory.class)
public interface ChannelDao {

    @CreateSqlObject
    GuildDao createGuild();

    @SqlUpdate("INSERT INTO channel VALUES (?, ?)")
    void insert(Snowflake id, Snowflake guildId);

    @SqlUpdate("DELETE FROM channel WHERE id = ?")
    void deleteById(Snowflake id);

    @Transaction
    default void insertGuildAndChannel(Snowflake guildId, Snowflake channelId) {
        PrimaryKeyConstraintHandler.handleInsert(() -> createGuild().insert(guildId));
        insert(channelId, guildId);
    }
}
