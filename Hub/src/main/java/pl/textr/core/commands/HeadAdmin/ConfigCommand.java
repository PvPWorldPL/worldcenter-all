package pl.textr.core.commands.HeadAdmin;

import org.bukkit.command.CommandSender;

import pl.textr.core.LobbyPlugin;
import pl.textr.core.commands.Api.Command;
import pl.textr.core.utils.other.ChatUtil;

public class ConfigCommand extends Command {
    public ConfigCommand() {
        super("core", "Informacje o Core", "/core <reload>", "core.cmd.headadmin", "config", "configuration");
    }

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
        }

        final String option = args[0];

        if ("reload".equals(option)) {
        	LobbyPlugin.getPlugin().getConfiguration().load();
            return ChatUtil.sendMessage(sender, "&aConfig save!");
        }

        return ChatUtil.sendMessage(sender, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
    }
}