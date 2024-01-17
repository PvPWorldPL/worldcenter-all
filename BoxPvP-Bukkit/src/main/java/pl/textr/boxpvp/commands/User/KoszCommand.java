package pl.textr.boxpvp.commands.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "kosz", description = "Otwiera kosz", usage = "/kosz", permission = "core.cmd.user")
public class KoszCommand extends PlayerCommandExecutor {

  
  public boolean onCommand(Player p, String[] args) {
    Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, ChatUtil.fixColor("&c&lKosz"));
    p.openInventory(inv);
    return false;
  }
}
