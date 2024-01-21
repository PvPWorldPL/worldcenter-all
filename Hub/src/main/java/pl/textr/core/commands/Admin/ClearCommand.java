package pl.textr.core.commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.textr.core.commands.Api.PlayerCommand;
import pl.textr.core.utils.other.ChatUtil;

public class ClearCommand extends PlayerCommand
{
    public ClearCommand() {
        super("clearinv", "Czyszczenie ekwipunku graczy", "/clearinv [gracz]", "core.cmd.helper", "clear", "clearinventory", "ci");
    }
    
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
        if (!p.hasPermission("core.cmd.admin")) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie masz dostepu!");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage(p, "&8[&c&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
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
