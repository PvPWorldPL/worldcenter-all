package pl.textr.core.listeners.other;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.util.Vector;

public class CoreListener implements Listener {

	
	@EventHandler
	public void onBlockPlace(final BlockPlaceEvent e) {
		final Player p = e.getPlayer();
		if (p.getWorld().getName().equals("world") && !p.hasPermission("core.arena.budowanie")) {
			e.setCancelled(true);

		}
	}

	@EventHandler
	public void onBreakknock(final BlockBreakEvent e) {
		final Player p = e.getPlayer();
		if (p.getWorld().getName().equals("world") && !p.hasPermission("core.arena.budowanie")) {
			e.setCancelled(true);

		}
	}

	@EventHandler
	public void onFall(EntityDamageEvent e) {
	if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
	e.setCancelled(true);
	}
	}


	@EventHandler
	public void onWeather(WeatherChangeEvent event) {
	    World world = event.getWorld();
	    if (event.toWeatherState()) {
	        world.setWeatherDuration(0);
	        event.setCancelled(true);
	    }
	}

	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		GameMode gameMode = player.getGameMode();

		if (gameMode == GameMode.ADVENTURE || gameMode == GameMode.SURVIVAL) {
			if (!player.isFlying()) {
				event.setCancelled(true);
				player.setFlying(false);
				launchPlayer(player);
			}
		}
	}


	private void launchPlayer(Player player) {
		Vector vector = player.getLocation().getDirection().normalize().multiply(1.1);
		vector.setY(0.5);
		player.setVelocity(vector);
		// Odtwarzanie dźwięku tylko dla danego gracza
		player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
	}



	@EventHandler
	public void onFoodLevelChangeEvent(final FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}


}
