package io.ribbot.core.jdbi;

import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import discord4j.common.util.Snowflake;

@RegisterArgumentFactory(SnowflakeArgumentFactory.class)
public interface RoleSelectMenuDao {

    @CreateSqlObject
    MessageDao createMessage();

    @SqlUpdate("INSERT INTO role_select_menu VALUES (?, ?)")
    void insert(String id, Snowflake messageId);

    @SqlUpdate("DELETE FROM role_select_menu WHERE select_menu_id = ?")
    void delete(String selectMenuId);

    @Transaction
    default void insert(Snowflake guildId, Snowflake channelId, Snowflake messageId, String selectMenuId) {
        PrimaryKeyConstraintHandler.handleInsert(() -> createMessage().insertChannelAndMessage(guildId, channelId, messageId));
        insert(selectMenuId, messageId);
    }
}
