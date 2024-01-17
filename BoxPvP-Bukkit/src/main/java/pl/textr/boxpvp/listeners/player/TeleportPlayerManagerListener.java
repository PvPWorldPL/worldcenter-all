package pl.textr.boxpvp.listeners.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import api.data.UserProfile;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.TimeUtil;
import pl.textr.boxpvp.utils.teleport.TeleportCallback;

public class TeleportPlayerManagerListener implements Listener
{
   
	   private static HashMap<UUID, TimerTask> tasks = new HashMap<>();
    
 
    
    public static void addTask(final Player player, final UserProfile user, final TeleportCallback<Player> call, final int time) {
        if (player.hasPermission("core.cmd.helper")) {
            call.success(player);
            return;
        }
        TimerTask t = TeleportPlayerManagerListener.tasks.get(player.getUniqueId());
        if (t != null) {
            t.cancel(player);
            return;
        }
        t = new TimerTask(player.getUniqueId(), call);
        TeleportPlayerManagerListener.tasks.put(player.getUniqueId(), t);
        t.runTaskLater(Main.getPlugin(), TimeUtil.SECOND.getTick(time));
    }
    
    private static void cancel(final TimerTask task, final Player player) {
        task.cancel(player);
        TeleportPlayerManagerListener.tasks.remove(player.getUniqueId());
    }
    
    @EventHandler
    public void onMove(final PlayerMoveEvent event) {
        if (event.getFrom().getBlockX() == event.getTo().getBlockX() && event.getFrom().getBlockY() == event.getTo().getBlockY() && event.getFrom().getBlockZ() == event.getTo().getBlockZ()) {
            return;
        }
        final TimerTask t = TeleportPlayerManagerListener.tasks.get(event.getPlayer().getUniqueId());
        if (t != null) {
            cancel(t, event.getPlayer());
        }
    }
    
    @EventHandler
    public void onDamage(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player)event.getEntity();
            final TimerTask t = TeleportPlayerManagerListener.tasks.get(player.getUniqueId());
            if (t != null) {
                cancel(t, player);
            }
        }
    }
    
    @EventHandler
    public void interakcja(final PlayerInteractEvent event) {
        if (event.getPlayer() instanceof Player) {
            final Player player = event.getPlayer();
            final TimerTask t = TeleportPlayerManagerListener.tasks.get(player.getUniqueId());
            if (t != null) {
                cancel(t, player);
            }
        }
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final TimerTask t = TeleportPlayerManagerListener.tasks.get(player.getUniqueId());
        if (t != null) {
            cancel(t, player);
        }
    }
    
    public static class TimerTask extends BukkitRunnable
    {
        private final UUID player;
        private final TeleportCallback<Player> call;
        
        public void run() {
            this.call.success(Bukkit.getPlayer(this.player));
            TeleportPlayerManagerListener.tasks.remove(this.player);
        }
        
        public void cancel(final Player player) {
            super.cancel();
            this.call.error(player);
        }
        
        public TimerTask(final UUID player, final TeleportCallback<Player> call) {
            this.player = player;
            this.call = call;
        }
        
        public UUID getPlayer() {
            return this.player;
        }
    }
}
