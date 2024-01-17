package api.discord.command.api;

import java.util.ArrayList;
import java.util.List;

import api.discord.command.AwardCommand;

public class CommandDiscordManager {
    private List<CommandDiscord> commandList;

    public CommandDiscordManager() {
        this.commandList = new ArrayList<>();
    }

    public void loadCommands() {
        this.commandList.add(new AwardCommand());
       
    }

    public CommandDiscord getCommand(String arg) {
        return this.commandList.stream()
            .filter(command -> command.getInfo().getName().equals(arg) || command.getInfo().getAliases().contains(arg))
            .findFirst()
            .orElse(null);
    }
}
