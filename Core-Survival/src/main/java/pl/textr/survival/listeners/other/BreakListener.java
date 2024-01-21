package pl.textr.survival.listeners.other;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pl.textr.survival.Main;
import pl.textr.survival.utils.RandomUtil;


public class BreakListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void drop2inventory(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (event.isCancelled()) {
			event.setCancelled(true);
			return;
		}

		if (Main.getPlugin().getConfiguration().turbodrop > System.currentTimeMillis()) {
			int turboMultiplier = Main.getPlugin().getConfiguration().turbodropmnoznik();
			for (int i = 0; i < turboMultiplier; i++) {
				for (ItemStack item : event.getBlock().getDrops()) {
					ItemStack clonedItem = new ItemStack(item);
					player.getInventory().addItem(clonedItem);
				}
			}

			event.setDropItems(false);
			int expToGive = RandomUtil.getRandInt(4, 8);
			player.giveExpLevels(expToGive); // Użyj giveExpLevels zamiast giveExp
		}
	}
}
