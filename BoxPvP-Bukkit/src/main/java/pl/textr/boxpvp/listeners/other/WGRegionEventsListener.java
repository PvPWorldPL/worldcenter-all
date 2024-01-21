package pl.textr.boxpvp.listeners.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import api.data.Clans;
import api.managers.ClanManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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

public class WGRegionEventsListener implements Listener {
    private static final Map<Player, Set<ProtectedRegion>> playerRegions = new HashMap<>();

    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionId = event.getRegion().getId();

        if (regionId.equals(Main.getPlugin().getConfiguration().spawnregion())) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Jesteś na środku spawnu - zostałeś ukryty")));

            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                player.hidePlayer(Main.getPlugin(), otherPlayer);
                otherPlayer.hidePlayer(Main.getPlugin(), player);
            }

            if (regionId.equals("afk")) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Wszedłeś do strefy AFK")));
                RewardTask.startTask(300, 1800, player);

                for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                    player.hidePlayer(Main.getPlugin(), otherPlayer);
                    otherPlayer.hidePlayer(Main.getPlugin(), player);
                }
            }
        }
    }


    @EventHandler
    public void onRegionEnteredCrystal(RegionEnteredEvent event) {
        Player player = event.getPlayer();

        if (event.getRegion().getId().equals("crystal")) {
            Clans guild = ClanManager.getGuild(player);

            if (guild == null) {
                handleRegionEntryDenied(player, event.getPlayer().getLocation(), "&cNie możesz wejść na obszar &fcrystal&c, ponieważ nie należysz do żadnego klanu.");
                return;
            }

            int totalMembersInRegion = (int) guild.getOnlineMembers().stream()
                    .filter(member -> isSameWorldAndWithinRadius(player, member.getLocation(), 5))
                    .count();

            int totalPlayersInRegion = (int) Bukkit.getOnlinePlayers().stream()
                    .filter(p -> isSameWorldAndWithinRadius(player, p.getLocation(), 5))
                    .count();

            if (totalMembersInRegion >= 2) {
                handleRegionEntryDenied(player, event.getPlayer().getLocation(), "&cNie możesz wejść na obszar &facrystal&c, ponieważ liczba członków gildii na obszarze jest już powyżej 2.");
                return;
            }

            if (totalPlayersInRegion >= 30) {
                handleRegionEntryDenied(player, event.getPlayer().getLocation(), "&cNie możesz wejść na obszar &facrystal&c, ponieważ liczba graczy na obszarze jest już powyżej 30.");
                return;
            }
            player.spigot().sendMessage(ChatMessageType.CHAT, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Wszedles na kraine crystal")));
        }
    }


    private boolean isSameWorldAndWithinRadius(Player player, Location location, double radius) {
        return player.getWorld() == location.getWorld() && player.getLocation().distance(location) <= radius;
    }

    public static void handleRegionEntryDenied(Player player, Location location, String Message) {
        Vector vector = player.getLocation().toVector().subtract(location.toVector()).normalize().multiply(1.4D).setY(0.1D);
        if (player.isInsideVehicle())
            player.leaveVehicle();
        player.setVelocity(vector);
        player.spigot().sendMessage(ChatMessageType.CHAT, new TextComponent(ChatUtil.translateHexColorCodes(Message)));
    }

    @EventHandler
    public void onRegionLeft(RegionLeftEvent event) {
        Player player = event.getPlayer();
        String regionId = event.getRegion().getId();

        if (regionId.equals("afk")) {
            player.spigot().sendMessage(ChatMessageType.CHAT, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Opuściłeś strefę AFK")));
            RewardTask.cancelTask(player);

            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                player.showPlayer(Main.getPlugin(), otherPlayer);
                otherPlayer.showPlayer(Main.getPlugin(), player);
            }

            if (regionId.equals(Main.getPlugin().getConfiguration().spawnregion())) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Opuściłeś środek spawnu - zostałeś odkryty!")));

                for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                    player.showPlayer(Main.getPlugin(), otherPlayer);
                    otherPlayer.showPlayer(Main.getPlugin(), player);
                }
            }
        }
    }


    @EventHandler
    public void joinspawn(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();

        if (p.getLocation().getWorld() != null) {
            final RegionContainer rg = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager region = rg.get(BukkitAdapter.adapt(p.getLocation().getWorld()));

            if (region != null && region.hasRegion(Main.getPlugin().getConfiguration().spawnregion())) {
                for (final Player onp : Bukkit.getOnlinePlayers()) {
                    p.hidePlayer(Main.getPlugin(), onp);
                    onp.hidePlayer(Main.getPlugin(), p);
                }
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
        handlePlayerDisconnect(e.getPlayer(), e);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        handlePlayerDisconnect(e.getPlayer(), e);
    }

    private void handlePlayerDisconnect(Player player, PlayerEvent event) {
        Set<ProtectedRegion> regions = playerRegions.remove(player);
        if (regions != null) {
            for (ProtectedRegion region : regions) {
                RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, player, MovementWay.DISCONNECT, event);
                RegionLeftEvent leftEvent = new RegionLeftEvent(region, player, MovementWay.DISCONNECT, event);
                Bukkit.getPluginManager().callEvent(leaveEvent);
                Bukkit.getPluginManager().callEvent(leftEvent);
            }
        }
    }



    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent e) {
        e.setCancelled(this.updateRegions(e.getPlayer(), MovementWay.MOVE, e.getPlayer().getLocation(), e));
    }

    @EventHandler
    public void onPlayerTeleport(final PlayerTeleportEvent e) {
        e.setCancelled(this.updateRegions(e.getPlayer(), MovementWay.TELEPORT, e.getPlayer().getLocation(), e));
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
        Set<ProtectedRegion> oldRegions = new HashSet<>(regions);

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        World world = to.getWorld();
        if (world == null) {
            return false;
        }

        RegionManager rm = container.get(BukkitAdapter.adapt(world));
        if (rm == null) {
            return false;
        }

        Set<ProtectedRegion> appRegions = rm.getApplicableRegions(BlockVector3.at(to.getX(), to.getY(), to.getZ())).getRegions();
        ProtectedRegion globalRegion = rm.getRegion("__global__");
        if (globalRegion != null) {
            appRegions.add(globalRegion);
        }

        regions.retainAll(appRegions);
        regions.addAll(appRegions);

        for (ProtectedRegion region : appRegions) {
            if (!oldRegions.contains(region)) {
                RegionEnterEvent enterEvent = new RegionEnterEvent(region, player, movement, event);
                Bukkit.getServer().getPluginManager().callEvent(enterEvent);

                if (enterEvent.isCancelled()) {
                    return true;
                }

                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(), () -> {
                    RegionEnteredEvent enteredEvent = new RegionEnteredEvent(region, player, movement, event);
                    Bukkit.getServer().getPluginManager().callEvent(enteredEvent);
                }, 1L);
            }
        }

        for (ProtectedRegion region : oldRegions) {
            if (!regions.contains(region) && rm.getRegion(region.getId()) == region) {
                RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, player, movement, event);
                Bukkit.getServer().getPluginManager().callEvent(leaveEvent);

                if (leaveEvent.isCancelled()) {
                    return true;
                }

                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(), () -> {
                    RegionLeftEvent leftEvent = new RegionLeftEvent(region, player, movement, event);
                    Bukkit.getServer().getPluginManager().callEvent(leftEvent);
                }, 1L);
            }
        }

        playerRegions.put(player, regions);
        return false;
    }
}