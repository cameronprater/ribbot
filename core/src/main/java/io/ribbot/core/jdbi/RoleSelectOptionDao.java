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
public interface RoleSelectOptionDao {

    @CreateSqlObject
    RoleSelectMenuDao createRoleSelectMenu();

    @CreateSqlObject
    RoleDao createRole();

    @SqlUpdate("INSERT INTO role_select_option VALUES (?, ?)")
    void insert(String selectMenuId, Snowflake roleId);

    @SqlQuery("SELECT message.channel_id, menu.message_id " +
            "FROM role_select_option AS option " +
            "INNER JOIN role_select_menu AS menu ON option.select_menu_id = menu.select_menu_id " +
            "INNER JOIN message ON menu.message_id = message.id " +
            "WHERE option.role_id = ?")
    @RegisterConstructorMapper(RoleSelectOption.class)
    List<RoleSelectOption> getByRoleId(Snowflake roleId);

    @SqlQuery("SELECT role_id FROM role_select_option WHERE select_menu_id = ?")
    List<Snowflake> getBySelectMenuId(String selectMenuId);

    @Transaction
    default void insert(Snowflake guildId, Snowflake channelId, Snowflake messageId, String selectMenuId, Snowflake roleId) {
        PrimaryKeyConstraintHandler
                .handleInsert(() -> createRoleSelectMenu().insert(guildId, channelId, messageId, selectMenuId));
        PrimaryKeyConstraintHandler.handleInsert(() -> createRole().insert(roleId, guildId));
        insert(selectMenuId, roleId);
    }

    class RoleSelectOption {
        private final Snowflake channelId;
        private final Snowflake messageId;

        public RoleSelectOption(Snowflake channelId, Snowflake messageId) {
            this.channelId = channelId;
            this.messageId = messageId;
        }

        public Snowflake getChannelId() {
            return channelId;
        }

        public Snowflake getMessageId() {
            return messageId;
        }
    }
}
