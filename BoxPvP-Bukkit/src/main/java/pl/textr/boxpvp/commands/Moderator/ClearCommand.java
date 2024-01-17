package pl.textr.boxpvp.commands.Moderator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "clearinv", description = "Czyszczenie ekwipunku graczy", usage = "/clearinv [gracz]", permission = "core.cmd.moderator", aliases = {"clear", "clearinventory", "ci"})
public class ClearCommand extends PlayerCommandExecutor {

	
	
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            p.getInventory().clear();
            return ChatUtil.sendMessage(p, "&7Wyczyszczono &fekwipunek");
        }
   
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&5&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!"));
        }
        o.getInventory().clear();
        o.getInventory().setHelmet(null);
        o.getInventory().setChestplate(null);
        o.getInventory().setLeggings(null);
        o.getInventory().setBoots(null);
        ChatUtil.sendMessage(o, "&7Twoj ekwipunek zostal wyczyszczony przez &f" + p.getName());
        return ChatUtil.sendMessage(p, "&7Wyczyszczono ekwipunek dla gracza &f" + o.getName());
    }

	
}
