package pl.textr.boxpvp.listeners.inventory;


import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import api.data.Clans;
import api.managers.ClanManager;
import api.menu.ColorsTagMenu;
import api.redis.packet.guild.UpdateGuildColorTagPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class KolorowyTagGildiaListener implements Listener {


	    
    @EventHandler
    public void onInteract12(final InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&7RANGI"))) {
            e.setCancelled(true);
        }
    }
    	 


 
  
 
	   @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	    public void onClicktagi(final InventoryClickEvent e) {
	        final Player p = (Player) e.getWhoClicked();
	        final Clans clans = ClanManager.getGuild(p);
	        if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.translateHexColorCodes("&#66fb0b&lK&#81fa0f&lO&#9df813&lL&#b8f718&lO&#d4f61c&lR&#eff520&lO&#fdf322&lW&#fef123&lE &#feef23&lT&#feed24&lA&#ffeb24&lG&#ffe925&lI"))) {
	            e.setCancelled(true);

	            int slot = e.getSlot();
	            if (slot == 0) { // Slot dla LIGHT_PURPLE
					if (clans != null && !"#FF00FF".equalsIgnoreCase(clans.isColorTag())) {

						UpdateGuildColorTagPacket UpdateGuildColorTagPacket;
						UpdateGuildColorTagPacket = new UpdateGuildColorTagPacket(clans.getTag(), "#FF00FF");
						Main.getPlugin().getRedisService().publishAsync("UpdateGuildColorTag", UpdateGuildColorTagPacket);

						p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
						ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ustawiłeś tag na &#FF00FFLIGHT_PURPLE"));
					}
				}

	            if (slot == 1) { // Slot dla LIGHT_GREEN
					if (clans != null && !clans.isColorTag().equalsIgnoreCase("#00FF00")) {

						UpdateGuildColorTagPacket UpdateGuildColorTagPacket;
						UpdateGuildColorTagPacket = new UpdateGuildColorTagPacket(clans.getTag(), "#00FF00");
						Main.getPlugin().getRedisService().publishAsync("UpdateGuildColorTag", UpdateGuildColorTagPacket);

						p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
						ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ustawiłeś tag na &#00FF00LIGHT_GREEN"));
					}
				}

	            if (slot == 2) { // Slot dla ORANGE
					if (clans != null && !clans.isColorTag().equalsIgnoreCase("#FF9B06")) {
						if (p.hasPermission("core.color.vip")) {

							UpdateGuildColorTagPacket UpdateGuildColorTagPacket;
							UpdateGuildColorTagPacket = new UpdateGuildColorTagPacket(clans.getTag(), "#FF9B06");
							Main.getPlugin().getRedisService().publishAsync("UpdateGuildColorTag", UpdateGuildColorTagPacket);

							p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
							ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ustawiłeś tag na &#FF9B06GOLD"));
						} else {
							ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Nie posiadasz rangi &6VIP");
						}
					}
				}

	            if (slot == 3) { // Slot dla LIGHT_BLUE
					if (clans != null && !clans.isColorTag().equalsIgnoreCase("#06E1FF")) {
						if (p.hasPermission("core.color.vip")) {

							UpdateGuildColorTagPacket UpdateGuildColorTagPacket;
							UpdateGuildColorTagPacket = new UpdateGuildColorTagPacket(clans.getTag(), "#06E1FF");
							Main.getPlugin().getRedisService().publishAsync("UpdateGuildColorTag", UpdateGuildColorTagPacket);

							ClanManager.downloadGuild(clans.getTag()); //wczytuje gildie z bazy
							p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
							ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ustawiłeś tag na &#06E1FFLIGHT_BLUE"));
						} else {
							ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Nie posiadasz rangi &6VIP");
						}
					}
				}

	            if (slot == 4) { // Slot dla DARK_PURPLE
					if (clans != null && !clans.isColorTag().equalsIgnoreCase("#931095")) {
						if (p.hasPermission("core.color.vip")) {

							UpdateGuildColorTagPacket UpdateGuildColorTagPacket;
							UpdateGuildColorTagPacket = new UpdateGuildColorTagPacket(clans.getTag(), "#931095");
							Main.getPlugin().getRedisService().publishAsync("UpdateGuildColorTag", UpdateGuildColorTagPacket);

							p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
							ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ustawiłeś tag na &#931095DARK_PURPLE"));
						} else {
							ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Nie posiadasz rangi &6VIP &8| &eSVIP");
						}
					}
				}

	            if (slot == 5) { // Slot dla DARK_RED
					if (clans != null && !clans.isColorTag().equalsIgnoreCase("#9B0000")) {
						if (p.hasPermission("core.color.sponsor")) {

							UpdateGuildColorTagPacket UpdateGuildColorTagPacket;
							UpdateGuildColorTagPacket = new UpdateGuildColorTagPacket(clans.getTag(), "#9B0000");
							Main.getPlugin().getRedisService().publishAsync("UpdateGuildColorTag", UpdateGuildColorTagPacket);

							p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
							ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ustawiłeś tag na &#9B0000DARK_RED"));
						} else {
							ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Nie posiadasz rangi &9SPONSOR");
						}
					}
				}

	            if (slot == 6) { // Slot dla DARK_AQUA
					if (clans != null && !clans.isColorTag().equalsIgnoreCase("#1D8AB9")) {
						if (p.hasPermission("core.color.sponsor")) {


							UpdateGuildColorTagPacket UpdateGuildColorTagPacket;
							UpdateGuildColorTagPacket = new UpdateGuildColorTagPacket(clans.getTag(), "#1D8AB9");
							Main.getPlugin().getRedisService().publishAsync("UpdateGuildColorTag", UpdateGuildColorTagPacket);

							p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
							ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ustawiłeś tag na &#1D8AB9DARK_AQUA"));
						} else {
							ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Nie posiadasz rangi &9SPONSOR");
						}
					}
				}

	            if (slot == 7) { // Slot dla DARK_GREEN
					if (clans != null && !clans.isColorTag().equalsIgnoreCase("#208916")) {
						if (p.hasPermission("core.color.sponsor")) {

							UpdateGuildColorTagPacket UpdateGuildColorTagPacket;
							UpdateGuildColorTagPacket = new UpdateGuildColorTagPacket(clans.getTag(), "#208916");
							Main.getPlugin().getRedisService().publishAsync("UpdateGuildColorTag", UpdateGuildColorTagPacket);

							p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
							ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ustawiłeś tag na &#208916DARK_GREEN"));
						} else {
							ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Nie posiadasz rangi &9SPONSOR");
						}
					}
				}

	            if (slot == 8) { // Slot dla DARK_GRAY
					if (clans != null && !clans.isColorTag().equalsIgnoreCase("#414040")) {
						if (p.hasPermission("core.color.sponsor")) {

							UpdateGuildColorTagPacket UpdateGuildColorTagPacket;
							UpdateGuildColorTagPacket = new UpdateGuildColorTagPacket(clans.getTag(), "#414040");
							Main.getPlugin().getRedisService().publishAsync("UpdateGuildColorTag", UpdateGuildColorTagPacket);

							p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
							ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ustawiłeś tag na &#414040DARK_GRAY"));
						} else {
							ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Nie posiadasz rangi &9SPONSOR");
						}
					}
				}

	            ColorsTagMenu.show(p);
	        }
	   }		
}
