package api.regions;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerEvent;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RegionLeaveEvent extends RegionEvent implements Cancellable {
    private boolean cancelled;
    private boolean cancellable;

    public RegionLeaveEvent(final ProtectedRegion region, final Player player, final MovementWay movement, final PlayerEvent parent) {
        super(region, player, movement, parent);
        this.cancelled = false;
        this.cancellable = movement != MovementWay.SPAWN && movement != MovementWay.DISCONNECT;
    }

    public void setCancelled(final boolean cancelled) {
        if (!this.cancellable) {
            return;
        }
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public boolean isCancellable() {
        return this.cancellable;
    }

    protected void setCancellable(final boolean cancellable) {
        if (!(this.cancellable = cancellable)) {
            this.cancelled = false;
        }
    }
}
