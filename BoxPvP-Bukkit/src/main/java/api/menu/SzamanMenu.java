package api.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import api.data.UserProfile;
import api.managers.ItemsManager;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;

public class SzamanMenu {
  public static void show(Player p) {
    Inventory inv = Bukkit.createInventory(p, 54, ChatUtil.fixColor("&eSzaman"));
   UserProfile user = UserAccountManager.getUser(p);
   
    ItemBuilder perk1 = (new ItemBuilder(Material.BEETROOT_SOUP))
    		.setTitle(ChatUtil.translateHexColorCodes("&#00ff00&lP&#00f500&le&#00eb00&lr&#00e000&lk &#00d600&lz&#00cc00&ld&#00c200&lr&#00b800&lo&#00ad00&lw&#00a300&li&#009900&la"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&7Aktualny poziom: &f" + user.getPerkZycia()))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.translateHexColorCodes("&#ff92c0&lP&#f494c9&lo&#ea97d2&lz&#df99db&li&#d49be4&lo&#c99ded&lm&#bfa0f6&ly&#b4a2ff&l:"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&71 &8- &7Dodatkowe zycie &41♥"))
    		.addLore(ChatUtil.fixColor("&72 &8- &7Dodatkowe zycie &41♥"))
    		.addLore(ChatUtil.fixColor("&73 &8- &7Dodatkowe zycie &41♥"))
    		.addLore(ChatUtil.fixColor("&74 &8- &7Dodatkowe zycie &41♥"))
    		
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&7Koszt ulepszenia: &f50 &7odlamkow"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.translateHexColorCodes("&#ff99cc&lK&#fc96c9&ll&#f993c6&li&#f690c3&lk&#f38dc0&ln&#f08abd&li&#ed87ba&lj &#ea84b7&la&#e781b4&lb&#e47eb1&ly &#e17bae&lu&#de78ab&ll&#db75a8&le&#d872a5&lp&#d56fa2&ls&#d26c9f&lz&#cf699c&ly&#cc6699&lc"))
   	        .addLore(ChatUtil.fixColor(""));
    
    
    ItemBuilder perk2 = (new ItemBuilder(Material.CYAN_DYE))
    	    .setTitle(ChatUtil.translateHexColorCodes("&#0099ff&lP&#0095fb&le&#0091f7&lr&#008cf2&lk &#0088ee&ls&#0084ea&lz&#0080e6&ly&#007be1&lb&#0077dd&lk&#0073d9&lo&#006fd5&ls&#006ad0&lc&#0066cc&li"))
    	    .addLore(ChatUtil.fixColor(""))
    	    .addLore(ChatUtil.fixColor("&7Aktualny poziom: &f" + user.getPerkSzybkosci()))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.translateHexColorCodes("&#ff92c0&lP&#f494c9&lo&#ea97d2&lz&#df99db&li&#d49be4&lo&#c99ded&lm&#bfa0f6&ly&#b4a2ff&l:"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&71 &8- &7Zwieksza poziom twojej szybkosci"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&7Koszt ulepszenia: &f50 &7odlamkow"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.translateHexColorCodes("&#ff99cc&lK&#fc96c9&ll&#f993c6&li&#f690c3&lk&#f38dc0&ln&#f08abd&li&#ed87ba&lj &#ea84b7&la&#e781b4&lb&#e47eb1&ly &#e17bae&lu&#de78ab&ll&#db75a8&le&#d872a5&lp&#d56fa2&ls&#d26c9f&lz&#cf699c&ly&#cc6699&lc"))
   	        .addLore(ChatUtil.fixColor(""));
    
    
    
    ItemBuilder perk3 = (new ItemBuilder(Material.GREEN_DYE))
    		.setTitle(ChatUtil.translateHexColorCodes("&#4eff31&lP&#5cf535&le&#69ea39&lr&#77e03d&lk &#84d642&lw&#92cb46&la&#a0c14a&lm&#adb74e&lp&#bbad52&li&#c9a256&lr&#d6985b&ly&#e48e5f&lz&#f18363&lm&#ff7967&lu"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&7Aktualny poziom: &f" + user.getPerkWampiryzmu()))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.translateHexColorCodes("&#ff92c0&lP&#f494c9&lo&#ea97d2&lz&#df99db&li&#d49be4&lo&#c99ded&lm&#bfa0f6&ly&#b4a2ff&l:"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&71 &8- &a1.0% &7szans na wyleczenie"))
    		.addLore(ChatUtil.fixColor("&72 &8- &a2.0% &7szans na wyleczenie"))
    		.addLore(ChatUtil.fixColor("&73 &8- &a3.0% &7szans na wyleczenie"))
    		.addLore(ChatUtil.fixColor("&74 &8- &a4.0% &7szans na wyleczenie"))   		
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&7Koszt ulepszenia: &f50 &7odlamkow"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.translateHexColorCodes("&#ff99cc&lK&#fc96c9&ll&#f993c6&li&#f690c3&lk&#f38dc0&ln&#f08abd&li&#ed87ba&lj &#ea84b7&la&#e781b4&lb&#e47eb1&ly &#e17bae&lu&#de78ab&ll&#db75a8&le&#d872a5&lp&#d56fa2&ls&#d26c9f&lz&#cf699c&ly&#cc6699&lc"))
	        .addLore(ChatUtil.fixColor(""));
    
    
    ItemBuilder perk4 = (new ItemBuilder(Material.DIAMOND_SWORD))
    	    .setTitle(ChatUtil.translateHexColorCodes("&#ffa500&lP&#ee9703&le&#de8a05&lr&#cd7c08&lk &#bd6e0b&ls&#ac600e&li&#9c5310&ll&#8b4513&ly"))
    	    .addLore(ChatUtil.fixColor(""))
    	    .addLore(ChatUtil.fixColor("&7Aktualny poziom: &f" + user.getPerkSily()))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.translateHexColorCodes("&#ff92c0&lP&#f494c9&lo&#ea97d2&lz&#df99db&li&#d49be4&lo&#c99ded&lm&#bfa0f6&ly&#b4a2ff&l:"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&71 &8- &7Zwieksza twoje obrazenia o &42.1 &7⚔"))
    		.addLore(ChatUtil.fixColor("&72 &8- &7Zwieksza twoje obrazenia o &42.2 &7⚔"))
    		.addLore(ChatUtil.fixColor("&73 &8- &7Zwieksza twoje obrazenia o &42.3 &7⚔"))
    		.addLore(ChatUtil.fixColor("&74 &8- &7Zwieksza twoje obrazenia o &42.4 &7⚔"))
    		
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&7Koszt ulepszenia: &f50 &7odlamkow"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.translateHexColorCodes("&#ff99cc&lK&#fc96c9&ll&#f993c6&li&#f690c3&lk&#f38dc0&ln&#f08abd&li&#ed87ba&lj &#ea84b7&la&#e781b4&lb&#e47eb1&ly &#e17bae&lu&#de78ab&ll&#db75a8&le&#d872a5&lp&#d56fa2&ls&#d26c9f&lz&#cf699c&ly&#cc6699&lc"))  	     
	        .addLore(ChatUtil.fixColor(""));
    
    ItemBuilder perk5 = (new ItemBuilder(Material.NETHERITE_SWORD))
    		.setTitle(ChatUtil.translateHexColorCodes("&#ffd700&lP&#f8ca00&le&#f1be00&lr&#ebb100&lk &#e4a400&ls&#dd9800&lz&#d68b00&ly&#cf7e00&lb&#c87200&lk&#c26500&lo&#bb5900&ls&#b44c00&lc&#ad3f00&li &#a63300&la&#9f2600&lt&#991900&la&#920d00&lk&#8b0000&lu"))
    		.addLore(ChatUtil.fixColor(""))
    	    .addLore(ChatUtil.fixColor("&7Aktualny poziom: &f" + user.getPerkSzybkosciAtaku()))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.translateHexColorCodes("&#ff92c0&lP&#f494c9&lo&#ea97d2&lz&#df99db&li&#d49be4&lo&#c99ded&lm&#bfa0f6&ly&#b4a2ff&l:"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&71 &8- &7Zwieksza poziom szybkosci ataku o &e4.2 &6⚡"))
    		.addLore(ChatUtil.fixColor("&72 &8- &7Zwieksza poziom szybkosci ataku o &e4.25 &6⚡"))
    		.addLore(ChatUtil.fixColor("&73 &8- &7Zwieksza poziom szybkosci ataku o &e4.3 &6⚡"))
    		.addLore(ChatUtil.fixColor("&74 &8- &7Zwieksza poziom szybkosci ataku o &e4.35 &6⚡"))
    		
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.fixColor("&7Koszt ulepszenia: &f50 &7odlamkow"))
    		.addLore(ChatUtil.fixColor(""))
    		.addLore(ChatUtil.translateHexColorCodes("&#ff99cc&lK&#fc96c9&ll&#f993c6&li&#f690c3&lk&#f38dc0&ln&#f08abd&li&#ed87ba&lj &#ea84b7&la&#e781b4&lb&#e47eb1&ly &#e17bae&lu&#de78ab&ll&#db75a8&le&#d872a5&lp&#d56fa2&ls&#d26c9f&lz&#cf699c&ly&#cc6699&lc"))  	        	     
	        .addLore(ChatUtil.fixColor(""));

      ItemBuilder perk6 = (new ItemBuilder(Material.DIAMOND_PICKAXE))
              .setTitle(ChatUtil.translateHexColorCodes("&#6dd60f&lP&#91e536&le&#b6f35d&lr&#cefd77&lk &#cefd77&lD&#cefd77&lr&#b6f35d&lo&#91e536&lp&#6dd60f&lu"))
              .addLore(ChatUtil.fixColor(""))
              .addLore(ChatUtil.fixColor("&7Aktualny poziom: &f" + user.getPerkDropu()))
              .addLore(ChatUtil.fixColor(""))
              .addLore(ChatUtil.translateHexColorCodes("&#ff92c0&lP&#f494c9&lo&#ea97d2&lz&#df99db&li&#d49be4&lo&#c99ded&lm&#bfa0f6&ly&#b4a2ff&l:"))
              .addLore(ChatUtil.fixColor(""))
              .addLore(ChatUtil.fixColor("&71 &8- &7Zwieksza procent dropu o &e4.2 &6⛏"))
              .addLore(ChatUtil.fixColor("&72 &8- &7Zwieksza procent dropu o &e4.25 &6⛏"))
              .addLore(ChatUtil.fixColor("&73 &8- &7Zwieksza procent dropu o &e4.3 &6⛏"))
              .addLore(ChatUtil.fixColor("&74 &8- &7Zwieksza procent dropu o &e4.35 &6⛏"))

              .addLore(ChatUtil.fixColor(""))
              .addLore(ChatUtil.fixColor("&7Koszt ulepszenia: &f50 &7odlamkow"))
              .addLore(ChatUtil.fixColor(""))
              .addLore(ChatUtil.translateHexColorCodes("&#ff99cc&lK&#fc96c9&ll&#f993c6&li&#f690c3&lk&#f38dc0&ln&#f08abd&li&#ed87ba&lj &#ea84b7&la&#e781b4&lb&#e47eb1&ly &#e17bae&lu&#de78ab&ll&#db75a8&le&#d872a5&lp&#d56fa2&ls&#d26c9f&lz&#cf699c&ly&#cc6699&lc"))
              .addLore(ChatUtil.fixColor(""));

      ItemBuilder.fillGui(inv);
    inv.setItem(20, perk1.ToItemStack());
    inv.setItem(21, perk2.ToItemStack());
    inv.setItem(22, perk3.ToItemStack());
    inv.setItem(23, perk4.ToItemStack());
    inv.setItem(24, perk5.ToItemStack());
      inv.setItem(29, perk6.ToItemStack());

      p.openInventory(inv);
  }

  
  public static void resetPerki(Player p) {
      UserProfile user = UserAccountManager.getUser(p);
      if (user == null)
          return;
      //oddanie odlamkow za perk zycia
      if (user.getPerkZycia() == 1) {
          p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
      }
      if (user.getPerkZycia() == 2) {
          p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
  }
    if (user.getPerkZycia() == 3) {
        p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkZycia() == 4) {
        p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkSzybkosci() == 1) {
        p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    //oddanie odlamkow za perk sily
    if (user.getPerkSily() == 1) {
    p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkSily() == 2) {
     p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkSily() == 3) {
    p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkSily() == 4) {
    p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    //oddanie odlamkow za perk szybkosciataku
    if (user.getPerkSzybkosciAtaku() == 1) {
    p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkSzybkosciAtaku() == 2) {
    p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkSzybkosciAtaku() == 3) {
    p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkSzybkosciAtaku() == 4) {
    p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    //oddanie odlamkow za perk PerkWampiryzmu
    if (user.getPerkWampiryzmu() == 1) {
     p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkWampiryzmu() == 2) {
    p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkWampiryzmu() == 3) {
    p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
    if (user.getPerkWampiryzmu() == 4) {
    p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
    }
      //oddanie odlamkow za perk PerkWampiryzmu
      if (user.getPerkDropu() == 1) {
          p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
      }
      if (user.getPerkDropu() == 2) {
          p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
      }
      if (user.getPerkDropu() == 3) {
          p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
      }
      if (user.getPerkDropu() == 4) {
          p.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(50)));
      }
    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0D);
    p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1D);
    p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2.0D);
    p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.0D);
    user.setPerkZycia(0);
    user.setPerkSzybkosci(0);
    user.setPerkSily(0);
    user.setPerkSzybkosciAtaku(0);
    user.setPerkWampiryzmu(0);
    user.setPerkDropu(0);
    user.save();
  }
  
  public static void checkPerki(Player p) {
   UserProfile user = UserAccountManager.getUser(p);
    if (user == null)
      return; 
    
      if (user.getPerkZycia() == 0) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0D);
      }
      if (user.getPerkZycia() == 1) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.getPlugin().getConfiguration().getPerkZycia1());
      }
      if (user.getPerkZycia() == 2) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.getPlugin().getConfiguration().getPerkZycia2());
      }
      if (user.getPerkZycia() == 3) {
     p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.getPlugin().getConfiguration().getPerkZycia3());
      }
   if (user.getPerkZycia() == 4) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.getPlugin().getConfiguration().getPerkZycia4());
   }
      
      //perk szybkosci
    if (user.getPerkSzybkosci() == 0) {
        p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1D);
    }
      if (user.getPerkSzybkosci() == 1) {
        p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(Main.getPlugin().getConfiguration().getPerkSzybkosci1());
    }
    
    //perk sily
    if (user.getPerkSily() == 0) {
      p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2.0D);
    }
    if (user.getPerkSily() == 1) {
      p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(Main.getPlugin().getConfiguration().getPerkSily1());
    }
    if (user.getPerkSily() == 2) {
    	  p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(Main.getPlugin().getConfiguration().getPerkSily2());
    }
    if (user.getPerkSily() == 3) {
    	  p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(Main.getPlugin().getConfiguration().getPerkSily3());
    }
    if (user.getPerkSily() == 4) {
    	  p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(Main.getPlugin().getConfiguration().getPerkSily4());
    }
   //perk szybkosci ataku
    
    if (user.getPerkSzybkosciAtaku() == 0) {
      p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.0D);
    }
    if (user.getPerkSzybkosciAtaku() == 1) {
      p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(Main.getPlugin().getConfiguration().getPerkSzybkosciAtaku1());
    }
    if (user.getPerkSzybkosciAtaku() == 2) {
      p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(Main.getPlugin().getConfiguration().getPerkSzybkosciAtaku2());
    }
    if (user.getPerkSzybkosciAtaku() == 3) {
      p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(Main.getPlugin().getConfiguration().getPerkSzybkosciAtaku3());
    }
      if (user.getPerkSzybkosciAtaku() == 4) {
          p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(Main.getPlugin().getConfiguration().getPerkSzybkosciAtaku4());
      }


  }
}
