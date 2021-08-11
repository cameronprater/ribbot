package io.ribbot.core.jdbi;

import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import discord4j.common.util.Snowflake;

@RegisterArgumentFactory(SnowflakeArgumentFactory.class)
public interface MessageDao {

    @CreateSqlObject
    ChannelDao createChannel();

    @SqlUpdate("INSERT INTO message VALUES (?, ?)")
    void insert(Snowflake id, Snowflake channelId);

    @SqlUpdate("DELETE FROM message WHERE id = ?")
    void deleteById(Snowflake id);

    @Transaction
    default void insertChannelAndMessage(Snowflake guildId, Snowflake channelId, Snowflake messageId) {
        PrimaryKeyConstraintHandler.handleInsert(() -> createChannel().insertGuildAndChannel(guildId, channelId));
        insert(messageId, channelId);
    }
}
