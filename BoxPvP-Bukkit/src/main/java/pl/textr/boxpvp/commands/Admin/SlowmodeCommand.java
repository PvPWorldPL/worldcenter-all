package pl.textr.boxpvp.commands.Admin;

import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.DataUtil;

@CommandInfo(name = "slowmode", description = "Ustawia tryb powolnego chatu", usage = "/slowmode <czas>", permission = "core.cmd.moderator", aliases = {"slow", "smode"})
public class SlowmodeCommand extends BaseCommand {
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length != 1) {
            String msg = Main.getPlugin().getConfiguration().errordonthavepermission();
            msg = msg.replace("{USAGE}", this.getUsage());
            return ChatUtil.sendMessage(sender, msg);
        }
        if (!ChatUtil.isInteger(args[0])) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cPodana wartosc nie jest liczba!");
        }
        final int slowmode = Main.getPlugin().getConfiguration().chatslowmode = Integer.parseInt(args[0]);
        Main.getPlugin().getConfiguration().save();
        return ChatUtil.sendMessage(sender, "&7Pomyslnie ustawiono slowmode czatu na &f" + DataUtil.secondsToString(slowmode));
    }
}
