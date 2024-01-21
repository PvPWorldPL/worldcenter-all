package pl.textr.core.listeners.other;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

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



	public void onFoodLevelChangeEvent(final FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}


}
