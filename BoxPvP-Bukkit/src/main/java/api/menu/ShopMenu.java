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
        final Inventory inv = Bukkit.createInventory(p, 54, ChatUtil.translateHexColorCodes("&eSklep &7(menu)"));
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
        final Inventory inv = Bukkit.createInventory(p, 54, ChatUtil.translateHexColorCodes("&eSklep &7(sklep za czas)"));
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


        final ItemBuilder moneta1 = new ItemBuilder(Material.LIME_DYE, 10)
        .setTitle(ChatUtil.translateHexColorCodes("&#30ff38&lʀ&#2ef636&lᴜ&#2cec34&lʙ&#2ae331&lɪ&#28d92f&lɴ &#26d02d&ls&#24c62b&lᴢ&#22bd29&lᴍ&#21b326&lᴀ&#1faa24&lʀ&#1da022&lᴀ&#1b9720&lɢ&#198d1e&lᴅ&#17841b&lᴏ&#157a19&lᴡ&#137117&lʏ &8(&6✪1&8)"))
        .addLore(ChatUtil.translateHexColorCodes("&7Koszt: &e1000&7 monet czasu"))
        .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby zakupic!"));

        final ItemBuilder moneta2 = new ItemBuilder(Material.LIGHT_BLUE_DYE, 5)
                .setTitle(ChatUtil.translateHexColorCodes("&#5be1ff&lʀ&#56d9fb&lᴜ&#51d0f6&lʙ&#4cc8f2&lɪ&#46c0ed&lɴ &#41b8e9&lʟ&#3cafe4&lᴀ&#37a7e0&lᴢ&#329fdb&lᴜ&#2d96d7&lʀ&#288ed2&lʏ&#2286ce&lᴛ&#1d7ec9&lᴏ&#1875c5&lᴡ&#136dc0&lʏ &8(&6✪2&8)"))
                .addLore(ChatUtil.translateHexColorCodes("&7Koszt: &e3000&7 monet czasu"))
                .addLore(ChatUtil.translateHexColorCodes("&aKliknij aby zakupic!"));


        final ItemBuilder moneta4 = new ItemBuilder(Material.BLUE_DYE, 10)
        .setTitle(ChatUtil.translateHexColorCodes("&#1400fb&lʀ&#1803f9&lᴜ&#1b06f8&lʙ&#1f09f6&lɪ&#220cf5&lɴ &#260ff3&lɢ&#2912f1&lʀ&#2d15f0&lᴀ&#3018ee&lɴ&#341bec&lᴀ&#371eeb&lᴛ&#3b21e9&lᴏ&#3e24e8&lᴡ&#4227e6&lʏ &8(&6✪4&8)"))
        .addLore(ChatUtil.translateHexColorCodes("&7Koszt: &e5000&7 monet czasu"))
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
        inv.setItem(25, moneta4.ToItemStack());
        
        inv.setItem(23, moneta2.ToItemStack());
        inv.setItem(40, stankonta.ToItemStack());
        p.openInventory(inv);
    }
    
    public static void show2(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, ChatUtil.fixColor("&eSklep &7(sklep za kase)"));
        final UserProfile user = UserAccountManager.getUser(p);
        
        final ItemBuilder moneta1 = new ItemBuilder(Material.LIME_DYE, 1)
        .setTitle(ChatUtil.translateHexColorCodes("&#30ff38&lʀ&#2ef636&lᴜ&#2cec34&lʙ&#2ae331&lɪ&#28d92f&lɴ &#26d02d&ls&#24c62b&lᴢ&#22bd29&lᴍ&#21b326&lᴀ&#1faa24&lʀ&#1da022&lᴀ&#1b9720&lɢ&#198d1e&lᴅ&#17841b&lᴏ&#157a19&lᴡ&#137117&lʏ &8(&6✪1&8)"))
        .addLore(ChatUtil.fixColor("&7Kwota zakupu: &a$1"))
        .addLore(ChatUtil.fixColor("&7Kwota sprzedazy: &c$1"))
        .addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.fixColor("&aKliknij LPM aby zakupic!"))
        .addLore(ChatUtil.fixColor("&aKliknij PPM aby sprzedac!"))
        .addLore(ChatUtil.fixColor("&aKliknij SHIFT + LPM aby sprzedac wszystko!"));

        ItemBuilder moneta2 = new ItemBuilder(Material.LIGHT_BLUE_DYE, 1)
        .setTitle(ChatUtil.translateHexColorCodes("&#5be1ff&lʀ&#56d9fb&lᴜ&#51d0f6&lʙ&#4cc8f2&lɪ&#46c0ed&lɴ &#41b8e9&lʟ&#3cafe4&lᴀ&#37a7e0&lᴢ&#329fdb&lᴜ&#2d96d7&lʀ&#288ed2&lʏ&#2286ce&lᴛ&#1d7ec9&lᴏ&#1875c5&lᴡ&#136dc0&lʏ &8(&6✪2&8)"))
        .addLore(ChatUtil.fixColor("&7Kwota zakupu: &a$64"))
        .addLore(ChatUtil.fixColor("&7Kwota sprzedazy: &c$64"))
        .addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.fixColor("&aKliknij LPM aby zakupic!"))
        .addLore(ChatUtil.fixColor("&aKliknij PPM aby sprzedac!"))
        .addLore(ChatUtil.fixColor("&aKliknij SHIFT + LPM aby sprzedac wszystko!"));
        
        final ItemBuilder moneta3 = new ItemBuilder(Material.MAGENTA_DYE, 1)
        .setTitle(ChatUtil.translateHexColorCodes("&#fa1eff&lʀ&#f21ffb&lᴜ&#eb20f7&lʙ&#e321f2&lɪ&#dc22ee&lɴ &#d423ea&lʙ&#cc24e6&lᴜ&#c525e2&lʀ&#bd27dd&ls&#b628d9&lᴢ&#ae29d5&lᴛ&#a62ad1&lʏ&#9f2bcd&lɴ&#972cc8&lᴏ&#902dc4&lᴡ&#882ec0&lʏ  &8(&6✪3&8)"))
        .addLore(ChatUtil.fixColor("&7Kwota zakupu: &a$4.1k"))
        .addLore(ChatUtil.fixColor("&7Kwota sprzedazy: &c$4.1k"))
        .addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.fixColor("&aKliknij LPM aby zakupic!"))
        .addLore(ChatUtil.fixColor("&aKliknij PPM aby sprzedac!"))
        .addLore(ChatUtil.fixColor("&aKliknij SHIFT + LPM aby sprzedac wszystko!"));
        
        final ItemBuilder moneta4 = new ItemBuilder(Material.BLUE_DYE, 1)
        .setTitle(ChatUtil.translateHexColorCodes("&#1400fb&lʀ&#1803f9&lᴜ&#1b06f8&lʙ&#1f09f6&lɪ&#220cf5&lɴ &#260ff3&lɢ&#2912f1&lʀ&#2d15f0&lᴀ&#3018ee&lɴ&#341bec&lᴀ&#371eeb&lᴛ&#3b21e9&lᴏ&#3e24e8&lᴡ&#4227e6&lʏ &8(&6✪4&8)"))
        .addLore(ChatUtil.fixColor("&7Kwota zakupu: &a$262.14M"))
        .addLore(ChatUtil.fixColor("&7Kwota sprzedazy: &c$262.14M"))
        .addLore(ChatUtil.fixColor(""))
        .addLore(ChatUtil.fixColor("&aKliknij LPM aby zakupic!"))
        .addLore(ChatUtil.fixColor("&aKliknij PPM aby sprzedac!"))
        .addLore(ChatUtil.fixColor("&aKliknij SHIFT + LPM aby sprzedac wszystko!"));
        //

        final ItemBuilder moneta5 = new ItemBuilder(Material.RED_DYE, 1)
        .setTitle(ChatUtil.translateHexColorCodes("&#fb2020&lʀ&#fb2421&lᴜ&#fb2821&lʙ&#fb2d22&lɪ&#fc3122&lɴ &#fc3523&ls&#fc3923&lᴢ&#fc3e24&lᴋ&#fc4225&lᴀ&#fc4625&lʀ&#fc4a26&lʟ&#fd4e26&lᴀ&#fd5327&lᴛ&#fd5727&lɴ&#fd5b28&lʏ &8(&6✪5&8)"))
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
