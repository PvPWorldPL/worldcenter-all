package pl.textr.listeners;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.textr.Main;
import pl.textr.utils.MessageFormatter;


public class BlockActionListener implements Listener {

    @EventHandler
    public void handleBlockBreak(BlockBreakEvent event) {
        if (Main.getPlugin().getConfiguration().Worlds.contains(event.getPlayer().getWorld().getName())) {
            if (Main.getPlugin().getConfiguration().allowedBreakBlocks.contains(event.getBlock().getType())) {
                return;
            }
            if (!event.getPlayer().hasPermission(Main.getPlugin().getConfiguration().permissionBreak)) {
                event.setCancelled(true);
                MessageFormatter.sendFormattedMessage(event.getPlayer(), MessageFormatter.translateHexColorCodes(Main.getPlugin().getConfiguration().BlockBreakMessages));
            }
        }
    }
        @EventHandler
        public void handleBlockPlace (BlockPlaceEvent event ){
            if (Main.getPlugin().getConfiguration().Worlds.contains(event.getPlayer().getWorld().getName())) {
                if (Main.getPlugin().getConfiguration().allowedPlaceBlocks.contains(event.getBlock().getType())) {
                    return;
                }
                if (!event.getPlayer().hasPermission(Main.getPlugin().getConfiguration().permissionPlace)) {
                    event.setCancelled(true);
                    MessageFormatter.sendFormattedMessage(event.getPlayer(), MessageFormatter.translateHexColorCodes(Main.getPlugin().getConfiguration().BlockPlaceMessages));
                }
            }
        }
    }

