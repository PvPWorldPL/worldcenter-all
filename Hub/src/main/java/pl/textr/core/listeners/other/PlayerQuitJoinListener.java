package pl.textr.core.listeners.other;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import org.bukkit.scheduler.BukkitRunnable;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.utils.other.ChatUtil;
import pl.textr.core.utils.other.ItemBuild;

public class PlayerQuitJoinListener implements Listener {

    private static Location SPAWN_LOCATION;

	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(final PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		e.setJoinMessage(null);
        p.setGameMode(GameMode.ADVENTURE);
        
        if (SPAWN_LOCATION == null) {
            SPAWN_LOCATION = new Location(Bukkit.getWorld("World"), -0.527, 85.0, -0.471, 179.3f, -10.3f);
        }
		ChatUtil.sendTitle(e.getPlayer(), ChatUtil.translateHexColorCodes("&x&6&b&f&b&5&a&lᴡ&x&6&4&f&b&6&7&lᴏ&x&5&c&f&a&7&3&lʀ&x&5&5&f&a&8&0&lʟ&x&4&e&f&9&8&c&lᴅ&x&4&6&f&9&9&9&lᴄ&x&3&f&f&9&a&5&lᴇ&x&3&8&f&8&b&2&lɴ&x&3&1&f&8&b&e&lᴛ&x&2&9&f&8&c&b&lᴇ&x&2&2&f&7&d&7&lʀ&x&1&b&f&7&e&4&l.&x&1&3&f&6&f&0&lᴘ&x&0&c&f&6&f&d&lʟ"), ChatUtil.translateHexColorCodes("&x&c&9&0&0&f&bT&x&c&5&0&a&f&bw&x&c&0&1&4&f&bo&x&b&c&1&e&f&bj&x&b&7&2&8&f&be &x&b&3&3&2&f&bc&x&a&e&3&c&f&ce&x&a&a&4&6&f&cn&x&a&6&5&0&f&ct&x&a&1&5&a&f&cr&x&9&d&6&4&f&cu&x&9&8&6&e&f&cm &x&9&4&7&8&f&cr&x&8&f&8&2&f&co&x&8&b&8&c&f&cz&x&8&7&9&6&f&cg&x&8&2&a&0&f&dr&x&7&e&a&a&f&dy&x&7&9&b&4&f&dw&x&7&5&b&e&f&dk&x&7&0&c&8&f&di&x&6&c&d&2&f&d!"),20, 60, 20);
       	p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10.0f, 1.0f);
		p.teleport(SPAWN_LOCATION);
	}

	@EventHandler
	public void PlayerKick(final PlayerKickEvent e) {
		e.setLeaveMessage(null);
	

	}


	@EventHandler
	public void PlayerQuit(final PlayerQuitEvent e) {
         e.setQuitMessage(null);
	}
}
