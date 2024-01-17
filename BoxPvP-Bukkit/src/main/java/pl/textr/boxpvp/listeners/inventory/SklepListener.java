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
        if (item.getType() == Material.GHAST_TEAR) {
            if (user.getMoney() >= 1000) {
                ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&aPomyślnie zakupiono &#00F3FF&lK&#00E9FF&lr&#00DFFF&lo&#00D5FF&lp&#00CBFF&ll&#00C2FF&la &#00B8FF&lb&#00AEFF&lo&#00A4FF&lg&#009AFF&la"));
                user.setMoney(user.getMoney() - 1000);
                p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta1(10)));
                p.closeInventory();
            } else {
                p.closeInventory();
                ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości monet czasu!");
            }
            return;
        }

        if (item.getType() == Material.WITHER_ROSE) {
            if (user.getMoney() >= 5000) {
                ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &#DE00FF&lP&#CC01ED&lo&#BA02DA&ls&#A903C8&lk&#9704B5&lr&#8505A3&la&#730591&lm&#61067E&li&#50076C&la&#3E0859&lc&#2C0947&lz"));
                user.setMoney(user.getMoney() - 5000);
                p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta4(5)));
                p.closeInventory();
            } else {
                p.closeInventory();
                ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości monet czasu!");
            }
            return;
        }

        if (item.getType() == Material.LEAD) {
            if (user.getMoney() >= 3000) {
                ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &#DE00FF&lP&#CC01ED&lo&#BA02DA&ls&#A903C8&lk&#9704B5&lr&#8505A3&la&#730591&lm&#61067E&li&#50076C&la&#3E0859&lc&#2C0947&lz"));
                user.setMoney(user.getMoney() - 3000);
                p.getInventory().addItem(new ItemStack(ItemsManager.getMoneta2(5)));
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
        if (item.getType() == Material.GHAST_TEAR) {
          if (e.getClick() == ClickType.LEFT) {
            if (user.hasEnough(BigDecimal.valueOf(1L))) {
              user.removeBalance(1.0D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &fx1 &#00F3FF&lK&#00E9FF&lr&#00DFFF&lo&#00D5FF&lp&#00CBFF&ll&#00C2FF&la &#00B8FF&lb&#00AEFF&lo&#00A4FF&lg&#009AFF&la"));
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
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie sprzedano &fx1 &#00F3FF&lK&#00E9FF&lr&#00DFFF&lo&#00D5FF&lp&#00CBFF&ll&#00C2FF&la &#00B8FF&lb&#00AEFF&lo&#00A4FF&lg&#009AFF&la"));
             
              p.getInventory().removeItem(new ItemStack(ItemsManager.getMoneta1(1))); 
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości przedmiotow!");
            } 
            return;
          } 
          if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.DOUBLE_CLICK)) {
          
        	  ItemsManager.kasakasa(p, ItemsManager.getMoneta1(1), 1.0D);
            ShopMenu.show2(p);
          } 
          return;
        } 
        
        
        if (item.getType() == Material.LEAD) {
          if (e.getClick() == ClickType.LEFT) {
            if (user.hasEnough(BigDecimal.valueOf(64L))) {
              user.removeBalance(64.0D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &fx1 &#DE00FF&lP&#CC01ED&lo&#BA02DA&ls&#A903C8&lk&#9704B5&lr&#8505A3&la&#730591&lm&#61067E&li&#50076C&la&#3E0859&lc&#2C0947&lz"));
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
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie sprzedano &fx1 &#DE00FF&lP&#CC01ED&lo&#BA02DA&ls&#A903C8&lk&#9704B5&lr&#8505A3&la&#730591&lm&#61067E&li&#50076C&la&#3E0859&lc&#2C0947&lz"));
            
              p.getInventory().removeItem(new ItemStack(ItemsManager.getMoneta2(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości przedmiotow!");
            } 
            return;
          } 
          if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.DOUBLE_CLICK)) {
          
        	  ItemsManager.kasakasa(p, ItemsManager.getMoneta2(1), 64.0D);
            ShopMenu.show2(p);
          } 
          return;
         } 
        
        
        
        if (item.getType() == Material.CRIMSON_FUNGUS) {
          if (e.getClick() == ClickType.LEFT) {
            if (user.hasEnough(BigDecimal.valueOf(4000L))) {
              user.removeBalance(4000.1D);
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &fx1 &#C03450&lR&#BB3350&lu&#B53250&lb&#B03151&li&#AA3051&ln&#A52F51&lk&#A02E51&lo&#9A2D52&lw&#952C52&ly &#902B52&lg&#8A2A52&lr&#852953&lz&#7F2853&ly&#7A2753&lb"));
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
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie sprzedano &fx1 &#C03450&lR&#BB3350&lu&#B53250&lb&#B03151&li&#AA3051&ln&#A52F51&lk&#A02E51&lo&#9A2D52&lw&#952C52&ly &#902B52&lg&#8A2A52&lr&#852953&lz&#7F2853&ly&#7A2753&lb"));
            
              p.getInventory().removeItem(new ItemStack(ItemsManager.getMoneta3(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości przedmiotow!");
            } 
            return;
          } 
          if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.DOUBLE_CLICK)) {
           
        	  ItemsManager.kasakasa(p, ItemsManager.getMoneta3(1), 4096.0D);
            ShopMenu.show2(p);
          } 
          return;
        } 
        
        
        if (item.getType() == Material.WITHER_ROSE) {
          if (e.getClick() == ClickType.LEFT) {
            if (user.hasEnough(BigDecimal.valueOf(262140000L))) {
              user.removeBalance(262140000D);    	   
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &fx1 &#FF7400&lR&#F87405&ló&#F1750B&lz&#EB7510&la &#E47615&lU&#DD761B&lp&#D67720&la&#CF7726&ld&#C8782B&lł&#C27830&le&#BB7936&lg&#B4793B&lo"));
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
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie sprzedano &fx1 &#FF7400&lR&#F87405&ló&#F1750B&lz&#EB7510&la &#E47615&lU&#DD761B&lp&#D67720&la&#CF7726&ld&#C8782B&lł&#C27830&le&#BB7936&lg&#B4793B&lo"));
	           p.getInventory().removeItem(new ItemStack(ItemsManager.getMoneta4(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości przedmiotow!");
	            } 
	            return;
	          } 
	          if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.DOUBLE_CLICK)) {
	          
	        	  ItemsManager.kasakasa(p, ItemsManager.getMoneta4(1), 262144.0D);
	            ShopMenu.show2(p);
	          } 
	          return;
	        } 
	        
	        
	        if (item.getType() == Material.HORN_CORAL) {
	          if (e.getClick() == ClickType.LEFT) {
	            if (user.hasEnough(BigDecimal.valueOf(562140000L))) {
	              user.removeBalance(562140000D);
 
	              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie zakupiono &fx1 &#FFF000&lK&#FCE902&lo&#F9E205&lr&#F6DB07&la&#F2D40A&ll&#EFCD0C&lo&#ECC60F&lw&#E9BE11&ly &#E6B714&lp&#E3B016&lr&#DFA919&lo&#DCA21B&lm&#D99B1E&ly&#D69420&lk"));
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
              ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Pomyslnie sprzedano &fx1 &#FFF000&lK&#FCE902&lo&#F9E205&lr&#F6DB07&la&#F2D40A&ll&#EFCD0C&lo&#ECC60F&lw&#E9BE11&ly &#E6B714&lp&#E3B016&lr&#DFA919&lo&#DCA21B&lm&#D99B1E&ly&#D69420&lk"));
               p.getInventory().removeItem(new ItemStack(ItemsManager.getMoneta5(1)));
              ShopMenu.show2(p);
            } else {
              p.closeInventory();
              ChatUtil.sendMessage(p, "&8[&c&l!&8] &cNie posiadasz wystarczającej ilości przedmiotow!");
	    	            } 
	    	            return;
	    	          } 
	    	          if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.DOUBLE_CLICK)) {
	    	        
	    	        	  ItemsManager.kasakasa(p, ItemsManager.getMoneta5(1), 1.6777216E7D);
	    	            ShopMenu.show2(p);
	    	          }
            }
	    	      }
	    	 }
	    
	    }
	
	
	
	

}
