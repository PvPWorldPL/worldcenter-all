package api.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import api.data.Clans;
import api.managers.ClanManager;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;

public class ColorsTagMenu {
    public static InventoryView show(final Player p) {
 
    	
    	  final Clans g = ClanManager.getGuild(p);
    	  
    	
    	  
        final ItemBuilder kolor1 = new ItemBuilder(Material.GRAY_WOOL)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#414040DARK_GRAY &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Podgląd"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#414040&l" + g.getTag()  + " &7" + p.getName()) )
                .addLore(ChatUtil.translateHexColorCodes(""))   
                .addLore(ChatUtil.translateHexColorCodes("&7Wymagana ranga &9SPONSOR"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep: " + (p.hasPermission("core.color.sponsor") ? "&atak" : "&cnie")))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby aktywować"))
                 
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#414040DARK_GRAY &8|------]"));

     
        final ItemBuilder kolor2 = new ItemBuilder(Material.GREEN_WOOL)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#208916DARK_GREEN &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Podgląd"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#208916&l" + g.getTag()  + " &7" + p.getName()) )
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Wymagana ranga &9SPONSOR"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep: " + (p.hasPermission("core.color.sponsor") ? "&atak" : "&cnie")))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby aktywować"))
                 
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#208916DARK_GREEN &8|------]"));

        final ItemBuilder kolor3 = new ItemBuilder(Material.CYAN_WOOL)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#1D8AB9DARK_AQUA &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Podgląd"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#1D8AB9&l" + g.getTag()  + " &7" + p.getName()) )
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Wymagana ranga &9SPONSOR"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep: " + (p.hasPermission("core.color.sponsor") ? "&atak" : "&cnie")))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby aktywować"))
                   
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#1D8AB9DARK_AQUA &8|------]"));

        
        final ItemBuilder kolor4 = new ItemBuilder(Material.RED_WOOL)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#9B0000DARK_RED &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Podgląd"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#9B0000&L" + g.getTag()  + " &7" + p.getName()) )
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Wymagana ranga &9SPONSOR"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep: " + (p.hasPermission("core.color.sponsor") ? "&atak" : "&cnie")))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby aktywować"))                   
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#9B0000DARK_RED &8|------]"));

        final ItemBuilder kolor5 = new ItemBuilder(Material.PURPLE_WOOL)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#931095DARK_PURPLE &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Podgląd"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#931095&l" + g.getTag()  + " &7" + p.getName()) )
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Wymagana ranga &6VIP | &eSVIP"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep: " + (p.hasPermission("core.color.vip") ? "&atak" : "&cnie")))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby aktywować"))       
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#931095DARK_PURPLE &8|------]"));


        final ItemBuilder kolor6 = new ItemBuilder(Material.LIGHT_BLUE_WOOL)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#06E1FFLIGHT_BLUE &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Podgląd"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#06E1FF&l" + g.getTag()  + " &7" + p.getName()) )
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Wymagana ranga &6VIP | &eSVIP"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep: " + (p.hasPermission("core.color.vip") ? "&atak" : "&cnie")))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby aktywować"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#06E1FFLIGHT_BLUE &8|------]"));

        
        final ItemBuilder kolor7 = new ItemBuilder(Material.ORANGE_WOOL)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#FF9B06ORANGE &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Podgląd"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#FF9B06&l" + g.getTag()  + " &7" + p.getName()) )
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Wymagana ranga &6VIP | &eSVIP"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep: " + (p.hasPermission("core.color.vip") ? "&atak" : "&cnie")))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby aktywować"))
          
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#FF9B06ORANGE &8|------]"));

        
        final ItemBuilder kolor8 = new ItemBuilder(Material.LIME_WOOL)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#00FF00LIGHT_GREEN &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Podgląd"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#00FF00&l" + g.getTag()  + " &7" + p.getName()) )
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Wymagana ranga &fPlayer"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep: " + (p.hasPermission("core.color.player") ? "&atak" : "&cnie")))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby aktywować"))             
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#00FF00LIGHT_GREEN &8|------]"));

        
        final ItemBuilder kolor9 = new ItemBuilder(Material.PINK_WOOL)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#FF00FFLIGHT_PURPLE &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Podgląd"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#FF00FF&l" + g.getTag()  + " &7" + p.getName()) )
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Wymagana ranga &fPlayer"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep: " + (p.hasPermission("core.color.player") ? "&atak" : "&cnie")))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby aktywować"))
              
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#FF00FFLIGHT_PURPLE &8|------]"));

        
        final Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.translateHexColorCodes("&#66fb0b&lK&#81fa0f&lO&#9df813&lL&#b8f718&lO&#d4f61c&lR&#eff520&lO&#fdf322&lW&#fef123&lE &#feef23&lT&#feed24&lA&#ffeb24&lG&#ffe925&lI"));
        inv.setItem(inv.getSize() - 9, kolor9.ToItemStack());
        inv.setItem(inv.getSize() - 8, kolor8.ToItemStack());
        inv.setItem(inv.getSize() - 7, kolor7.ToItemStack());
        inv.setItem(inv.getSize() - 6, kolor6.ToItemStack());
        inv.setItem(inv.getSize() - 5, kolor5.ToItemStack());
        inv.setItem(inv.getSize() - 4, kolor4.ToItemStack());
        inv.setItem(inv.getSize() - 3, kolor3.ToItemStack());
        inv.setItem(inv.getSize() - 2, kolor2.ToItemStack());
        inv.setItem(inv.getSize() - 1, kolor1.ToItemStack());
        return p.openInventory(inv);
    }
}
