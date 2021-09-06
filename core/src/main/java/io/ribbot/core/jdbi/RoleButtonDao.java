package io.ribbot.core.jdbi;

import java.util.List;

import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import discord4j.common.util.Snowflake;

@RegisterArgumentFactory(SnowflakeArgumentFactory.class)
@RegisterColumnMapper(SnowflakeMapper.class)
public interface RoleButtonDao {

    @CreateSqlObject
    MessageDao createMessage();

    @CreateSqlObject
    RoleDao createRole();

    @SqlUpdate("INSERT INTO role_button VALUES (?, ?, ?)")
    void insert(String id, Snowflake messageId, Snowflake roleId);

    @SqlQuery("SELECT role_id FROM role_button WHERE button_id = ? AND message_id = ?")
    Snowflake getById(String id, Snowflake messageId);

    @SqlQuery("SELECT button.button_id, message.channel_id, button.message_id " +
            "FROM role_button AS button " +
            "INNER JOIN message ON button.message_id = message.id " +
            "WHERE button.role_id = ?")
    @RegisterConstructorMapper(RoleButton.class)
    List<RoleButton> getByRoleId(Snowflake roleId);

    @Transaction
    default void insert(Snowflake guildId, Snowflake channelId, Snowflake messageId, Snowflake roleId, String buttonId) {
        PrimaryKeyConstraintHandler.handleInsert(() -> createMessage().insertChannelAndMessage(guildId, channelId, messageId));
        PrimaryKeyConstraintHandler.handleInsert(() -> createRole().insert(roleId, guildId));
        insert(buttonId, messageId, roleId);
    }

    class RoleButton {
        private final String buttonId;
        private final Snowflake channelId;
        private final Snowflake messageId;

        public RoleButton(String buttonId, Snowflake channelId, Snowflake messageId) {
            this.buttonId = buttonId;
            this.channelId = channelId;
            this.messageId = messageId;
        }

        public Snowflake getChannelId() {
            return channelId;
        }

        public Snowflake getMessageId() {
            return messageId;
        }

        public String getButtonId() {
            return buttonId;
        }
    }
}
