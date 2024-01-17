package pl.textr.boxpvp.commands.HeadAdmin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "kickall", description = "Wyrzucanie wszystkich graczy z serwera", usage = "/kickall", permission = "core.cmd.admin")
public class KickAllCommand extends BaseCommand {

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        final String kickMessage = "\n&7Zostales wyrzucony z proxy przez &c" + sender.getName() + "\n&4Powod: &c" + StringUtils.join(args, " ") + "\n";
        
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (!p.hasPermission("core.cmd.mod")) {

                p.kickPlayer(ChatUtil.fixColor(kickMessage));
            }
        }
        return ChatUtil.sendMessage(sender, "&aWyrzucono wszystkich graczy z serwera!");
    }
}
