package io.ribbot.core;

import discord4j.common.util.Snowflake;

public class MessageLink {
    private static final String REGEX = String.format("(http(s)?://)?(www\\.)?discord\\.com/channels(/%s){3}",
            SnowflakeConstants.REGEX);

    private final Snowflake guildId;
    private final Snowflake channelId;
    private final Snowflake messageId;

    public static MessageLink from(String s) {
        if (!s.matches(REGEX)) {
            throw new IllegalArgumentException(String.format("%s isn't a guild message link", s));
        }

        int i = s.indexOf("channels") + "channels".length() + 1;
        String guildId = s.substring(i, s.indexOf('/', i));
        i = s.indexOf(guildId) + SnowflakeConstants.LENGTH + 1;
        String channelId = s.substring(i, s.indexOf('/', i));
        String messageId = s.substring(s.indexOf(channelId) + SnowflakeConstants.LENGTH + 1);

        return new MessageLink(Snowflake.of(guildId), Snowflake.of(channelId), Snowflake.of(messageId));
    }

    private MessageLink(Snowflake guildId, Snowflake channelId, Snowflake messageId) {
        this.guildId = guildId;
        this.channelId = channelId;
        this.messageId = messageId;
    }

    public Snowflake getGuildId() {
        return guildId;
    }

    public Snowflake getChannelId() {
        return channelId;
    }

    public Snowflake getMessageId() {
        return messageId;
    }
}
