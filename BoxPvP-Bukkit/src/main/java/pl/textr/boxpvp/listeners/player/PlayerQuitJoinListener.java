package pl.textr.boxpvp.listeners.player;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import api.managers.UserAccountManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class PlayerQuitJoinListener implements Listener {

	  private static Location SPAWN_LOCATION;
	  

	  @EventHandler
	  public void onJoin(PlayerJoinEvent event) {
	      Player player = event.getPlayer();
	      event.setJoinMessage(null);
	      ChatUtil.sendTitle(player, "", "§7Pomyślnie przeteleportowano na §f" + Main.getPlugin().getConfiguration().boxpvpName());
	      UserAccountManager.downloadPlayerInfo(player);
	     this.handle(player);
	  }

	  public  void handle (Player player) {
		  Bukkit.getScheduler().runTaskLater(Main.getPlugin(),
				  () -> {
					  if (player.getWorld().equals(Bukkit.getWorld("pvp"))) {
						  if (SPAWN_LOCATION == null) {
							  SPAWN_LOCATION = Main.getPlugin().getConfiguration().getSpawnLocation();
						  }
						  player.teleport(SPAWN_LOCATION);
					  }
				  },
				  10L
		  );
	  }

	@EventHandler
	public void onKick(PlayerKickEvent e) {
e.setLeaveMessage(null);
	}

	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
	    e.setQuitMessage(null);

	}
}
