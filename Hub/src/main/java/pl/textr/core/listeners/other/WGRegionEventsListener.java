package pl.textr.core.listeners.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import api.region.MovementWay;
import api.region.events.RegionEnterEvent;
import api.region.events.RegionEnteredEvent;
import api.region.events.RegionLeaveEvent;
import api.region.events.RegionLeftEvent;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.utils.other.ChatUtil;

public class WGRegionEventsListener implements Listener
{
 
    private static final Map<Player, Set<ProtectedRegion>> playerRegions;
    
    
   static {
       playerRegions = new HashMap<Player, Set<ProtectedRegion>>();
    }
   
   
   
   @EventHandler
   public void onRegionEntered(final RegionEnteredEvent event) {
       final Player p = event.getPlayer();
       if (event.getRegion().getId().equals("srodek")) {
           p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Jestes na srodku spawnu zostales &cukryty")));
           for (final Player otherPlayer : Bukkit.getOnlinePlayers()) {
               p.hidePlayer(otherPlayer);
               otherPlayer.hidePlayer(p);

           }
       }
   }



   @EventHandler
   public void onRegionLeft(final RegionLeftEvent event) {
       final Player player = event.getPlayer();
       if (event.getRegion().getId().equals("srodek")) {
           player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Opusciles srodek spawnu, zostales odkryty!")));
           for (final Player otherPlayer : Bukkit.getOnlinePlayers()) {
        	   player.showPlayer(otherPlayer);
               otherPlayer.showPlayer(player);
           }
       }
   }
  

   

   @EventHandler
   public void Entrada(final PlayerJoinEvent e) {
       final Player p = e.getPlayer();
       final RegionContainer rg = WorldGuard.getInstance().getPlatform().getRegionContainer();
       RegionManager region = rg.get(BukkitAdapter.adapt(p.getLocation().getWorld()));
       if (region.equals("srodek")) {
           for (final Player onp : Bukkit.getOnlinePlayers()) {
               p.hidePlayer(onp);
               onp.hidePlayer(p);
           }
       }
   }

   
	
   @EventHandler
   public static void UnhidePlayers(final PlayerTeleportEvent event) {
       final Player p = event.getPlayer();
       for (final Player otherPlayer : Bukkit.getOnlinePlayers()) {
        
               otherPlayer.showPlayer(p);
               p.showPlayer(otherPlayer);
           }
       
   }


    
    @EventHandler
    public void onPlayerKick(final PlayerKickEvent e) {
        final Set<ProtectedRegion> regions = playerRegions.remove(e.getPlayer());
        if (regions != null) {
            for (final ProtectedRegion region : regions) {
                final RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, e.getPlayer(), MovementWay.DISCONNECT, e);
                final RegionLeftEvent leftEvent = new RegionLeftEvent(region, e.getPlayer(), MovementWay.DISCONNECT, e);
                Bukkit.getPluginManager().callEvent(leaveEvent);
                Bukkit.getPluginManager().callEvent(leftEvent);
            }
        }
    }
    
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e) {
        final Set<ProtectedRegion> regions = playerRegions.remove(e.getPlayer());
        if (regions != null) {
            for (final ProtectedRegion region : regions) {
                final RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, e.getPlayer(), MovementWay.DISCONNECT, e);
                final RegionLeftEvent leftEvent = new RegionLeftEvent(region, e.getPlayer(), MovementWay.DISCONNECT, e);
                Bukkit.getPluginManager().callEvent(leaveEvent);
                Bukkit.getPluginManager().callEvent(leftEvent);
            }
        }
    }
    
    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent e) {
        e.setCancelled(this.updateRegions(e.getPlayer(), MovementWay.MOVE, e.getTo(), e));
    }
    
    @EventHandler
    public void onPlayerTeleport(final PlayerTeleportEvent e) {
        e.setCancelled(this.updateRegions(e.getPlayer(), MovementWay.TELEPORT, e.getTo(), e));
    }
    
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        this.updateRegions(e.getPlayer(), MovementWay.SPAWN, e.getPlayer().getLocation(), e);
    }
    
    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent e) {
        this.updateRegions(e.getPlayer(), MovementWay.SPAWN, e.getRespawnLocation(), e);
    }
    
    private synchronized boolean updateRegions(final Player player, final MovementWay movement, final Location to, final PlayerEvent event) {
        Set<ProtectedRegion> regions;
        if (playerRegions.get(player) == null) {
            regions = new HashSet<ProtectedRegion>();
        } else {
            regions = new HashSet<ProtectedRegion>(playerRegions.get(player));
        }
        final Set<ProtectedRegion> oldRegions = new HashSet<ProtectedRegion>(regions);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        final RegionManager rm = container.get(BukkitAdapter.adapt(to.getWorld()));
        if (rm == null) {
            return false;
        }

        Set<ProtectedRegion> appRegions = Objects.requireNonNull(rm).getApplicableRegions(BlockVector3.at(to.getX(), to.getY(), to.getZ())).getRegions();

        final ProtectedRegion globalRegion = rm.getRegion("__global__");
        if (globalRegion != null) {
            appRegions.add(globalRegion);
        }
        for (final ProtectedRegion region : appRegions) {
            if (!regions.contains(region)) {
                final RegionEnterEvent e = new RegionEnterEvent(region, player, movement, event);
                Bukkit.getServer().getPluginManager().callEvent(e);
                if (e.isCancelled()) {
                    regions.clear();
                    regions.addAll(oldRegions);
                    return true;
                }
                Bukkit.getScheduler().runTaskLater(LobbyPlugin.getPlugin(), new Runnable() {
                    public void run() {
                        final RegionEnteredEvent e = new RegionEnteredEvent(region, player, movement, event);
                        Bukkit.getServer().getPluginManager().callEvent(e);
                    }
                }, 1L);
                regions.add(region);
            }
        }
        final Iterator<ProtectedRegion> itr = regions.iterator();
        while (itr.hasNext()) {
            final ProtectedRegion region2 = itr.next();
            if (!appRegions.contains(region2)) {
                if (rm.getRegion(region2.getId()) != region2) {
                    itr.remove();
                } else {
                    final RegionLeaveEvent e2 = new RegionLeaveEvent(region2, player, movement, event);
                    Bukkit.getServer().getPluginManager().callEvent(e2);
                    if (e2.isCancelled()) {
                        regions.clear();
                        regions.addAll(oldRegions);
                        return true;
                    }
                    Bukkit.getScheduler().runTaskLater(LobbyPlugin.getPlugin(), new Runnable() {
                        public void run() {
                            final RegionLeftEvent e = new RegionLeftEvent(region2, player, movement, event);
                            Bukkit.getServer().getPluginManager().callEvent(e);
                        }
                    }, 1L);
                    itr.remove();
                }
            }
        }
        playerRegions.put(player, regions);
        return false;
    }
}