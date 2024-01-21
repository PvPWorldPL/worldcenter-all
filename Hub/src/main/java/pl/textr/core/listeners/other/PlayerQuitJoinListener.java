package pl.textr.core.listeners.other;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
            SPAWN_LOCATION = new Location(Bukkit.getWorld("World"), -4.480, 63.0, -1.433, 179.3f, -10.3f);
        }
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
