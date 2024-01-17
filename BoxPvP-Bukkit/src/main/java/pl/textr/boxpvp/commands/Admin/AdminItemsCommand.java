package pl.textr.boxpvp.commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.managers.ItemsManager;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;

@CommandInfo(name = "adminitems", description = "adminitems", usage = "/adminitems", permission = "core.cmd.admin")
public class AdminItemsCommand extends PlayerCommandExecutor {


  
  public boolean onCommand(Player p, String[] args) {
    show(p);
    return false;
  }
  
  public void show(Player p) {
	    Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, ChatUtil.fixColor("&fItemy administratora"));
	    ItemBuilder.fillGui(inv);
	    inv.addItem(new ItemStack(ItemsManager.getMoneta1(64)));
	    inv.addItem(new ItemStack(ItemsManager.getMoneta2(64)));
	    inv.addItem(new ItemStack( ItemsManager.getMoneta3(64)));
	    inv.addItem(new ItemStack(ItemsManager.getMoneta4(64)));
	    inv.addItem(new ItemStack(ItemsManager.getMoneta5(64)));
	    inv.addItem(new ItemStack(ItemsManager.getMoneta6(64)));
	    inv.addItem(new ItemStack(ItemsManager.getMoneta7(64)));
	    inv.addItem(new ItemStack(ItemsManager.getMoneta8(64)));
	    
	    inv.addItem(new ItemStack(ItemsManager.getVoucherVIP(64)));
	    inv.addItem(new ItemStack(ItemsManager.getUsuwacz(64)));
	    inv.addItem(new ItemStack(ItemsManager.getodlamek(64)));
	    inv.addItem(new ItemStack(ItemsManager.getLuckyBlock(64)));
	    
	    
	    p.openInventory(inv);
	  }
}