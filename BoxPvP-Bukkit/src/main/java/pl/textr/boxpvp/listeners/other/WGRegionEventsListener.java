package pl.textr.boxpvp.listeners.other;

import java.util.*;
import java.util.stream.Collectors;

import api.data.Clans;
import api.managers.ClanManager;
import api.regions.WorldGuardRegionHelper;
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


public class WGRegionEventsListener implements Listener {


    @EventHandler
    public void onRegionEntered(RegionEnteredEvent event) {
        Player player = event.getPlayer();
        String regionId = event.getRegionName();

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
        String regionId = event.getRegionName();

        if (regionId.equals("crystal")) {
            Clans clan = ClanManager.getGuild(player);

            if (clan == null) {
                handleRegionEntryDenied(player);
                player.spigot().sendMessage(ChatMessageType.CHAT,
                new TextComponent(ChatUtil.translateHexColorCodes("&cNie możesz wejść na obszar &fcrystal&c, ponieważ nie należysz do żadnego klanu")));
                return;
            }

            int totalClanMembersInRegion = WorldGuardRegionHelper.getClanMembersInRegionCount(clan, "crystal");


            if (totalClanMembersInRegion >= 2) {
                handleRegionEntryDenied(player);
                player.spigot().sendMessage(ChatMessageType.CHAT,
                        new TextComponent(ChatUtil.translateHexColorCodes("&cNie możesz wejść na obszar &facrystal&c, ponieważ liczba członków gildii na obszarze jest już powyżej 2.")));
                return;
            }

            int totalPlayersInRegion = WorldGuardRegionHelper.getPlayersInRegionCount("crystal");

            if (totalPlayersInRegion >= 30) {
                handleRegionEntryDenied(player);
                player.spigot().sendMessage(ChatMessageType.CHAT,
                new TextComponent(ChatUtil.translateHexColorCodes("&cNie możesz wejść na obszar &facrystal&c, ponieważ liczba graczy na obszarze jest już powyżej 30.")));
                return;
            }
            player.spigot().sendMessage(ChatMessageType.CHAT,
            new TextComponent(ChatUtil.translateHexColorCodes("&8[&c&l!&8] &7Wszedles na kraine crystal")));
        }
    }


    private void handleRegionEntryDenied(Player player) {
        Vector knockbackDirection = new Vector(0, 0, -1);
        player.setVelocity(knockbackDirection);
    }



    @EventHandler
    public void onRegionLeft(RegionLeftEvent event) {
        Player player = event.getPlayer();
        String regionId = event.getRegionName();

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

}