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

	public static void convertMoneyToCurrency(Player player, ItemStack item, Double amount) {
		UserProfile userProfile = UserAccountManager.getUser(player);

		if (player.getInventory().containsAtLeast(new ItemStack(item), 1)) {
			int x = 0;
			while (x < 2304) {
				if (!player.getInventory().containsAtLeast(new ItemStack(item), 1)) {
					return;
				}
				player.getInventory().removeItem(new ItemStack(item));
				userProfile.addBalance(amount);
				userProfile.save();
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



	public static void transformMaterial(Player p, Material item, Material item2) {
		for (int x = 0; x < 256; x++) { //domyslnie wartosc 9
			if (p.getInventory().containsAtLeast(new ItemStack(item), 64)) {
				p.getInventory().removeItem(new ItemStack(item, 64));
				p.getInventory().addItem(new ItemStack(item2, 1));
			}
		}
	}

	public static void convertMoneyToOtherMoney(Player p, ItemStack item, ItemStack item2) {
		for (int x = 0; x < 256; x++) {
			if (p.getInventory().containsAtLeast(new ItemStack(item), 64)) {
				p.getInventory().removeItem(new ItemStack(item));
				p.getInventory().addItem(new ItemStack(item2));
			}
		}
	}

	    public static void convertBlockToMoney(Player p, Material item, Integer amount, ItemStack item2) {
	      for (int x = 0; x < 256; x++) {
	        if (p.getInventory().containsAtLeast(new ItemStack(item), amount)) {
	          p.getInventory().removeItem(new ItemStack(item, amount));
	          p.getInventory().addItem(new ItemStack(item2));
	        } 
	      } 
	    }
	    

	    
	  public static ItemStack getMoneta1(int size) {
		  ItemStack odlamek = (new ItemBuilder(Material.LIME_DYE, size))
				  .setTitle(ChatUtil.translateHexColorCodes("&#30ff38&lʀ&#2ef636&lᴜ&#2cec34&lʙ&#2ae331&lɪ&#28d92f&lɴ &#26d02d&ls&#24c62b&lᴢ&#22bd29&lᴍ&#21b326&lᴀ&#1faa24&lʀ&#1da022&lᴀ&#1b9720&lɢ&#198d1e&lᴅ&#17841b&lᴏ&#157a19&lᴡ&#137117&lʏ &8(&6✪1&8)"))
				  .addLore(ChatUtil.fixColor("&7(ᴍᴏɴᴇᴛᴀ ᴅᴏ ʜᴀɴᴅʟᴏᴡᴀɴɪᴀ)"))
				  .addLore(ChatUtil.translateHexColorCodes(""))
				  .addLore(ChatUtil.translateHexColorCodes(" &8&l・&fᴊᴇsᴛ ᴛᴏ ᴏᴅʟᴀᴍᴇᴋ &5ʀᴜʙɪɴᴜ &#30ff38s&#2ef736ᴢ&#2def34ᴍ&#2be733ᴀ&#2adf31ʀ&#28d82fᴀ&#26d02dɢ&#25c82bᴅ&#23c029ᴏ&#22b828ᴡ&#20b026ᴇ&#1ea824ᴊ &#1da022ᴘ&#1b9820ʟ&#19911eᴀ&#18891dɴ&#16811bᴇ&#157919ᴛ&#137117ʏ"))
				  .addLore(ChatUtil.translateHexColorCodes("&8&l・&fᴋᴛᴏʀʏ ᴍᴏᴢᴇsᴢ sᴘʀᴢᴇᴅᴀᴄ ᴘᴏᴅ ᴋᴏᴍᴇɴᴅᴀ &2/sᴋʟᴇᴘ"))
				  .addLore("")
				  .setGlow().ToItemStack();
		  return odlamek;
	  }
	  
	  public static ItemStack getMoneta2(int size) {
		  ItemStack odlamek2 = (new ItemBuilder(Material.LIGHT_BLUE_DYE, size))
				  .setTitle(ChatUtil.translateHexColorCodes("&#5be1ff&lʀ&#56d9fb&lᴜ&#51d0f6&lʙ&#4cc8f2&lɪ&#46c0ed&lɴ &#41b8e9&lʟ&#3cafe4&lᴀ&#37a7e0&lᴢ&#329fdb&lᴜ&#2d96d7&lʀ&#288ed2&lʏ&#2286ce&lᴛ&#1d7ec9&lᴏ&#1875c5&lᴡ&#136dc0&lʏ &8(&6✪2&8)"))
				  .addLore(ChatUtil.fixColor("&7(ᴍᴏɴᴇᴛᴀ ᴅᴏ ʜᴀɴᴅʟᴏᴡᴀɴɪᴀ)"))
				  .addLore(ChatUtil.translateHexColorCodes(""))
				  .addLore(ChatUtil.translateHexColorCodes("&8&l・&fᴊᴇsᴛ ᴛᴏ ᴏᴅʟᴀᴍᴇᴋ &5ʀᴜʙɪɴᴜ &#5be1ffʟ&#57dafbᴀ&#53d3f8ᴢ&#4ecdf4ᴜ&#4ac6f0ʀ&#46bfecʏ&#42b8e9ᴛ&#3db1e5ᴏ&#39aae1ᴡ&#35a4deᴇ&#319ddaᴊ &#2c96d6ᴘ&#288fd3ʟ&#2488cfᴀ&#2081cbɴ&#1b7bc7ᴇ&#1774c4ᴛ&#136dc0ʏ"))
				  .addLore(ChatUtil.translateHexColorCodes("&8&l・&fᴋᴛᴏʀʏ ᴍᴏᴢᴇsᴢ sᴘʀᴢᴇᴅᴀᴄ ᴘᴏᴅ ᴋᴏᴍᴇɴᴅᴀ &2/sᴋʟᴇᴘ"))
				  .addLore("")
				  .setGlow().ToItemStack();
		  return odlamek2;
	  }
	  
	  public static ItemStack getMoneta3(int size) {
		  ItemStack odlamek3 = (new ItemBuilder(Material.MAGENTA_DYE, size))
				  .setTitle(ChatUtil.translateHexColorCodes("&#fa1eff&lʀ&#f21ffb&lᴜ&#eb20f7&lʙ&#e321f2&lɪ&#dc22ee&lɴ &#d423ea&lʙ&#cc24e6&lᴜ&#c525e2&lʀ&#bd27dd&ls&#b628d9&lᴢ&#ae29d5&lᴛ&#a62ad1&lʏ&#9f2bcd&lɴ&#972cc8&lᴏ&#902dc4&lᴡ&#882ec0&lʏ  &8(&6✪3&8)"))
				  .addLore(ChatUtil.fixColor("&7(ᴍᴏɴᴇᴛᴀ ᴅᴏ ʜᴀɴᴅʟᴏᴡᴀɴɪᴀ)"))
				  .addLore(ChatUtil.translateHexColorCodes(""))
				  .addLore(ChatUtil.translateHexColorCodes("&8&l・&fᴊᴇsᴛ ᴛᴏ ᴏᴅʟᴀᴍᴇᴋ &5ʀᴜʙɪɴᴜ &#fa1effʙ&#f41ffcᴜ&#ed20f8ʀ&#e721f5s&#e122f1ᴢ&#da22eeᴛ&#d423eaʏ&#ce24e7ɴ&#c725e3ᴏ&#c126e0ᴡ&#bb27dcᴇ&#b428d9ᴊ &#ae29d5ᴘ&#a82ad2ʟ&#a12aceᴀ&#9b2bcbɴ&#952cc7ᴇ&#8e2dc4ᴛ&#882ec0ʏ"))
				  .addLore(ChatUtil.translateHexColorCodes("&8&l・&fᴋᴛᴏʀʏ ᴍᴏᴢᴇsᴢ sᴘʀᴢᴇᴅᴀᴄ ᴘᴏᴅ ᴋᴏᴍᴇɴᴅᴀ &2/sᴋʟᴇᴘ"))
				  .addLore("")
				  .setGlow().ToItemStack();
		  return odlamek3;
	  }
	  
	  public static ItemStack getMoneta4(int size) {
		ItemStack odlamek4 = (new ItemBuilder(Material.BLUE_DYE, size))
		 .setTitle(ChatUtil.translateHexColorCodes("&#1400fb&lʀ&#1803f9&lᴜ&#1b06f8&lʙ&#1f09f6&lɪ&#220cf5&lɴ &#260ff3&lɢ&#2912f1&lʀ&#2d15f0&lᴀ&#3018ee&lɴ&#341bec&lᴀ&#371eeb&lᴛ&#3b21e9&lᴏ&#3e24e8&lᴡ&#4227e6&lʏ &8(&6✪4&8)"))
				.addLore(ChatUtil.fixColor("&7(ᴍᴏɴᴇᴛᴀ ᴅᴏ ʜᴀɴᴅʟᴏᴡᴀɴɪᴀ)"))
		 .addLore(ChatUtil.translateHexColorCodes(""))
		 .addLore(ChatUtil.translateHexColorCodes("&8&l・&fᴊᴇsᴛ ᴛᴏ ᴏᴅʟᴀᴍᴇᴋ &5ʀᴜʙɪɴᴜ &#1400fbɢ&#1702faʀ&#1a05f8ᴀ&#1d07f7ɴ&#200af6ᴀ&#220cf4ᴛ&#250ff3ᴏ&#2811f2ᴡ&#2b14f1ᴇ&#2e16efᴊ &#3118eeɢ&#341bedᴡ&#371debɪ&#3920eaᴀ&#3c22e9ᴢ&#3f25e7ᴅ&#4227e6ʏ"))
		 .addLore(ChatUtil.translateHexColorCodes("&8&l・&fᴋᴛᴏʀʏ ᴍᴏᴢᴇsᴢ sᴘʀᴢᴇᴅᴀᴄ ᴘᴏᴅ ᴋᴏᴍᴇɴᴅᴀ &2/sᴋʟᴇᴘ"))
		 .addLore("")
		 .setGlow().ToItemStack();
	    return odlamek4;
	  }
	  
	  public static ItemStack getMoneta5(int size) {
	    ItemStack odlamek5 = (new ItemBuilder(Material.RED_DYE, size))
	    .setTitle(ChatUtil.translateHexColorCodes("&#fb2020&lʀ&#fb2421&lᴜ&#fb2821&lʙ&#fb2d22&lɪ&#fc3122&lɴ &#fc3523&ls&#fc3923&lᴢ&#fc3e24&lᴋ&#fc4225&lᴀ&#fc4625&lʀ&#fc4a26&lʟ&#fd4e26&lᴀ&#fd5327&lᴛ&#fd5727&lɴ&#fd5b28&lʏ &8(&6✪5&8)"))
		.addLore(ChatUtil.fixColor("&7(ᴍᴏɴᴇᴛᴀ ᴅᴏ ʜᴀɴᴅʟᴏᴡᴀɴɪᴀ)"))
		.addLore(ChatUtil.translateHexColorCodes(""))
		.addLore(ChatUtil.translateHexColorCodes("&8&l・&fᴊᴇsᴛ ᴛᴏ ᴏᴅʟᴀᴍᴇᴋ &5ʀᴜʙɪɴᴜ &#fb2020s&#fb2320ᴢ&#fb2721ᴋ&#fb2a21ᴀ&#fb2e22ʀ&#fc3122ʟ&#fc3523ᴀ&#fc3823ᴛ&#fc3c24ɴ&#fc3f24ᴇ&#fc4325ᴊ &#fc4625ᴘ&#fc4a26ʟ&#fd4d26ᴀ&#fd5127ɴ&#fd5427ᴇ&#fd5828ᴛ&#fd5b28ʏ"))
		.addLore(ChatUtil.translateHexColorCodes("&8&l・&fᴋᴛᴏʀʏ ᴍᴏᴢᴇsᴢ sᴘʀᴢᴇᴅᴀᴄ ᴘᴏᴅ ᴋᴏᴍᴇɴᴅᴀ &2/sᴋʟᴇᴘ"))
		.addLore("")
	    .setGlow().ToItemStack();
	    return odlamek5;
	  }
	  
	  public static ItemStack getMoneta6(int size) {
	    ItemStack odlamek5 = (new ItemBuilder(Material.PAPER, size))
		.addLore("")
		.setGlow().ToItemStack();
		 return odlamek5;
		  }


	public static ItemStack getMoneta7(int size) {
		ItemStack odlamek5 = (new ItemBuilder(Material.PAPER, size))
				.addLore("")
				.setGlow().ToItemStack();
		return odlamek5;
	}


	public static ItemStack getMoneta8(int size) {
		ItemStack odlamek5 = (new ItemBuilder(Material.PAPER, size))
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
