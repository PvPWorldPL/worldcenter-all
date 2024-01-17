package pl.textr.boxpvp.commands.HeadAdmin;

import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "core", description = "Informacje o Core", usage = "/core <reload>", permission = "core.cmd.dev")
public class ConfigCommand extends BaseCommand {

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }

        final String option = args[0];

        if ("reload".equals(option)) {
        	Main.getPlugin().getConfiguration().load();
            return ChatUtil.sendMessage(sender, "&aConfig save!");
        }

        return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
    }
}