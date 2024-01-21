package api.region.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import api.region.MovementWay;

public abstract class RegionEvent extends PlayerEvent
{
    private static final HandlerList handlerList;
    private final ProtectedRegion region;
    private final MovementWay movement;
    public PlayerEvent parentEvent;
    
    public RegionEvent(final ProtectedRegion region, final Player player, final MovementWay movement, final PlayerEvent parent) {
        super(player);
        this.region = region;
        this.movement = movement;
        this.parentEvent = parent;
    }
    
    public HandlerList getHandlers() {
        return RegionEvent.handlerList;
    }
    
    public ProtectedRegion getRegion() {
        return this.region;
    }
    
    public static HandlerList getHandlerList() {
        return RegionEvent.handlerList;
    }
    
    public MovementWay getMovementWay() {
        return this.movement;
    }
    
    public PlayerEvent getParentEvent() {
        return this.parentEvent;
    }
    
    static {
        handlerList = new HandlerList();
    }
}
