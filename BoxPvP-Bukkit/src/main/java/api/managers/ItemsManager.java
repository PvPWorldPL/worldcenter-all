package api.managers;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import api.data.UserProfile;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;
import pl.textr.boxpvp.utils.RandomUtil;

public class ItemsManager {



	public static void kasakasa(Player player, ItemStack item, Double amount) {
	    UserProfile u = UserAccountManager.getUser(player);
	    if (player.getInventory().containsAtLeast(new ItemStack(item), 1)) {
	        int x = 0;
	        while (x < 2304) {
	            if (!player.getInventory().containsAtLeast(new ItemStack(item), 1)) {
	                return;
	            }
	            player.getInventory().removeItem(new ItemStack(item));
	            u.addBalance(amount);
	            u.save();
	            x++;
	        }
	    } else {
	        ChatUtil.sendMessage(player, "&8[&C&l!&8] &cNie posiadasz wystarczająco przedmiotów.");
		}
	    
	}


	
	

	  public static void recalculateDurability(Player player, ItemStack item) {
		    if (item.getType().getMaxDurability() == 0)
		      return; 
		    int enchantLevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
		    short d = item.getDurability();
		    if (enchantLevel > 0) {
		      if (100 / (enchantLevel + 1) > RandomUtil.getRandInt(0, 100))
		        if (d == item.getType().getMaxDurability()) {
		          player.getInventory().clear(player.getInventory().getHeldItemSlot());
		          player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
		        } else {
		          item.setDurability((short)(d + 1));
		        }  
		    } else if (d == item.getType().getMaxDurability()) {
		      player.getInventory().clear(player.getInventory().getHeldItemSlot());
		      player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
		    } else {
		      item.setDurability((short)(d + 1));
		    } 
		  }
		  
	  
	    
	    public static void change(Player p, Material item, Material item2) {
	      for (int x = 0; x < 256; x++) { //domyslnie wartosc 9
	        if (p.getInventory().containsAtLeast(new ItemStack(item), 64)) {
	          p.getInventory().removeItem(new ItemStack(item, 64));
	          p.getInventory().addItem(new ItemStack(item2, 1));
	        } 
	      } 
	    }
	    
	    public static void change2(Player p, Material item, Integer amount, ItemStack item2) {
	      for (int x = 0; x < 256; x++) {
	        if (p.getInventory().containsAtLeast(new ItemStack(item), amount)) {
	          p.getInventory().removeItem(new ItemStack(item, amount));
	          p.getInventory().addItem(new ItemStack(item2));
	        } 
	      } 
	    }
	    
	    public static void change3(Player p, ItemStack item, ItemStack item2) {
	      for (int x = 0; x < 256; x++) {
	        if (p.getInventory().containsAtLeast(new ItemStack(item), 64)) {
	          p.getInventory().removeItem(new ItemStack(item));
	          p.getInventory().addItem(new ItemStack(item2));
	        } 
	      } 
	    }
	    
	  public static ItemStack getMoneta1(int size) {
	    ItemStack odlamek = (new ItemBuilder(Material.GHAST_TEAR, size))
	    .setTitle(ChatUtil.translateHexColorCodes("&#00F3FF&lK&#00E9FF&lr&#00DFFF&lo&#00D5FF&lp&#00CBFF&ll&#00C2FF&la &#00B8FF&lb&#00AEFF&lo&#00A4FF&lg&#009AFF&la"))
	    .addLore("")
	    .addLore(ChatUtil.translateHexColorCodes("&7Tym itemem możesz handlować"))
		.addLore(ChatUtil.translateHexColorCodes("&7z villagerami na spawn"))
		.addLore("")
	    .setGlow().ToItemStack();
	    return odlamek;
	  }
	  
	  public static ItemStack getMoneta2(int size) {
	    ItemStack odlamek2 = (new ItemBuilder(Material.LEAD, size))
	    .setTitle(ChatUtil.translateHexColorCodes("&#DE00FF&lP&#CC01ED&lo&#BA02DA&ls&#A903C8&lk&#9704B5&lr&#8505A3&la&#730591&lm&#61067E&li&#50076C&la&#3E0859&lc&#2C0947&lz"))
	    .addLore("")
		.addLore(ChatUtil.translateHexColorCodes("&7Tym itemem możesz handlować"))
		.addLore(ChatUtil.translateHexColorCodes("&7z villagerami na spawn"))
		.addLore("")
	    .setGlow().ToItemStack();
	    return odlamek2;
	  }
	  
	  public static ItemStack getMoneta3(int size) {
	    ItemStack odlamek3 = (new ItemBuilder(Material.CRIMSON_FUNGUS, size))
	    .setTitle(ChatUtil.translateHexColorCodes("&#C03450&lR&#BB3350&lu&#B53250&lb&#B03151&li&#AA3051&ln&#A52F51&lk&#A02E51&lo&#9A2D52&lw&#952C52&ly &#902B52&lg&#8A2A52&lr&#852953&lz&#7F2853&ly&#7A2753&lb"))
	    .addLore("")
		.addLore(ChatUtil.translateHexColorCodes("&7Tym itemem możesz handlować"))
		.addLore(ChatUtil.translateHexColorCodes("&7z villagerami na spawn"))
		.addLore("")
	    .setGlow().ToItemStack();
	    return odlamek3;
	  }
	  
	  public static ItemStack getMoneta4(int size) {
	    ItemStack odlamek4 = (new ItemBuilder(Material.WITHER_ROSE, size))
	    .setTitle(ChatUtil.translateHexColorCodes("&#FF7400&lR&#F87405&ló&#F1750B&lz&#EB7510&la &#E47615&lU&#DD761B&lp&#D67720&la&#CF7726&ld&#C8782B&lł&#C27830&le&#BB7936&lg&#B4793B&lo"))
	    .addLore("")
		.addLore(ChatUtil.translateHexColorCodes("&7Tym itemem możesz handlować"))
		.addLore(ChatUtil.translateHexColorCodes("&7z villagerami na spawn"))
		.addLore("")
	    .setGlow().ToItemStack();
	    return odlamek4;
	  }
	  
	  public static ItemStack getMoneta5(int size) {
	    ItemStack odlamek5 = (new ItemBuilder(Material.HORN_CORAL, size))
	    .setTitle(ChatUtil.translateHexColorCodes("&#FFF000&lK&#FCE902&lo&#F9E205&lr&#F6DB07&la&#F2D40A&ll&#EFCD0C&lo&#ECC60F&lw&#E9BE11&ly &#E6B714&lp&#E3B016&lr&#DFA919&lo&#DCA21B&lm&#D99B1E&ly&#D69420&lk"))
	    .addLore("")
		.addLore(ChatUtil.translateHexColorCodes("&7Tym itemem możesz handlować"))
		.addLore(ChatUtil.translateHexColorCodes("&7z villagerami na spawn"))
		.addLore("")
	    .setGlow().ToItemStack();
	    return odlamek5;
	  }
	  
	  public static ItemStack getMoneta6(int size) {
	    ItemStack odlamek5 = (new ItemBuilder(Material.NAUTILUS_SHELL, size))
		.setTitle(ChatUtil.translateHexColorCodes("&#fbf9e5&lS&#fbf9e0&lk&#fbf9da&lr&#fbf9d5&lo&#fcf9cf&lu&#fcf9ca&lp&#fcf9c5&la &#fcf9bf&lN&#fcf9ba&la&#fcf9b4&lu&#fcf9af&lt&#fcf9aa&li&#fdf9a4&ll&#fdf99f&lu&#fdf999&ls&#fdf994&la"))
		.addLore("")
		.addLore(ChatUtil.translateHexColorCodes("&7Tym itemem możesz handlować"))
		.addLore(ChatUtil.translateHexColorCodes("&7z villagerami na spawn"))
		.addLore("")
		.setGlow().ToItemStack();
		 return odlamek5;
		  }
	  
	  
	  public static ItemStack getMoneta7(int size) {
	   ItemStack odlamek5 = (new ItemBuilder(Material.SLIME_BALL, size))
	   .setTitle(ChatUtil.translateHexColorCodes("&#93ec22&lL&#88e11e&le&#7cd51a&lp&#71ca15&lk&#65be11&la &#5ab30d&lK&#4ea709&lu&#439c04&ll&#379000&la"))
	   .addLore("")
	   .addLore(ChatUtil.translateHexColorCodes("&7Tym itemem możesz handlować"))
	   .addLore(ChatUtil.translateHexColorCodes("&7z villagerami na spawn"))
	   .addLore("")
	   .setGlow().ToItemStack();
	   return odlamek5;
		  }
	  
	  
	  public static ItemStack getMoneta8(int size) {
	    ItemStack odlamek5 = (new ItemBuilder(Material.BLACK_DYE, size))
		.setTitle(ChatUtil.translateHexColorCodes("&#848484&lS&#888c8b&lz&#8d9392&la&#919b9a&lr&#95a2a1&ly &#9aaaa8&lP&#9eb1af&ly&#a2b9b7&ll&#a7c0be&le&#abc8c5&lk"))
		.addLore("")
		.addLore(ChatUtil.translateHexColorCodes("&7Tym itemem możesz handlować"))
		.addLore(ChatUtil.translateHexColorCodes("&7z villagerami na spawn"))
		.addLore("")
		.setGlow().ToItemStack();
		return odlamek5;
		  }
	  
	  public static ItemStack getUsuwacz(int size) {
	    ItemStack usuwacz = (new ItemBuilder(Material.BLAZE_ROD, size))
	    .setTitle(ChatUtil.fixColor("&6&lUsuwacz pajęczyn"))
	    .addLore(ChatUtil.fixColor(""))
	    .addLore(ChatUtil.fixColor("&7Po kliknieciu Usuwa pajęczyny z Twojej drogi"))
	    .addLore("")
	    .setGlow().ToItemStack();
	    return usuwacz;
	  }
	  
	  public static ItemStack getodlamek(int size) {
	    ItemStack usuwacz = (new ItemBuilder(Material.PRISMARINE_SHARD, size))
	    .setTitle(ChatUtil.translateHexColorCodes("&#01ffff&lK&#03f6fc&la&#05edf9&lm&#07e4f6&li&#09dbf3&le&#0ad2f0&ln &#0cc9ed&lf&#0ec0ea&li&#10b7e7&ll&#12ade3&lo&#14a4e0&lz&#169bdd&lo&#1892da&lf&#1989d7&li&#1b80d4&lc&#1d77d1&lz&#1f6ece&ln&#2165cb&ly"))
	    .setGlow().ToItemStack();
		return usuwacz;
		  }
	  
	  public static ItemStack getVoucherVIP(int size) {
	    ItemStack vip = (new ItemBuilder(Material.PAPER, size))
	    .setTitle(ChatUtil.fixColor("&e&lVoucher VIP"))
	    .addLore("")
	    .addLore("")
		.addLore(ChatUtil.fixColor("&7Kliknij aby aktywować range"))
	    .addLore("")
	    .setGlow().ToItemStack();
	    return vip;
	  }

	public static ItemStack getVoucherSVIP(int size) {
		ItemStack vip = (new ItemBuilder(Material.PAPER, size))
				.setTitle(ChatUtil.fixColor("&e&lVoucher SVIP"))
				.addLore("")
				.addLore("")
				.addLore(ChatUtil.fixColor("&7Kliknij aby aktywować range"))
				.addLore("")
				.setGlow().ToItemStack();
		return vip;
	}

	public static ItemStack getVoucherSPONSOR(int size) {
		ItemStack vip = (new ItemBuilder(Material.PAPER, size))
				.setTitle(ChatUtil.fixColor("&e&lVoucher SPONSOR"))
				.addLore("")
				.addLore("")
				.addLore(ChatUtil.fixColor("&7Kliknij aby aktywować range"))
				.addLore("")
				.setGlow().ToItemStack();
		return vip;
	}

    public static ItemStack getrozdzkawiatr(int size) {
		ItemStack rozdzkawiatr = (new ItemBuilder(Material.STICK, size))
				.setTitle(ChatUtil.fixColor("&e&lRozdzka Wiatr"))
				.addLore("")
				.addLore("")
				.addLore(ChatUtil.fixColor("&7Kliknij aby odrzucic gracza w powietrze!"))
				.addLore("")
				.setGlow().ToItemStack();
		return rozdzkawiatr;
	}

}
