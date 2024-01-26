package pl.textr.boxpvp.listeners.other;

import java.util.*;
import java.util.stream.Collectors;

import api.data.Clans;
import api.managers.ClanManager;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionQuery;
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


import api.regions.RegionEnteredEvent;

import api.regions.RegionLeftEvent;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.util.Vector;
import pl.textr.boxpvp.Main;

import pl.textr.boxpvp.tasks.RewardTask;
import pl.textr.boxpvp.utils.ChatUtil;

import javax.annotation.Nonnull;

public class WGRegionEventsListener implements Listener {


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
        } else if (regionId.equals("afk")) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Wszedłeś do strefy AFK")));
            RewardTask.startTask(300, 1800, player);

            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
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
        Vector vector = player.getLocation().toVector().subtract(location.toVector()).normalize().multiply(1.4D);
        vector.setY(0.1D); // Set the y-component separately

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
        } else if (regionId.equals(Main.getPlugin().getConfiguration().spawnregion())) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Opuściłeś środek spawnu - zostałeś odkryty!")));

            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                player.showPlayer(Main.getPlugin(), otherPlayer);
                otherPlayer.showPlayer(Main.getPlugin(), player);
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



    @Nonnull
    public static Set<ProtectedRegion> getRegions(UUID playerUUID)
    {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null || !player.isOnline())
            return Collections.emptySet();

        RegionQuery query = Main.getPlugin().getRegionContainer().createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()));
        return set.getRegions();
    }

    /**
     * Gets the regions names a player is currently in.
     *
     * @param playerUUID UUID of the player in question.
     * @return Set of Strings with the names of the regions the player is currently in.
     */
    @Nonnull
    public static Set<String> getRegionsNames(UUID playerUUID)
    {
        return getRegions(playerUUID).stream().map(ProtectedRegion::getId).collect(Collectors.toSet());
    }

    /**
     * Checks whether a player is in one or several regions
     *
     * @param playerUUID UUID of the player in question.
     * @param regionNames Set of regions to check.
     * @return True if the player is in (all) the named region(s).
     */
    public static boolean isPlayerInAllRegions(UUID playerUUID, Set<String> regionNames)
    {
        Set<String> regions = getRegionsNames(playerUUID);
        if(regionNames.isEmpty()) throw new IllegalArgumentException("You need to check for at least one region !");

        return regions.containsAll(regionNames.stream().map(String::toLowerCase).collect(Collectors.toSet()));
    }

    /**
     * Checks whether a player is in one or several regions
     *
     * @param playerUUID UUID of the player in question.
     * @param regionNames Set of regions to check.
     * @return True if the player is in (any of) the named region(s).
     */
    public static boolean isPlayerInAnyRegion(UUID playerUUID, Set<String> regionNames)
    {
        Set<String> regions = getRegionsNames(playerUUID);
        if(regionNames.isEmpty()) throw new IllegalArgumentException("You need to check for at least one region !");
        for(String region : regionNames)
        {
            if(regions.contains(region.toLowerCase()))
                return true;
        }
        return false;
    }

    /**
     * Checks whether a player is in one or several regions
     *
     * @param playerUUID UUID of the player in question.
     * @param regionName List of regions to check.
     * @return True if the player is in (any of) the named region(s).
     */
    public static boolean isPlayerInAnyRegion(UUID playerUUID, String... regionName)
    {
        return isPlayerInAnyRegion(playerUUID, new HashSet<>(Arrays.asList(regionName)));
    }

    /**
     * Checks whether a player is in one or several regions
     *
     * @param playerUUID UUID of the player in question.
     * @param regionName List of regions to check.
     * @return True if the player is in (any of) the named region(s).
     */
    public static boolean isPlayerInAllRegions(UUID playerUUID, String... regionName)
    {
        return isPlayerInAllRegions(playerUUID, new HashSet<>(Arrays.asList(regionName)));
    }

}