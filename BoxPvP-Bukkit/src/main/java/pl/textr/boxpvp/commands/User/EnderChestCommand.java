package pl.textr.boxpvp.commands.User;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.ChatUtil;



@CommandInfo(name = "ec", description = "Otwiera Ender Chest", usage = "/enderchest", permission = "core.cmd.user", aliases = { "enderchest" })
public class EnderChestCommand extends PlayerCommandExecutor {


    @Override
    public boolean onCommand(final Player p, final String[] args) {
    	 if (!p.hasPermission("core.premium")) {
           	ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&cTa czynnosc jest dostepna tylko dla rang &6premium"));
           	return true;
         }
    	p.openInventory(p.getEnderChest());
        return true;
    }
}
