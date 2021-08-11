package io.ribbot.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MessageLinkTest {

    @Test
    public void test() {
        long guildId = 845327886076477460L;
        long channelId = 845327886076477463L;
        long messageId = 871966674042118185L;

        MessageLink messageLink = MessageLink
                .from(String.format("https://discord.com/channels/%d/%d/%d", guildId, channelId, messageId));
        Assertions.assertEquals(guildId, messageLink.getGuildId().asLong());
        Assertions.assertEquals(channelId, messageLink.getChannelId().asLong());
        Assertions.assertEquals(messageId, messageLink.getMessageId().asLong());
    }
}
