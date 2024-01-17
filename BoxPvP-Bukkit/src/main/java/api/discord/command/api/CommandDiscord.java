package api.discord.command.api;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandDiscord {
    CommandBase getInfo();

    void execute(MessageReceivedEvent event, String... args);
}
