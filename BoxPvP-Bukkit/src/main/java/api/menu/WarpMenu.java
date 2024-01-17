package api.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;

public class WarpMenu {
 
    public static InventoryView show(final Player p) {
        final ItemBuilder ciemne = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setTitle(ChatUtil.translateHexColorCodes("&8[&AI&8]"));
        final ItemBuilder zielone = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setTitle(ChatUtil.translateHexColorCodes("&8[&AI&8]"));

        final ItemBuilder strefa = new ItemBuilder(Material.SHIELD)
                .setTitle(ChatUtil.translateHexColorCodes("&#ebff00&lS&#e5ff00&lt&#dfff00&lr&#d9ff00&le&#d2ff00&lf&#ccff00&la &#c6ff00&lB&#c0ff00&le&#baff01&lz&#b4ff01&lp&#aeff01&li&#a8ff01&le&#a1ff01&lc&#9bff01&lz&#95ff01&ln&#8fff01&la"))
                .addLore("")
                .addLore(ChatUtil.translateHexColorCodes("&#fff3b4&lK&#ffefaf&ll&#ffebaa&li&#ffe7a5&lk&#ffe3a0&ln&#ffdf9b&li&#ffdb96&lj &#ffd792&la&#ffd38d&lb&#ffcf88&ly &#ffcb83&ls&#ffc77e&li&#ffc379&le &#ffbf74&lp&#ffbb6f&lr&#ffb76a&lz&#ffb365&le&#ffaf60&lt&#ffab5b&le&#ffa756&ll&#ffa351&le&#ff9f4d&lp&#ff9b48&lo&#ff9743&lr&#ff933e&lt&#ff8f39&lo&#ff8b34&lw&#ff872f&la&#ff832a&lc"));

        final ItemBuilder spawn = new ItemBuilder(Material.DISPENSER)
                .setTitle(ChatUtil.translateHexColorCodes("&#d52cff&lS&#c028e6&lP&#ab24cd&lA&#961fb4&lW&#811b9b&lN"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#fff3b4&lK&#ffefaf&ll&#ffebaa&li&#ffe7a5&lk&#ffe3a0&ln&#ffdf9b&li&#ffdb96&lj &#ffd792&la&#ffd38d&lb&#ffcf88&ly &#ffcb83&ls&#ffc77e&li&#ffc379&le &#ffbf74&lp&#ffbb6f&lr&#ffb76a&lz&#ffb365&le&#ffaf60&lt&#ffab5b&le&#ffa756&ll&#ffa351&le&#ff9f4d&lp&#ff9b48&lo&#ff9743&lr&#ff933e&lt&#ff8f39&lo&#ff8b34&lw&#ff872f&la&#ff832a&lc"));


        final ItemBuilder strefapremium = new ItemBuilder(Material.GOLD_BLOCK)
                .setTitle(ChatUtil.translateHexColorCodes("&#ff0000&lS&#fd0f07&lt&#fc1d0e&lr&#fa2c15&le&#f93a1c&lf&#f74923&la &#f6582a&lP&#f46631&lr&#f27538&le&#f1833f&lm&#ef9246&li&#eea04d&lu&#ecaf54&lm"))
                .addLore("")
                .addLore(ChatUtil.translateHexColorCodes("&#fff3b4&lK&#ffefaf&ll&#ffebaa&li&#ffe7a5&lk&#ffe3a0&ln&#ffdf9b&li&#ffdb96&lj &#ffd792&la&#ffd38d&lb&#ffcf88&ly &#ffcb83&ls&#ffc77e&li&#ffc379&le &#ffbf74&lp&#ffbb6f&lr&#ffb76a&lz&#ffb365&le&#ffaf60&lt&#ffab5b&le&#ffa756&ll&#ffa351&le&#ff9f4d&lp&#ff9b48&lo&#ff9743&lr&#ff933e&lt&#ff8f39&lo&#ff8b34&lw&#ff872f&la&#ff832a&lc"));

   
        final ItemBuilder pvp = new ItemBuilder(Material.NETHERITE_SWORD)
                .setTitle(ChatUtil.translateHexColorCodes("&#ff7c1d&lS&#f6711d&lt&#ec661d&lr&#e35b1d&le&#da511d&lf&#d0461c&la &#c73b1c&lP&#bd301c&lV&#b4251c&lP"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#fff3b4&lK&#ffefaf&ll&#ffebaa&li&#ffe7a5&lk&#ffe3a0&ln&#ffdf9b&li&#ffdb96&lj &#ffd792&la&#ffd38d&lb&#ffcf88&ly &#ffcb83&ls&#ffc77e&li&#ffc379&le &#ffbf74&lp&#ffbb6f&lr&#ffb76a&lz&#ffb365&le&#ffaf60&lt&#ffab5b&le&#ffa756&ll&#ffa351&le&#ff9f4d&lp&#ff9b48&lo&#ff9743&lr&#ff933e&lt&#ff8f39&lo&#ff8b34&lw&#ff872f&la&#ff832a&lc"));

        final ItemBuilder afk = new ItemBuilder(Material.CLOCK)
                .setTitle(ChatUtil.translateHexColorCodes("&#47fb29&lS&#41fb44&lT&#3cfc5e&lR&#36fc79&lE&#31fc93&lF&#2bfcae&lA &#25fdc8&lA&#20fde3&lF&#1afdfd&lK"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#fff3b4&lK&#ffefaf&ll&#ffebaa&li&#ffe7a5&lk&#ffe3a0&ln&#ffdf9b&li&#ffdb96&lj &#ffd792&la&#ffd38d&lb&#ffcf88&ly &#ffcb83&ls&#ffc77e&li&#ffc379&le &#ffbf74&lp&#ffbb6f&lr&#ffb76a&lz&#ffb365&le&#ffaf60&lt&#ffab5b&le&#ffa756&ll&#ffa351&le&#ff9f4d&lp&#ff9b48&lo&#ff9743&lr&#ff933e&lt&#ff8f39&lo&#ff8b34&lw&#ff872f&la&#ff832a&lc"));


   
        final Inventory inv = Bukkit.createInventory(null, 27, ChatUtil.translateHexColorCodes("&7WARPY"));
        inv.setItem(inv.getSize() - 27, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 26, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 25, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 24, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 23, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 22, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 21, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 20, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 19, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 18, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 17, strefapremium.ToItemStack());
        inv.setItem(inv.getSize() - 16, strefa.ToItemStack());
        inv.setItem(inv.getSize() - 15, zielone.ToItemStack());
        inv.setItem(inv.getSize() - 14, afk.ToItemStack());
        inv.setItem(inv.getSize() - 13, zielone.ToItemStack());
        inv.setItem(inv.getSize() - 12, pvp.ToItemStack());
        inv.setItem(inv.getSize() - 11, spawn.ToItemStack());
        inv.setItem(inv.getSize() - 10, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 9, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 8, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 7, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 6, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 5, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 4, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 3, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 2, ciemne.ToItemStack());
        inv.setItem(inv.getSize() - 1, ciemne.ToItemStack());
        return p.openInventory(inv);
    }
}
