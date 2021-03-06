package io.ribbot.core.command;

import java.util.Optional;
import java.util.function.Consumer;

import org.apache.commons.text.StringEscapeUtils;
import org.jboss.logging.Logger;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.SlashCommandEvent;
import discord4j.core.object.Embed;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.MessageData;
import discord4j.discordjson.json.WebhookMessageEditRequest;
import discord4j.rest.http.client.ClientException;
import discord4j.rest.interaction.InteractionResponse;
import io.quarkiverse.discord4j.GatewayEvent;
import io.ribbot.core.ColorConverter;
import io.ribbot.core.MessageLink;
import io.ribbot.core.PaginatedMessage.Page;
import reactor.core.publisher.Mono;

public class MessageCommand {
    private static final Logger LOGGER = Logger.getLogger(MessageCommand.class);
    private final ColorConverter colorConverter;

    MessageCommand(ColorConverter colorConverter) {
        this.colorConverter = colorConverter;
    }

    private Mono<MessageData> onEmbed(ApplicationCommandInteractionOption subcommandGroup,
            Mono<MessageChannel> interactionChannel, InteractionResponse response) {
        ApplicationCommandInteractionOption subcommand = subcommandGroup.getOptions().get(0);
        Optional<String> titleOption = subcommand.getOption("title")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString);
        Optional<String> descriptionOption = subcommand.getOption("desc")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .map(StringEscapeUtils::unescapeJava);
        Optional<String> colorOption = subcommand.getOption("color")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString);
        if (titleOption.isEmpty() && descriptionOption.isEmpty()) {
            return Mono.error(new IllegalArgumentException("Title or description must be provided"));
        }

        Consumer<EmbedCreateSpec> embedCreator = embedSpec -> {
            titleOption.ifPresent(embedSpec::setTitle);
            descriptionOption.ifPresent(embedSpec::setDescription);
            colorOption.ifPresent(color -> embedSpec.setColor(colorConverter.convert(color)));
        };

        switch (subcommand.getName()) {
            case "add":
                String messageOption = subcommand.getOption("msg")
                        .flatMap(ApplicationCommandInteractionOption::getValue)
                        .map(ApplicationCommandInteractionOptionValue::asString)
                        .get();
                GatewayDiscordClient gateway = subcommand.getClient();
                Mono<Message> messageMono = Mono.fromCallable(() -> MessageLink.from(messageOption))
                        .flatMap(messageLink -> gateway.getGuildById(messageLink.getGuildId())
                                .flatMap(guild -> guild.getChannelById(messageLink.getChannelId()))
                                .ofType(MessageChannel.class)
                                .flatMap(channel -> channel.getMessageById(messageLink.getMessageId())))
                        .onErrorMap(ClientException.isStatusCode(404, 403), e -> new IllegalArgumentException(
                                String.format("%s doesn't exist or is inaccessible", messageOption)))
                        .filter(message -> message.getAuthor().map(User::getId).map(gateway.getSelfId()::equals).orElse(false))
                        .switchIfEmpty(Mono.error(
                                new IllegalArgumentException(String.format("%s isn't a Ribbot message", messageOption))));

                return messageMono.flatMap(message -> message.edit(messageSpec -> {
                    for (Embed embed : message.getEmbeds()) {
                        Page.from(embed).accept(messageSpec);
                    }
                    messageSpec.addEmbed(embedCreator);
                })).then(response.editInitialResponse(WebhookMessageEditRequest.builder()
                        .content(String.format("Embed added to %s", messageOption))
                        .build()));
            case "new":
                Mono<MessageChannel> channelMono = subcommand.getOption("channel")
                        .flatMap(ApplicationCommandInteractionOption::getValue)
                        .map(value -> value.asChannel().ofType(MessageChannel.class)
                                .switchIfEmpty(Mono.error(new IllegalArgumentException(
                                        String.format("<#%s> isn't a text channel", value.getRaw())))))
                        .orElse(interactionChannel);

                return channelMono.flatMap(channel -> channel.createMessage(messageSpec -> messageSpec.addEmbed(embedCreator)))
                        .flatMap(message -> message.getGuild()
                                .flatMap(guild -> response.editInitialResponse(WebhookMessageEditRequest.builder()
                                        .content(String.format("Embed created at https://discord.com/channels/%d/%d/%d",
                                                guild.getId().asLong(),
                                                message.getChannelId().asLong(),
                                                message.getId().asLong()))
                                        .build())));
            default:
                return Mono.error(IllegalStateException::new);
        }
    }

    private Mono<MessageData> onNew(ApplicationCommandInteractionOption subcommand, Mono<MessageChannel> interactionChannel,
            InteractionResponse response) {
        Mono<MessageChannel> channelMono = subcommand.getOption("channel")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(value -> value.asChannel().ofType(MessageChannel.class)
                        .switchIfEmpty(Mono.error(new IllegalArgumentException(
                                String.format("<#%s> isn't a text channel", value.getRaw())))))
                .orElse(interactionChannel);
        String text = subcommand.getOption("txt")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .map(StringEscapeUtils::unescapeJava)
                .get();

        return channelMono.flatMap(channel -> channel.createMessage(text))
                .flatMap(message -> message.getGuild()
                        .flatMap(guild -> response.editInitialResponse(WebhookMessageEditRequest.builder()
                                .content(String.format("Message created at https://discord.com/channels/%d/%d/%d",
                                        guild.getId().asLong(),
                                        message.getChannelId().asLong(),
                                        message.getId().asLong()))
                                .build())));
    }

    public Mono<Void> onSlashCommand(@GatewayEvent SlashCommandEvent slashCommand) {
        if (!slashCommand.getCommandName().equals("msg")) {
            return Mono.empty();
        }

        ApplicationCommandInteractionOption subcommandGroup = slashCommand.getOptions().get(0);
        Mono<MessageChannel> interactionChannel = slashCommand.getInteraction().getChannel();
        InteractionResponse response = slashCommand.getInteractionResponse();

        return slashCommand.acknowledgeEphemeral().then(Mono.defer(() -> {
            switch (subcommandGroup.getName()) {
                case "embed":
                    return onEmbed(subcommandGroup, interactionChannel, response);
                case "new":
                    return onNew(subcommandGroup, interactionChannel, response);
                default:
                    return Mono.error(IllegalStateException::new);
            }
        })).onErrorResume(IllegalArgumentException.class,
                e -> response.editInitialResponse(WebhookMessageEditRequest.builder().content(e.getMessage()).build()))
                .onErrorResume(e -> Mono.fromRunnable(() -> LOGGER.warn(e)))
                .then();
    }
}
