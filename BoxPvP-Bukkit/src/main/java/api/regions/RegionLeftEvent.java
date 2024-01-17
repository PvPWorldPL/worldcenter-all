package api.regions;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RegionLeftEvent extends RegionEvent {
    public RegionLeftEvent(final ProtectedRegion region, final Player player, final MovementWay movement, final PlayerEvent parent) {
        super(region, player, movement, parent);
    }
}
