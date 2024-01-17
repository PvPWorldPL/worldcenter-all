package pl.textr.boxpvp.listeners.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class WarpListener implements Listener {

    private static Location PVP_LOCATION;
    private static Location SPAWN_LOCATION;
    private static Location KOPALNIA_LOCATION;
    private static Location KOPALNIAPREMIUM_LOCATION;
    private static Location AFK;
	
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onClick2(final InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        final int slot = e.getSlot();

        if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&7WARPY"))) {
            e.setCancelled(true);
            
            if (slot == 11) {
                if (KOPALNIA_LOCATION == null) {
                    KOPALNIA_LOCATION = Main.getPlugin().getConfiguration().getKopalniaLocation();
                }
                p.teleport(KOPALNIA_LOCATION);
                return;
            }
            
            if (slot == 10) {
                if (p.hasPermission("core.premium")) {
                    if (KOPALNIAPREMIUM_LOCATION == null) {
                        KOPALNIAPREMIUM_LOCATION = Main.getPlugin().getConfiguration().getKopalniapremiumLocation();
                    }
                    p.teleport(KOPALNIAPREMIUM_LOCATION);
                    return;
                } else {
                    ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ta strefa jest dostepna od rang &6premium"));
                }
                return;
            }
            
	
            
            if (slot == 13) {
               if (AFK == null) {
                  
                    AFK = Main.getPlugin().getConfiguration().getAfkLocation();
                    SPAWN_LOCATION = new Location(Bukkit.getWorld("world"), -24, 37, 31, 0.3f, 1.2f);
              
               }
                p.teleport(AFK);
                return;
            }
            
            if (slot == 16) {
                if (SPAWN_LOCATION == null) {
                	SPAWN_LOCATION = Main.getPlugin().getConfiguration().getSpawnLocation();
                }
                p.teleport(SPAWN_LOCATION);
                return;
            }
            
            
            if (slot == 15) {
                if (PVP_LOCATION == null) {
                    PVP_LOCATION = Main.getPlugin().getConfiguration().getPvpLocation();
                }
                p.teleport(PVP_LOCATION);
            }
        }
    }
}