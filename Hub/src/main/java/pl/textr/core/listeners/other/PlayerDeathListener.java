package pl.textr.core.listeners.other;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import pl.textr.core.LobbyPlugin;
import pl.textr.core.utils.other.ChatUtil;
import pl.textr.core.utils.other.ItemBuild;

public class PlayerDeathListener implements Listener {

	   private static Location SPAWN_LOCATION;
	   
	   @EventHandler(priority = EventPriority.HIGH)
	   public void onDeath(PlayerDeathEvent event) {
	       event.setDeathMessage(null);
	       final Player player = event.getEntity();

	       new BukkitRunnable() {
	           public void run() {
	               if (player.isInsideVehicle()) {
	                   player.leaveVehicle();
	               }

	               player.spigot().respawn();
	               if (SPAWN_LOCATION == null) {
	                   SPAWN_LOCATION = new Location(Bukkit.getWorld("World"), -4.480, 63.0, -1.433, 179.3f, -10.3f);
	               }
	               player.teleport(SPAWN_LOCATION);
	           }
	       }.runTaskLater(LobbyPlugin.getPlugin(), 5L);
	   }




}