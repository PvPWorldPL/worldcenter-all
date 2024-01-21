package pl.textr.boxpvp.listeners.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import api.data.Clans;
import api.managers.ClanManager;
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

import api.regions.MovementWay;
import api.regions.RegionEnterEvent;
import api.regions.RegionEnteredEvent;
import api.regions.RegionLeaveEvent;
import api.regions.RegionLeftEvent;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.util.Vector;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.tasks.CrystalBossTask;
import pl.textr.boxpvp.tasks.RewardTask;
import pl.textr.boxpvp.utils.ChatUtil;

public class WGRegionEventsListener implements Listener
{
	private static final Map<Player, Set<ProtectedRegion>> playerRegions = new HashMap<>();

   @EventHandler
   public void onRegionEnteredspawn(final RegionEnteredEvent event) {
       final Player p = event.getPlayer();
       if (event.getRegion().getId().equals(Main.getPlugin().getConfiguration().spawnregion())) {
           p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Jestes na srodku spawnu zostales &cukryty")));
           for (final Player otherPlayer : Bukkit.getOnlinePlayers()) {
               p.hidePlayer(Main.getPlugin(),otherPlayer);
               otherPlayer.hidePlayer(Main.getPlugin(),p);
           }
       }
   }
  
   @EventHandler
   public void onRegionEnteredAfk(RegionEnteredEvent event) {
       Player player = event.getPlayer();
       if (event.getRegion().getId().equals("afk")) {
    	player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Wszedłeś do strefy afk")));
          RewardTask.startTask(300, 1800, player);
           for (final Player otherPlayer : Bukkit.getOnlinePlayers()) {
               player.hidePlayer(Main.getPlugin(), otherPlayer);
               otherPlayer.hidePlayer(Main.getPlugin(), player);
           }
       }
   }

    @EventHandler
    public void onRegionEnteredCrystal(RegionEnteredEvent event) {
        Player player = event.getPlayer();

        if (event.getRegion().getId().equals("crystal")) {
            Clans guild = ClanManager.getGuild(player);

            if (guild != null) {
                int totalMembersInRegion = (int) guild.getOnlineMembers().stream()
                        .filter(member -> isSameWorldAndWithinRadius(player, member.getLocation(), 5))
                        .count();

                if (totalMembersInRegion >= 2) {
                    handleRegionEntryDenied(player, event.getRegion().getMaximumPoint(), "&cNie możesz wejść na obszar &facrystal&c, ponieważ liczba członków gildii na obszarze jest już powyżej 2.");

                } else {
                    int totalPlayersInRegion = (int) Bukkit.getOnlinePlayers().stream()
                            .filter(p -> isSameWorldAndWithinRadius(player, p.getLocation(), 5))
                            .count();

                    if (totalPlayersInRegion >= 30) {
                        handleRegionEntryDenied(player, event.getRegion().getMaximumPoint(), "&cNie możesz wejść na obszar &facrystal&c, ponieważ liczba graczy na obszarze jest już powyżej 30.");
                    } else {
                        player.spigot().sendMessage(ChatMessageType.CHAT, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Wszedles na kraine crystal")));
                    }
                }
            } else {
                handleRegionEntryDenied(player, event.getRegion().getMaximumPoint(), "&cNie możesz wejść na obszar &fcrystal&c, ponieważ nie należysz do żadnego klanu.");
            }
        }
    }


    private boolean isSameWorldAndWithinRadius(Player player, Location location, double radius) {
        return player.getWorld() == location.getWorld() && player.getLocation().distance(location) <= radius;
    }

    private void handleRegionEntryDenied(Player player, BlockVector3 maximumPoint, String message) {
        Location playerLocation = player.getLocation();
        Location maxPointLocation = new Location(player.getWorld(), maximumPoint.getX(), maximumPoint.getY(), maximumPoint.getZ());
        Vector directionToMaxPoint = maxPointLocation.toVector().subtract(playerLocation.toVector()).normalize();
        Vector backwardVelocity = directionToMaxPoint.multiply(-1.5);
        player.setVelocity(backwardVelocity);
        ChatUtil.sendMessage(player, message);
    }





    @EventHandler
   public void onRegionLeftAfk(RegionLeftEvent event) {
       Player player = event.getPlayer();
       if (event.getRegion().getId().equals("afk")) {
           player.spigot().sendMessage(ChatMessageType.CHAT, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Opuściłeś strefę afk")));
           RewardTask.cancelTask(player);
           for (final Player otherPlayer : Bukkit.getOnlinePlayers()) {
        	   player.showPlayer(Main.getPlugin(),otherPlayer);
               otherPlayer.showPlayer(Main.getPlugin(),player);
           }
       }
   }

   @EventHandler
   public void onRegionLeftspawn(final RegionLeftEvent event) {
       final Player player = event.getPlayer();
       if (event.getRegion().getId().equals(Main.getPlugin().getConfiguration().spawnregion())) {
           player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Opusciles srodek spawnu, zostales odkryty!")));
           for (final Player otherPlayer : Bukkit.getOnlinePlayers()) {
        	   player.showPlayer(Main.getPlugin(),otherPlayer);
               otherPlayer.showPlayer(Main.getPlugin(),player);
           }
       }
   }
   
   @EventHandler
   public void joinspawn(final PlayerJoinEvent e) {
       final Player p = e.getPlayer();
       final RegionContainer rg = WorldGuard.getInstance().getPlatform().getRegionContainer();
       RegionManager region = rg.get(BukkitAdapter.adapt(p.getLocation().getWorld()));
       if (region.hasRegion(Main.getPlugin().getConfiguration().spawnregion())) {
           for (final Player onp : Bukkit.getOnlinePlayers()) {
               p.hidePlayer(Main.getPlugin(),onp);
               onp.hidePlayer(Main.getPlugin(),p);
           }
       }
   }
   
   @EventHandler
   public static void UnhidePlayers(PlayerTeleportEvent event) {
       Player p = event.getPlayer();
       for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
           otherPlayer.showPlayer(Main.getPlugin(), p);
           p.showPlayer(Main.getPlugin(), otherPlayer);
       }
   }



    
   @EventHandler
   public void onPlayerKick(PlayerKickEvent e) {
       handlePlayerDisconnect(e.getPlayer(), MovementWay.DISCONNECT, e);
   }

   @EventHandler
   public void onPlayerQuit(PlayerQuitEvent e) {
       handlePlayerDisconnect(e.getPlayer(), MovementWay.DISCONNECT, e);
   }

   private void handlePlayerDisconnect(Player player, MovementWay movement, PlayerEvent event) {
       Set<ProtectedRegion> regions = playerRegions.remove(player);
       if (regions != null) {
           for (ProtectedRegion region : regions) {
               RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, player, movement, event);
               RegionLeftEvent leftEvent = new RegionLeftEvent(region, player, movement, event);
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
        Set<ProtectedRegion> regions = playerRegions.getOrDefault(player, new HashSet<>());
        final Set<ProtectedRegion> oldRegions = new HashSet<>(regions);
        
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        final RegionManager rm = container.get(BukkitAdapter.adapt(to.getWorld()));
        if (rm == null) {
            return false;
        }

        Set<ProtectedRegion> appRegions = rm.getApplicableRegions(BlockVector3.at(to.getX(), to.getY(), to.getZ())).getRegions();

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
                Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> {
                    final RegionEnteredEvent enteredEvent = new RegionEnteredEvent(region, player, movement, event);
                    Bukkit.getServer().getPluginManager().callEvent(enteredEvent);
                }, 1L);
                regions.add(region);
            }
        }
        
        regions.removeIf(region -> !appRegions.contains(region));
        
        for (final ProtectedRegion region : oldRegions) {
            if (!regions.contains(region)) {
                if (rm.getRegion(region.getId()) == region) {
                    final RegionLeaveEvent e = new RegionLeaveEvent(region, player, movement, event);
                    Bukkit.getServer().getPluginManager().callEvent(e);
                    if (e.isCancelled()) {
                        regions.clear();
                        regions.addAll(oldRegions);
                        return true;
                    }
                    Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> {
                        final RegionLeftEvent leftEvent = new RegionLeftEvent(region, player, movement, event);
                        Bukkit.getServer().getPluginManager().callEvent(leftEvent);
                    }, 1L);
                }
            }
        }
        
        playerRegions.put(player, regions);
        return false;
    }
}