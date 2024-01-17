package api.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;

public class AnvilIMenu {
	
    public static InventoryView show(final Player p) {
        final Inventory inv = Bukkit.createInventory(null, 54, ChatUtil.fixColor("&eKowadlo"));
        final ItemBuilder repair = new ItemBuilder(Material.EXPERIENCE_BOTTLE)
        		.setTitle(ChatUtil.fixColor("&eNapraw trzymany przedmiot"))
        	
        		.addLore(ChatUtil.fixColor("&aKliknij aby naprawic trzymany przedmiot!"));
        final ItemBuilder wymiana = new ItemBuilder(Material.SMITHING_TABLE)
        		.setTitle(ChatUtil.fixColor("&eWymien netherytowy przedmiot"))
        		.addLore(ChatUtil.fixColor("&aKliknij aby wymienic!"));
        ItemBuilder.fillGui(inv);
        inv.setItem(21, repair.ToItemStack());
        inv.setItem(23, wymiana.ToItemStack());
        return p.openInventory(inv);
    }
}
