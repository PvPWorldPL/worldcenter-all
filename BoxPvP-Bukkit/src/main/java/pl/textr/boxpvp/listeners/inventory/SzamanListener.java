package pl.textr.boxpvp.listeners.inventory;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import api.data.UserProfile;
import api.managers.ItemsManager;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class SzamanListener implements Listener {
	
	
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onClicklasa(final InventoryClickEvent e) {  
    	  final Player p = (Player) e.getWhoClicked();

    	  if (ChatUtil.fixColor("&eSzaman").equalsIgnoreCase(e.getView().getTitle())) {
    	      e.setCancelled(true);
    	      e.setResult(Event.Result.DENY);
    	      ItemStack item = e.getCurrentItem();
    	      UserProfile user = UserAccountManager.getUser(p);
    	      if (item != null) {
    	        if (user == null) {
    	          p.closeInventory();
    	          ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie wykryto Cie w bazie danych! Zgto administracji!");
    	          return;
    	        } 
    	    
    	        
    	        //perk zycia
    	        if (item.getType() == Material.BEETROOT_SOUP) {
    	          if (user.getPerkZycia() == 0) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
    	              p.closeInventory();
    	              return;
    	            } 
    	              p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	              user.addperkZycia();
    	              ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie zycia");
    	              p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.getPlugin().getConfiguration().getPerkZycia1());  
       	           
    	              p.closeInventory();
    	              return;
    	          } 
    	          if (user.getPerkZycia() == 1) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
        	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");       
    	              p.closeInventory();
    	              return;
    	            } 
    	              p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	              user.addperkZycia();
    	              ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie zycia");
    	              p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.getPlugin().getConfiguration().getPerkZycia2());  
    	              p.closeInventory();
    	              return;
    	          } 
    	          
    	          if (user.getPerkZycia() == 2) {
    	        	if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
    	              p.closeInventory();
    	              return;
    	            } 
    	              p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	              user.addperkZycia();
    	              ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie zycia");
    	              p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.getPlugin().getConfiguration().getPerkZycia2()); 
    	              p.closeInventory();
    	              return;
    	          } 
    	          
    	          if (user.getPerkZycia() == 3) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
    	              p.closeInventory();
    	              return;
    	            } 
    	              p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	              user.addperkZycia();
    	              ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie zycia");
    	              p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.getPlugin().getConfiguration().getPerkZycia4()); 
    	              p.closeInventory();
    	              return;
    	          } 
    	          if (user.getPerkZycia() == 4) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cPosiadasz juz maksymalny poziom!");
    	              p.closeInventory();
    	              return;
    	          } 
    	        } 
    	        
    	        //perk szybkosci
    	        if (item.getType() == Material.CYAN_DYE) {
    	          if (user.getPerkSzybkosci() == 0) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
    	              p.closeInventory();
    	              return;
    	            } 
    	              p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	              user.addperkSzybkosci();
    	              ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie szybkosci");
    	              p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(Main.getPlugin().getConfiguration().getPerkSzybkosci1());
    	              p.closeInventory();
    	              return;
    	          } 
    	          if (user.getPerkSzybkosci() == 1) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cPosiadasz juz maksymalny poziom!");
    	              p.closeInventory();
    	              return;
    	          } 
    	        } 
    	        
    	        //perk sily
    	        if (item.getType() == Material.DIAMOND_SWORD) {
    	          if (user.getPerkSily() == 0) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
    	              p.closeInventory();
    	              return;
    	            } 
    	              p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	              user.addperkSily();
    	              ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie obrazen");
    	              p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(Main.getPlugin().getConfiguration().getPerkSily1());
    	              p.closeInventory();
    	              return;
    	          } 
    	          
    	          if (user.getPerkSily() == 1) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
    	              p.closeInventory();
    	              return;
    	            } 
    	              p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	              user.addperkSily();
    	              ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie obrazen");
    	              p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(Main.getPlugin().getConfiguration().getPerkSily2());
    	              p.closeInventory();
    	              return;
    	          }
    	          
    	          if (user.getPerkSily() == 2) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
    	              p.closeInventory();
    	              return;
    	            } 
    	              p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	              user.addperkSily();
    	              ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie obrazen");
    	              p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(Main.getPlugin().getConfiguration().getPerkSily3());
    	              p.closeInventory();
    	            return;
    	          } 
    	          
    	          if (user.getPerkSily() == 3) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
    	              p.closeInventory();
    	              return;
    	            } 
    	              p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	              user.addperkSily();
    	              ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie obrazen");
    	              p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(Main.getPlugin().getConfiguration().getPerkSily4());
    	              p.closeInventory();
    	             return;
    	          } 
    	          if (user.getPerkSily() == 4) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cPosiadasz juz maksymalny poziom!");
    	              p.closeInventory();
    	              return;
    	          } 
    	        } 
    	        
    	        //perk szybkosci ataku
    	        
    	        if (item.getType() == Material.NETHERITE_SWORD) {
    	          if (user.getPerkSzybkosciAtaku() == 0) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
        	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
        	              p.closeInventory();
    	              return;
    	            } 
    	              p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	              user.addperkSzybkosciAtaku();
    	              ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie szybkosci ataku");
    	              p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(Main.getPlugin().getConfiguration().getPerkSzybkosciAtaku1());
    	              p.closeInventory();
    	            return;
    	          } 
    	          
    	          if (user.getPerkSzybkosciAtaku() == 1) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
        	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
        	              p.closeInventory();
    	              return;
    	            } 
    	             p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	             user.addperkSzybkosciAtaku();
    	             ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie szybkosci ataku");
    	             p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(Main.getPlugin().getConfiguration().getPerkSzybkosciAtaku2());
    	             p.closeInventory();
    	            return;
    	          } 
    	          
    	          if (user.getPerkSzybkosciAtaku() == 2) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
    	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie posiadasz 50 odlamkow &caby ulepszyc!");
    	              p.closeInventory();
    	              return;
    	            } 
    	             p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	             user.addperkSzybkosciAtaku();
    	             ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie szybkosci ataku");
    	             p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(Main.getPlugin().getConfiguration().getPerkSzybkosciAtaku3());
    	             p.closeInventory();
    	             return;
    	          } 
    	          
    	          if (user.getPerkSzybkosciAtaku() == 3) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
        	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
        	              p.closeInventory();
    	              return;
    	            } 
    	             p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	             user.addperkSzybkosciAtaku();
    	             ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie szybkosci ataku");
    	             p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(Main.getPlugin().getConfiguration().getPerkSzybkosciAtaku4());
    	             p.closeInventory();
    	             return;
    	          } 
    	          if (user.getPerkSzybkosciAtaku() == 4) {
    	            ChatUtil.sendMessage(p, "&8[&C&l!&8] &cPosiadasz juz maksymalny poziom!");
    	            p.closeInventory();
    	            return;
    	          } 
    	        } 
    	        
    	        //perk wampiryzmu
    	        
    	        if (item.getType() == Material.GREEN_DYE) {
    	          if (user.getPerkWampiryzmu() == 0) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
        	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
        	              p.closeInventory();
    	              return;
    	            } 
    	            p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	            user.addperkWampiryzmu();
    	            ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie szansy na uleczenie");
    	            p.closeInventory();
    	            return;
    	          } 
    	          
    	          if (user.getPerkWampiryzmu() == 1) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
        	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
        	              p.closeInventory();
    	             return;
    	            } 
    	            p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	            user.addperkWampiryzmu();
    	            ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie szansy na uleczenie");
    	            p.closeInventory();
    	            return;
    	          } 
    	          
    	          if (user.getPerkWampiryzmu() == 2) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
        	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
        	              p.closeInventory();
    	            return;
    	            } 
    	            p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	            user.addperkWampiryzmu();
    	            ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie szansy na uleczenie");
    	            p.closeInventory();
    	            return;
    	          } 
    	          if (user.getPerkWampiryzmu() == 3) {
    	        	  if (!p.getInventory().containsAtLeast(ItemsManager.getodlamek(50), 50)) {
        	              ChatUtil.sendMessage(p, "&8[&C&l!&8] &cBrakuje Ci 50szt kamienia filozoficznego do ulepszenia!");
        	              p.closeInventory();
    	            return;
    	            } 
    	            p.getInventory().removeItem(new ItemStack(ItemsManager.getodlamek(50)));
    	            user.addperkWampiryzmu();
    	            ChatUtil.sendMessage(p, "&7Pomyslnie zakupiles &azwiekszenie szansy na uleczenie");
    	            p.closeInventory();
    	            return;
    	          } 
    	          if (user.getPerkWampiryzmu() == 4) {
    	           ChatUtil.sendMessage(p, "&8[&C&l!&8] &cPosiadasz juz maksymalny poziom!");
    	           p.closeInventory();
    	           return;
    	          } 
    	        } 
    	      } 
    	  }
    }
    
    	  }
    
	

