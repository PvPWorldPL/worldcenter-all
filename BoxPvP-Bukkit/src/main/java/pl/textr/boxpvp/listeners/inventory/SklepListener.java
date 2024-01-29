package pl.textr.boxpvp.listeners.inventory;

import java.math.BigDecimal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import api.data.UserProfile;
import api.managers.ItemsManager;
import api.managers.UserAccountManager;
import api.menu.ShopMenu;
import pl.textr.boxpvp.utils.ChatUtil;

public class SklepListener implements Listener {
	
	
	
	  @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	    public void onClickShop(final InventoryClickEvent e) {
	        final Player p = (Player) e.getWhoClicked();

	        if (ChatUtil.fixColor("&eSklep &7(menu)").equalsIgnoreCase(e.getView().getTitle())) {
    e.setCancelled(true);
    e.setResult(Event.Result.DENY);
    ItemStack item = e.getCurrentItem();
    if (item != null) {
        if (item.getType() == Material.CLOCK) {
            ShopMenu.show(p);
        }
        if (item.getType() == Material.VILLAGER_SPAWN_EGG) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "shopkeepers open 4 " + p.getName());
        }
        if (item.getType() == Material.SUNFLOWER) {
            ShopMenu.show2(p);
        }
        return;
    }
}


if (ChatUtil.fixColor("&eSklep &7(sklep za czas)").equalsIgnoreCase(e.getView().getTitle())) {
    e.setCancelled(true);
    e.setResult(Event.Result.DENY);
    UserProfile user = UserAccountManager.getUser(p);
    ItemStack item = e.getCurrentItem();
    if (item != null) {
        if (item.getType() == Material.LIME_DYE) {
            if (user.getMoney() >= 1000) {
                ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&aPomyślnie zakupiono &#30ff38&lʀ&#2ef636&lᴜ&#2cec34&lʙ&#2ae331&lɪ&#28d92f&lɴ &#26d02d&ls&#24c62b&lᴢ&#22bd29&lᴍ&#21b326&lᴀ&#1faa24&lʀ&#1da022&lᴀ&#1b9720&lɢ&#198d1e&lᴅ&#17841b&lᴏ&#157a19&lᴡ&#137117&lʏ &8(&6✪1&8)"));
                user.setMoney(user.getMoney() - 1000);
                p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta1(10)));
                p.closeInventory();
            } else {
                p.closeInventory();
                ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości monet czasu!");
            }
            return;
        }



        if (item.getType() == Material.LIGHT_BLUE_DYE) {
            if (user.getMoney() >= 3000) {
                ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &#5be1ff&lʀ&#56d9fb&lᴜ&#51d0f6&lʙ&#4cc8f2&lɪ&#46c0ed&lɴ &#41b8e9&lʟ&#3cafe4&lᴀ&#37a7e0&lᴢ&#329fdb&lᴜ&#2d96d7&lʀ&#288ed2&lʏ&#2286ce&lᴛ&#1d7ec9&lᴏ&#1875c5&lᴡ&#136dc0&lʏ &8(&6✪2&8)"));
                user.setMoney(user.getMoney() - 3000);
                p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta2(5)));
                p.closeInventory();
            } else {
                p.closeInventory();
                ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości monet czasu!");
            }
            return;
        }

        if (item.getType() == Material.BLUE_DYE) {
            if (user.getMoney() >= 5000) {
                ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &#1400fb&lʀ&#1803f9&lᴜ&#1b06f8&lʙ&#1f09f6&lɪ&#220cf5&lɴ &#260ff3&lɢ&#2912f1&lʀ&#2d15f0&lᴀ&#3018ee&lɴ&#341bec&lᴀ&#371eeb&lᴛ&#3b21e9&lᴏ&#3e24e8&lᴡ&#4227e6&lʏ &8(&6✪4&8)"));
                user.setMoney(user.getMoney() - 5000);
                p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta4(5)));
                p.closeInventory();
            } else {
                p.closeInventory();
                ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości monet czasu!");
            }
            return;
        }
        
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.getDisplayName();
            if (meta.getDisplayName().equals(ChatUtil.fixColor("&fShulkerowa skrzynia"))) {
                if (user.getMoney() >= 6) {
                    ChatUtil.sendMessage(p, "&aPomyślnie zakupiono Shulkerową skrzynię");
                    user.setMoney(user.getMoney() - 6);
                    p.getInventory().addItem(new ItemStack(Material.SHULKER_BOX));
                    p.closeInventory();
                } else {
                    p.closeInventory();
                    ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości monet czasu!");
                }
                return;
            }

            if (meta.getDisplayName().equals(ChatUtil.fixColor("&7Klucz do skrzyni &b&lZWYKLEJ"))) {
                if (user.getMoney() >= 200) {
                    ChatUtil.sendMessage(p, "&aPomyślnie zakupiono Klucz do skrzyni ZWYKLEJ");
                    user.setMoney(user.getMoney() - 200);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give physical zwykla 1 " + p.getName());
                    p.closeInventory();
                } else {
                    p.closeInventory();
                    ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości monet czasu!");
                }
                return;
            }

            if (meta.getDisplayName().equals(ChatUtil.fixColor("&7Klucz do skrzyni &d&lEPICKIEJ"))) {
                if (user.getMoney() >= 400) {
                    ChatUtil.sendMessage(p, "&aPomyślnie zakupiono Klucz do skrzyni EPICKIEJ");
                    user.setMoney(user.getMoney() - 400);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give physical epicka 1 " + p.getName());
                    p.closeInventory();
                } else {
                    p.closeInventory();
                    ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości monet czasu!");
                }
                return;
            }

            if (meta.getDisplayName().equals(ChatUtil.fixColor("&7Klucz do skrzyni &a&lRZADKIEJ"))) {
                if (user.getMoney() >= 600) {
                    ChatUtil.sendMessage(p, "&aPomyślnie zakupiono Klucz do skrzyni RZADKIEJ");
                    user.setMoney(user.getMoney() - 600);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give physical rzadka 1 " + p.getName());
                    p.closeInventory();
                } else {
                    p.closeInventory();
                    ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości monet czasu!");
                }
            }
        }
            }
        }
    }



  
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onClicklasa(final InventoryClickEvent e) {  
    	  final Player p = (Player) e.getWhoClicked();

    	 if (ChatUtil.fixColor("&eSklep &7(sklep za kase)").equalsIgnoreCase(e.getView().getTitle())) {
      e.setCancelled(true);
      e.setResult(Event.Result.DENY);
      ItemStack item = e.getCurrentItem();
      UserProfile user = UserAccountManager.getUser(p);
      if (item != null) {
        if (item.getType() == Material.LIME_DYE) {
          if (e.getClick() == ClickType.LEFT) {
            if (user.hasEnough(BigDecimal.valueOf(1L))) {
              user.removeBalance(1.0D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &fx1 &#30ff38&lʀ&#2ef636&lᴜ&#2cec34&lʙ&#2ae331&lɪ&#28d92f&lɴ &#26d02d&ls&#24c62b&lᴢ&#22bd29&lᴍ&#21b326&lᴀ&#1faa24&lʀ&#1da022&lᴀ&#1b9720&lɢ&#198d1e&lᴅ&#17841b&lᴏ&#157a19&lᴡ&#137117&lʏ &8(&6✪1&8)"));
              p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta1(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości pieniedzy!");
            } 
            return;
          } 
          
          
          if (e.getClick() == ClickType.RIGHT) {
            if (p.getInventory().containsAtLeast(ItemsManager.getMoneta1(1), 1)) {
              user.addBalance(1.0D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie sprzedano &fx1 &#30ff38&lʀ&#2ef636&lᴜ&#2cec34&lʙ&#2ae331&lɪ&#28d92f&lɴ &#26d02d&ls&#24c62b&lᴢ&#22bd29&lᴍ&#21b326&lᴀ&#1faa24&lʀ&#1da022&lᴀ&#1b9720&lɢ&#198d1e&lᴅ&#17841b&lᴏ&#157a19&lᴡ&#137117&lʏ &8(&6✪1&8)"));
             
              p.getInventory().removeItem(new ItemStack(ItemsManager.getMoneta1(1))); 
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości przedmiotow!");
            } 
            return;
          } 
          if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.DOUBLE_CLICK)) {
          
        	  ItemsManager.convertMoneyToCurrency(p, ItemsManager.getMoneta1(1), 1.0D);
            ShopMenu.show2(p);
          } 
          return;
        } 
        
        
        if (item.getType() == Material.LIGHT_BLUE_DYE) {
          if (e.getClick() == ClickType.LEFT) {
            if (user.hasEnough(BigDecimal.valueOf(64L))) {
              user.removeBalance(64.0D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &fx1 &#5be1ff&lʀ&#56d9fb&lᴜ&#51d0f6&lʙ&#4cc8f2&lɪ&#46c0ed&lɴ &#41b8e9&lʟ&#3cafe4&lᴀ&#37a7e0&lᴢ&#329fdb&lᴜ&#2d96d7&lʀ&#288ed2&lʏ&#2286ce&lᴛ&#1d7ec9&lᴏ&#1875c5&lᴡ&#136dc0&lʏ &8(&6✪2&8)"));
              p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta2(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości pieniedzy!");
            } 
            return;
          } 
          
          if (e.getClick() == ClickType.RIGHT) {
            if (p.getInventory().containsAtLeast(ItemsManager.getMoneta2(1), 1)) {
              user.addBalance(64.0D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie sprzedano &fx1 &#5be1ff&lʀ&#56d9fb&lᴜ&#51d0f6&lʙ&#4cc8f2&lɪ&#46c0ed&lɴ &#41b8e9&lʟ&#3cafe4&lᴀ&#37a7e0&lᴢ&#329fdb&lᴜ&#2d96d7&lʀ&#288ed2&lʏ&#2286ce&lᴛ&#1d7ec9&lᴏ&#1875c5&lᴡ&#136dc0&lʏ &8(&6✪2&8)"));
            
              p.getInventory().removeItem(new ItemStack(ItemsManager.getMoneta2(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości przedmiotow!");
            } 
            return;
          } 
          if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.DOUBLE_CLICK)) {
          
        	  ItemsManager.convertMoneyToCurrency(p, ItemsManager.getMoneta2(1), 64.0D);
            ShopMenu.show2(p);
          } 
          return;
         } 
        
        
        
        if (item.getType() == Material.MAGENTA_DYE) {
          if (e.getClick() == ClickType.LEFT) {
            if (user.hasEnough(BigDecimal.valueOf(4000L))) {
              user.removeBalance(4000.1D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &fx1 &#fa1eff&lʀ&#f21ffb&lᴜ&#eb20f7&lʙ&#e321f2&lɪ&#dc22ee&lɴ &#d423ea&lʙ&#cc24e6&lᴜ&#c525e2&lʀ&#bd27dd&ls&#b628d9&lᴢ&#ae29d5&lᴛ&#a62ad1&lʏ&#9f2bcd&lɴ&#972cc8&lᴏ&#902dc4&lᴡ&#882ec0&lʏ  &8(&6✪3&8)"));
              p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta3(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości pieniedzy!");
            } 
            return;
          } 
          
          if (e.getClick() == ClickType.RIGHT) {
            if (p.getInventory().containsAtLeast(ItemsManager.getMoneta3(1), 1)) {
              user.addBalance(4000.1D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie sprzedano &fx1 &#fa1eff&lʀ&#f21ffb&lᴜ&#eb20f7&lʙ&#e321f2&lɪ&#dc22ee&lɴ &#d423ea&lʙ&#cc24e6&lᴜ&#c525e2&lʀ&#bd27dd&ls&#b628d9&lᴢ&#ae29d5&lᴛ&#a62ad1&lʏ&#9f2bcd&lɴ&#972cc8&lᴏ&#902dc4&lᴡ&#882ec0&lʏ  &8(&6✪3&8)"));
            
              p.getInventory().removeItem(new ItemStack(ItemsManager.getMoneta3(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości przedmiotow!");
            } 
            return;
          } 
          if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.DOUBLE_CLICK)) {
           
        	  ItemsManager.convertMoneyToCurrency(p, ItemsManager.getMoneta3(1), 4096.0D);
            ShopMenu.show2(p);
          } 
          return;
        } 
        
        
        if (item.getType() == Material.BLUE_DYE) {
          if (e.getClick() == ClickType.LEFT) {
            if (user.hasEnough(BigDecimal.valueOf(262140000L))) {
              user.removeBalance(262140000D);    	   
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &fx1 &#1400fb&lʀ&#1803f9&lᴜ&#1b06f8&lʙ&#1f09f6&lɪ&#220cf5&lɴ &#260ff3&lɢ&#2912f1&lʀ&#2d15f0&lᴀ&#3018ee&lɴ&#341bec&lᴀ&#371eeb&lᴛ&#3b21e9&lᴏ&#3e24e8&lᴡ&#4227e6&lʏ &8(&6✪4&8)"));
              p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta4(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości pieniedzy!");
            } 
            return;
          } 
          
          if (e.getClick() == ClickType.RIGHT) {
            if (p.getInventory().containsAtLeast(ItemsManager.getMoneta4(1), 1)) {
              user.addBalance(262140000D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie sprzedano &fx1 &#1400fb&lʀ&#1803f9&lᴜ&#1b06f8&lʙ&#1f09f6&lɪ&#220cf5&lɴ &#260ff3&lɢ&#2912f1&lʀ&#2d15f0&lᴀ&#3018ee&lɴ&#341bec&lᴀ&#371eeb&lᴛ&#3b21e9&lᴏ&#3e24e8&lᴡ&#4227e6&lʏ &8(&6✪4&8)"));
	           p.getInventory().removeItem(new ItemStack(ItemsManager.getMoneta4(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości przedmiotow!");
	            } 
	            return;
	          } 
	          if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.DOUBLE_CLICK)) {
	          
	        	  ItemsManager.convertMoneyToCurrency(p, ItemsManager.getMoneta4(1), 262144.0D);
	            ShopMenu.show2(p);
	          } 
	          return;
	        } 
	        
	        
	        if (item.getType() == Material.RED_DYE) {
	          if (e.getClick() == ClickType.LEFT) {
	            if (user.hasEnough(BigDecimal.valueOf(562140000L))) {
	              user.removeBalance(562140000D);
 
	              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &fx1 &#fb2020&lʀ&#fb2421&lᴜ&#fb2821&lʙ&#fb2d22&lɪ&#fc3122&lɴ &#fc3523&ls&#fc3923&lᴢ&#fc3e24&lᴋ&#fc4225&lᴀ&#fc4625&lʀ&#fc4a26&lʟ&#fd4e26&lᴀ&#fd5327&lᴛ&#fd5727&lɴ&#fd5b28&lʏ &8(&6✪5&8)"));
              p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta5(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości pieniedzy!");
            } 
            return;
          } 
          
          if (e.getClick() == ClickType.RIGHT) {
            if (p.getInventory().containsAtLeast(ItemsManager.getMoneta5(1), 1)) {
              user.addBalance(562140000D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie sprzedano &fx1 &#fb2020&lʀ&#fb2421&lᴜ&#fb2821&lʙ&#fb2d22&lɪ&#fc3122&lɴ &#fc3523&ls&#fc3923&lᴢ&#fc3e24&lᴋ&#fc4225&lᴀ&#fc4625&lʀ&#fc4a26&lʟ&#fd4e26&lᴀ&#fd5327&lᴛ&#fd5727&lɴ&#fd5b28&lʏ &8(&6✪5&8)"));
               p.getInventory().removeItem(new ItemStack(ItemsManager.getMoneta5(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości przedmiotow!");
	    	            } 
	    	            return;
	    	          } 
	    	          if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.DOUBLE_CLICK)) {
	    	        
	    	        	  ItemsManager.convertMoneyToCurrency(p, ItemsManager.getMoneta5(1), 1.6777216E7D);
	    	            ShopMenu.show2(p);
	    	          }
            }
	    	      }
	    	 }
	    
	    }
	
	
	
	

}
