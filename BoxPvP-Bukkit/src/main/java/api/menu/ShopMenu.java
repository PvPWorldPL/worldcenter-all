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

public class ShopMenu
{
    public static void menu(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, ChatUtil.translateHexColorCodes("&eSklep &7(menu)"));
        final ItemBuilder sklepzaczas = new ItemBuilder(Material.CLOCK).setTitle(ChatUtil.translateHexColorCodes("&#d34fc9&lSklep za czas")).addLore(ChatUtil.fixColor("&aKliknij aby przejsc"));
 
        final ItemBuilder sklep = new ItemBuilder(Material.SUNFLOWER).setTitle(ChatUtil.translateHexColorCodes("&#d34fc9&lSklep za kase")).addLore(ChatUtil.fixColor("&aKliknij aby przejsc"));
        final ItemBuilder stankonta = new ItemBuilder(Material.BOOK).setTitle(ChatUtil.translateHexColorCodes("&fInformacja")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Za kazde &f5 minut &7gry")).addLore(ChatUtil.fixColor("&7Otrzymujesz &f1 &7monete czasu")).addLore(ChatUtil.fixColor(""));
        ItemBuilder.fillGui(inv);
        inv.setItem(20, sklep.ToItemStack());
    
        inv.setItem(24, sklepzaczas.ToItemStack());
        inv.setItem(40, stankonta.ToItemStack());
        p.openInventory(inv);
    }
    
    public static void show(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, ChatUtil.translateHexColorCodes("&eSklep &7(sklep za czas)"));
        final UserProfile user = UserAccountManager.getUser(p);
       
        final ItemBuilder shulkerbox = new ItemBuilder(Material.SHULKER_BOX)
        .setTitle(ChatUtil.translateHexColorCodes("&fShulkerowa skrzynia"))
        .addLore(ChatUtil.translateHexColorCodes("&7Koszt: &f6&7 monet czasu"))
        .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby zakupic!"));
        
        final ItemBuilder klucz = new ItemBuilder(Material.TRIPWIRE_HOOK)
        .setTitle(ChatUtil.translateHexColorCodes("&7Klucz do skrzyni &b&lZWYKLEJ"))
        .addLore(ChatUtil.translateHexColorCodes("&7Koszt: &f200&7 monet czasu"))
        .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby zakupic!"));
        
        final ItemBuilder klucz2 = new ItemBuilder(Material.TRIPWIRE_HOOK)
        .setTitle(ChatUtil.translateHexColorCodes("&7Klucz do skrzyni &d&lEPICKIEJ"))
        .addLore(ChatUtil.translateHexColorCodes("&7Koszt: &f400&7 monet czasu"))
        .addLore(ChatUtil.translateHexColorCodes("  &aKliknij aby zakupic!"));
        
        
        final ItemBuilder klucz3 = new ItemBuilder(Material.TRIPWIRE_HOOK)
        .setTitle(ChatUtil.translateHexColorCodes("&7Klucz do skrzyni &a&lRZADKIEJ"))
        .addLore(ChatUtil.translateHexColorCodes("&7Koszt: &f600&7 monet czasu"))
        .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby zakupic!"));
  
        final ItemBuilder moneta1 = new ItemBuilder(Material.GHAST_TEAR, 10)
        .setTitle(ChatUtil.translateHexColorCodes("&#00F3FF&lK&#00E9FF&lr&#00DFFF&lo&#00D5FF&lp&#00CBFF&ll&#00C2FF&la &#00B8FF&lb&#00AEFF&lo&#00A4FF&lg&#009AFF&la"))
        .addLore(ChatUtil.translateHexColorCodes("&7Koszt: &e1000&7 monet czasu"))
        .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby zakupic!"));
        
        final ItemBuilder moneta4 = new ItemBuilder(Material.WITHER_ROSE, 10)
        .setTitle(ChatUtil.translateHexColorCodes("&#FF7400&lR&#F87405&ló&#F1750B&lz&#EB7510&la &#E47615&lU&#DD761B&lp&#D67720&la&#CF7726&ld&#C8782B&lł&#C27830&le&#BB7936&lg&#B4793B&lo"))
        .addLore(ChatUtil.translateHexColorCodes("&7Koszt: &e5000&7 monet czasu"))
        .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby zakupic!"));
                
        
        final ItemBuilder moneta2 = new ItemBuilder(Material.LEAD, 5)
        .setTitle(ChatUtil.translateHexColorCodes("&#DE00FF&lP&#CC01ED&lo&#BA02DA&ls&#A903C8&lk&#9704B5&lr&#8505A3&la&#730591&lm&#61067E&li&#50076C&la&#3E0859&lc&#2C0947&lz"))
        .addLore(ChatUtil.translateHexColorCodes("&7Koszt: &e3000&7 monet czasu"))
        .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby zakupic!"));
        
        
       
        final ItemBuilder stankonta = new ItemBuilder(Material.PLAYER_HEAD)
        .setTitle(ChatUtil.translateHexColorCodes("&7Stan konta")).addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.translateHexColorCodes("&7Monety czasu: &f" + user.getMoney()));
        
        ItemBuilder.fillGui(inv);
        inv.setItem(19, shulkerbox.ToItemStack());
        inv.setItem(20, klucz.ToItemStack());
        inv.setItem(21, klucz2.ToItemStack());
        inv.setItem(22, klucz3.ToItemStack());;
        inv.setItem(24, moneta1.ToItemStack());
        inv.setItem(23, moneta4.ToItemStack());
        
        inv.setItem(25, moneta2.ToItemStack());
        inv.setItem(40, stankonta.ToItemStack());
        p.openInventory(inv);
    }
    
    public static void show2(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, ChatUtil.fixColor("&eSklep &7(sklep za kase)"));
        final UserProfile user = UserAccountManager.getUser(p);
        
        final ItemBuilder moneta1 = new ItemBuilder(Material.GHAST_TEAR, 1)
        .setTitle(ChatUtil.translateHexColorCodes("&#00F3FF&lK&#00E9FF&lr&#00DFFF&lo&#00D5FF&lp&#00CBFF&ll&#00C2FF&la &#00B8FF&lb&#00AEFF&lo&#00A4FF&lg&#009AFF&la"))
        .addLore(ChatUtil.fixColor("&7Kwota zakupu: &a$1"))
        .addLore(ChatUtil.fixColor("&7Kwota sprzedazy: &c$1"))
        .addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.fixColor("&aKliknij LPM aby zakupic!"))
        .addLore(ChatUtil.fixColor("&aKliknij PPM aby sprzedac!"))
        .addLore(ChatUtil.fixColor("&aKliknij SHIFT + LPM aby sprzedac wszystko!"));

        ItemBuilder moneta2 = new ItemBuilder(Material.LEAD, 1)
        .setTitle(ChatUtil.translateHexColorCodes("&#DE00FF&lP&#CC01ED&lo&#BA02DA&ls&#A903C8&lk&#9704B5&lr&#8505A3&la&#730591&lm&#61067E&li&#50076C&la&#3E0859&lc&#2C0947&lz"))
        .addLore(ChatUtil.fixColor("&7Kwota zakupu: &a$64"))
        .addLore(ChatUtil.fixColor("&7Kwota sprzedazy: &c$64"))
        .addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.fixColor("&aKliknij LPM aby zakupic!"))
        .addLore(ChatUtil.fixColor("&aKliknij PPM aby sprzedac!"))
        .addLore(ChatUtil.fixColor("&aKliknij SHIFT + LPM aby sprzedac wszystko!"));
        
        final ItemBuilder moneta3 = new ItemBuilder(Material.CRIMSON_FUNGUS, 1)
        .setTitle(ChatUtil.translateHexColorCodes("&#C03450&lR&#BB3350&lu&#B53250&lb&#B03151&li&#AA3051&ln&#A52F51&lk&#A02E51&lo&#9A2D52&lw&#952C52&ly &#902B52&lg&#8A2A52&lr&#852953&lz&#7F2853&ly&#7A2753&lb"))
        .addLore(ChatUtil.fixColor("&7Kwota zakupu: &a$4.1k"))
        .addLore(ChatUtil.fixColor("&7Kwota sprzedazy: &c$4.1k"))
        .addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.fixColor("&aKliknij LPM aby zakupic!"))
        .addLore(ChatUtil.fixColor("&aKliknij PPM aby sprzedac!"))
        .addLore(ChatUtil.fixColor("&aKliknij SHIFT + LPM aby sprzedac wszystko!"));
        
        final ItemBuilder moneta4 = new ItemBuilder(Material.WITHER_ROSE, 1)
        .setTitle(ChatUtil.translateHexColorCodes("&#FF7400&lR&#F87405&ló&#F1750B&lz&#EB7510&la &#E47615&lU&#DD761B&lp&#D67720&la&#CF7726&ld&#C8782B&lł&#C27830&le&#BB7936&lg&#B4793B&lo"))
        .addLore(ChatUtil.fixColor("&7Kwota zakupu: &a$262.14M"))
        .addLore(ChatUtil.fixColor("&7Kwota sprzedazy: &c$262.14M"))
        .addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.fixColor("&aKliknij LPM aby zakupic!"))
        .addLore(ChatUtil.fixColor("&aKliknij PPM aby sprzedac!"))
        .addLore(ChatUtil.fixColor("&aKliknij SHIFT + LPM aby sprzedac wszystko!"));
        
        
        final ItemBuilder moneta5 = new ItemBuilder(Material.HORN_CORAL, 1)
        .setTitle(ChatUtil.translateHexColorCodes("&#FFF000&lK&#FCE902&lo&#F9E205&lr&#F6DB07&la&#F2D40A&ll&#EFCD0C&lo&#ECC60F&lw&#E9BE11&ly &#E6B714&lp&#E3B016&lr&#DFA919&lo&#DCA21B&lm&#D99B1E&ly&#D69420&lk"))
        .addLore(ChatUtil.fixColor("&7Kwota zakupu: &a$562.14M"))
        .addLore(ChatUtil.fixColor("&7Kwota sprzedazy: &c$562.14M"))
        .addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.fixColor("&aKliknij LPM aby zakupic!"))
        .addLore(ChatUtil.fixColor("&aKliknij PPM aby sprzedac!"))
        .addLore(ChatUtil.fixColor("&aKliknij SHIFT + LPM aby sprzedac wszystko!"));
        
        final ItemBuilder stankonta = new ItemBuilder(Material.PLAYER_HEAD)
        .setTitle(ChatUtil.fixColor("&6Stan konta"))
        .addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.fixColor("&7twoje pieniedze: &a" + ChatUtil.formatAmount(user.getBalance())));
        ItemBuilder.fillGui(inv);
        inv.setItem(20, moneta1.ToItemStack());
        inv.setItem(21, moneta2.ToItemStack());
        inv.setItem(22, moneta3.ToItemStack());
        inv.setItem(23, moneta4.ToItemStack());
        inv.setItem(24, moneta5.ToItemStack());
        inv.setItem(40, stankonta.ToItemStack());
        p.openInventory(inv);
    }
}
