package pl.textr.lobby.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.textr.lobby.Lobby;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();

        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && !item.getType().isAir()) {
            String materialName = Lobby.getInstance().cfg.getString("hotbarItem.material");
            String displayName = Lobby.getInstance().getString("hotbarItem.displayname");

            if (materialName != null && displayName != null && item.getType() == Material.getMaterial(materialName) &&
                    item.hasItemMeta() && item.getItemMeta().hasDisplayName() &&
                    item.getItemMeta().getDisplayName().equals(displayName)) {
                e.setCancelled(true);
                Lobby.getInstance().openGUI(p);
            }
        }
    }
}
