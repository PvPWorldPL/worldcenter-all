package pl.textr.boxpvp.tasks;


import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

import pl.textr.boxpvp.utils.ChatUtil;


public class ItemClearTask implements Runnable {


	@Override
	public void run() {
	    Bukkit.getWorlds().forEach(world -> world.getEntitiesByClass(Item.class).forEach(Entity::remove));
	    Bukkit.broadcastMessage("");
	    Bukkit.broadcastMessage(ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Wszystkie itemy z ziemi zostały usunięte!"));
	    Bukkit.broadcastMessage("");
	}
}