package api.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;

public class RanksMenu {
	  public static void show(final Player p) {
		   final Inventory inv = Bukkit.createInventory(p, 54, ChatUtil.translateHexColorCodes("&7RANGI"));
		     
        /// #FF0404 #EA7737
       
        final ItemBuilder SVIP = new ItemBuilder(Material.BOOK)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#effb0e&lS&#effb13&lV&#f0fb18&lI&#f0fb1d&lP &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Komendy"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#fb1b0ek&#f73218i&#f24923t &#ee602db&#ea7737y"))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#f82a15h&#f15126a&#ea7737t &8- &7zaklada item na glowe"))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#fb1b0eg&#f73218a&#f24923m&#ee602dm&#ea7737a &8- &7widzenie w ciemnosci"))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#fc170dr&#f82a15e&#f53e1ep&#f15126a&#ee642fi&#ea7737r &8- &7Naprawia item w lapce"))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#fd1009e&#fb1b0en&#f92713d&#f73218e&#f53e1er&#f24923c&#f05528h&#ee602de&#ec6c32s&#ea7737t &8- &7przenosny enderchest"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Przywileje"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep do  &#ff0404k&#fe0b07o&#fc120al&#fb1a0eo&#fa2111r&#f82814o&#f72f17w&#f6361ae&#f53e1eg&#f34521o &#f24c24p&#f15327i&#ef5a2as&#ee612da&#ed6931n&#eb7034i&#ea7737a"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dodatkowy  &#ff0404S&#f82a15l&#f15126o&#ea7737t&7"))

                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Kupisz tutaj: &#ff0404M&#fd0d08C&#fc170cY&#fa2010O&#f82a15U&#f63319.&#f53d1dP&#f34621L"))
                .addLore(ChatUtil.translateHexColorCodes("&7Czas trwania: &#ff04041 &#fc170de&#f82a15d&#f53e1ey&#f15126c&#ee642fj&#ea7737a"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#effb0e&lS&#effb13&lV&#f0fb18&lI&#f0fb1d&lP &8|------]"));

        final ItemBuilder SPONSOR = new ItemBuilder(Material.BOOK)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#066aff&lS&#0664f1&lP&#055ee3&lO&#0559d5&lN&#0553c6&lS&#044db8&lO&#0447aa&lR &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Komendy"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#fd1009k&#fb1b0ei&#f92713t &#f73218s&#f53e1ep&#f24923o&#f05528n&#ee602ds&#ec6c32o&#ea7737r"))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#f82a15h&#f15126a&#ea7737t &8- &7zaklada item na glowe"))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#fc170dr&#f82a15e&#f53e1ep&#f15126a&#ee642fi&#ea7737r &8- &7Naprawia item w lapce"))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#fd1009e&#fb1b0en&#f92713d&#f73218e&#f53e1er&#f24923c&#f05528h&#ee602de&#ec6c32s&#ea7737t &8- &7przenosny enderchest"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Przywileje"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep do  &#ff0404k&#fe0b07o&#fc120al&#fb1a0eo&#fa2111r&#f82814o&#f72f17w&#f6361ae&#f53e1eg&#f34521o &#f24c24p&#f15327i&#ef5a2as&#ee612da&#ed6931n&#eb7034i&#ea7737a"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dodatkowy  &#ff0404S&#f82a15l&#f15126o&#ea7737t"))

                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Kupisz tutaj: &#ff0404M&#fd0d08C&#fc170cY&#fa2010O&#f82a15U&#f63319.&#f53d1dP&#f34621L"))
                .addLore(ChatUtil.translateHexColorCodes("&7Czas trwania: &#ff04041 &#fc170de&#f82a15d&#f53e1ey&#f15126c&#ee642fj&#ea7737a"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#066aff&lS&#0664f1&lP&#055ee3&lO&#0559d5&lN&#0553c6&lS&#044db8&lO&#0447aa&lR &8|------]"));


        final ItemBuilder VIP = new ItemBuilder(Material.BOOK)
                .setTitle(ChatUtil.translateHexColorCodes("&8[------| &#ffda1b&lV&#ffda1b&lI&#ffda1b&lP &8|------]"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Komendy"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#fc170dk&#f82a15i&#f53e1et &#f15126v&#ee642fi&#ea7737p"))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#f82a15h&#f15126a&#ea7737t &8- &7zaklada item na glowe"))
                .addLore(ChatUtil.translateHexColorCodes("&#ff0404/&#fc170dr&#f82a15e&#f53e1ep&#f15126a&#ee642fi&#ea7737r &8- &7Naprawia item w lapce"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Przywileje"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Dostep do  &#ff0404k&#fe0b07o&#fc120al&#fb1a0eo&#fa2111r&#f82814o&#f72f17w&#f6361ae&#f53e1eg&#f34521o &#f24c24p&#f15327i&#ef5a2as&#ee612da&#ed6931n&#eb7034i&#ea7737a"))
                .addLore(ChatUtil.translateHexColorCodes("&7Dodatkowy  &#ff0404S&#f82a15l&#f15126o&#ea7737t"))

                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&7Kupisz tutaj: &#ff0404M&#fd0d08C&#fc170cY&#fa2010O&#f82a15U&#f63319.&#f53d1dP&#f34621L"))
                .addLore(ChatUtil.translateHexColorCodes("&7Czas trwania: &#ff04041 &#fc170de&#f82a15d&#f53e1ey&#f15126c&#ee642fj&#ea7737a"))
                .addLore(ChatUtil.translateHexColorCodes(""))
                .addLore(ChatUtil.translateHexColorCodes("&8[------| &#ffda1b&lV&#ffda1b&lI&#ffda1b&lP &8|------]"));


        ItemBuilder.fillGui(inv);
        inv.setItem(inv.getSize() - 34, VIP.ToItemStack());

        inv.setItem(inv.getSize() - 30, SVIP.ToItemStack());

        inv.setItem(inv.getSize() - 14, SPONSOR.ToItemStack());
     
        p.openInventory(inv);
    }
}
