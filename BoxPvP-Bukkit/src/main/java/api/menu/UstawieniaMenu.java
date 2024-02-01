package api.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;

public class UstawieniaMenu {
    public static void show(final Player p) {
        final Inventory inv = Bukkit.createInventory(p, 45, ChatUtil.translateHexColorCodes("&#2cfe4e&lU&#43f64b&lS&#5aef49&lT&#72e746&lA&#89df43&lW&#a0d841&lI&#b7d03e&lE&#cfc83b&lN&#e6c139&lI&#fdb936&lA"));
        
    	   final UserProfile u = UserAccountManager.getUser(p);
    
        /// #FF0404 #EA7737
      
        final ItemBuilder monety = new ItemBuilder(Material.SUNFLOWER)
                .setTitle(ChatUtil.translateHexColorCodes("kasa"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Status: " + (u.isPrzerabianieKasy() ? "&aWłączone" : "&cWyłączone")))
                .addLore(ChatUtil.translateHexColorCodes(""));
        final ItemBuilder kassa = new ItemBuilder(Material.MAGMA_CREAM)
                .setTitle(ChatUtil.translateHexColorCodes("monety"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Status: " + (u.isPrzerabianieMonet() ? "&aWłączone" : "&cWyłączone")))
                .addLore(ChatUtil.translateHexColorCodes(""));
            
        final ItemBuilder bloki = new ItemBuilder(Material.DEEPSLATE_EMERALD_ORE)
                .setTitle(ChatUtil.translateHexColorCodes("bloki"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Status: " + (u.isPrzerabianieBlokow() ? "&aWłączone" : "&cWyłączone")))
                .addLore(ChatUtil.translateHexColorCodes(""));
               
        
      ItemBuilder.fillGui(inv);
        
        inv.setItem(20, bloki.ToItemStack());
        inv.setItem(24, monety.ToItemStack());
        inv.setItem(22, kassa.ToItemStack());




        p.openInventory(inv);
    }
}
