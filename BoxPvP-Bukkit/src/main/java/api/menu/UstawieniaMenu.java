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
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, ChatUtil.translateHexColorCodes("&#2cfe4e&lU&#43f64b&lS&#5aef49&lT&#72e746&lA&#89df43&lW&#a0d841&lI&#b7d03e&lE&#cfc83b&lN&#e6c139&lI&#fdb936&lA"));
        
    	   final UserProfile u = UserAccountManager.getUser(p);
    
        /// #FF0404 #EA7737
      
        final ItemBuilder monety = new ItemBuilder(Material.BUBBLE_CORAL)
                .setTitle(ChatUtil.translateHexColorCodes("&#fb0f00&lP&#ee1e0d&lr&#e02d19&lz&#d33c26&le&#c54b32&lr&#b8593f&la&#aa684b&lb&#9d7758&li&#908665&la&#829571&ln&#75a47e&li&#67b38a&le &#5ac297&lM&#4cd0a3&lo&#3fdfb0&ln&#31eebc&le&#24fdc9&lt"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Status: " + (u.isPrzerabianieMonet() ? "&aWłączone" : "&cWyłączone")))
                .addLore(ChatUtil.translateHexColorCodes(""));
        final ItemBuilder kassa = new ItemBuilder(Material.FISHING_ROD)
                .setTitle(ChatUtil.translateHexColorCodes("&#fb0f00&lP&#ed1f0d&lr&#de2f1b&lz&#d03f28&le&#c24e36&lr&#b35e43&la&#a56e50&lb&#977e5e&li&#888e6b&la&#7a9e79&ln&#6cae86&li&#5dbe93&le &#4fcda1&lk&#41ddae&la&#32edbc&ls&#24fdc9&ly"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Status: " + (u.isPrzerabianieKasy() ? "&aWłączone" : "&cWyłączone")))
                .addLore(ChatUtil.translateHexColorCodes(""));
            
        final ItemBuilder bloki = new ItemBuilder(Material.STONE)
                .setTitle(ChatUtil.translateHexColorCodes("&#89fb18&lP&#85f723&lr&#81f42f&lz&#7ef03a&le&#7aed45&lr&#76e950&la&#72e55c&lb&#6ee267&li&#6ade72&la&#67db7e&ln&#63d789&li&#5fd494&le &#5bd0a0&lB&#57ccab&ll&#53c9b6&lo&#50c5c1&lk&#4cc2cd&lo&#48bed8&lw"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Status: " + (u.isPrzerabianieBlokow() ? "&aWłączone" : "&cWyłączone")))
                .addLore(ChatUtil.translateHexColorCodes(""));
               
        
      ItemBuilder.fillGui(inv);
        
        inv.setItem(inv.getSize() - 30, kassa.ToItemStack());

        inv.setItem(inv.getSize() - 14, monety.ToItemStack());
        inv.setItem(inv.getSize() - 34, bloki.ToItemStack());

       p.openInventory(inv);
    }
}
