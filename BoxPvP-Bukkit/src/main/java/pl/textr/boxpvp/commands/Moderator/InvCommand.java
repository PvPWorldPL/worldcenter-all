package pl.textr.boxpvp.commands.Moderator;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "inv", description = "Zarządzanie ekwipunkiem gracza", usage = "/inv <gracz>", permission = "core.cmd.moderator", aliases = {"invsee"})
public class InvCommand extends BaseCommand {
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        final Player player = (Player)sender;
        if (args.length == 0) {
            String msg = Main.getPlugin().getConfiguration().usage;
            msg = msg.replace("{USAGE}", this.getUsage());
            return ChatUtil.sendMessage(sender, msg);
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
        
        }
        if (args.length > 0) {
            final Player other = Bukkit.getPlayer(args[0]);
            if (other != null) {
                player.openInventory(other.getInventory());
            }
        }
        return false;
    }
}
