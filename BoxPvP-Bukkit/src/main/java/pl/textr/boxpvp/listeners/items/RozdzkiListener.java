package pl.textr.boxpvp.listeners.items;


import api.managers.CooldownManager;
import api.managers.ItemsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import api.regions.WorldGuardRegionHelper;

import java.util.Set;

public class RozdzkiListener implements Listener {

    @EventHandler
    public void handlewiatr(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity targetEntity = event.getRightClicked();

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.isSimilar(ItemsManager.getrozdzkawiatr(1))) {
            if (WorldGuardRegionHelper.isPlayerInAnyRegion(player.getUniqueId(), "protectspawn", "spawn", "kopalniapremium", "kopalnia", "afk")) {
                    event.setCancelled(true);
                    if (Main.getPlugin().getConfiguration().debug) {
                    Bukkit.getLogger().info("interakcja wylaczona");
                }
                } else {
                    // Gracz nie jest w żadnym z zablokowanych regionów, więc rozdzka działa
                    if (targetEntity instanceof Player targetPlayer) {
                        pushPlayer(targetPlayer, 3, 3);
                        player.getInventory().removeItem(new ItemStack(ItemsManager.getrozdzkawiatr(1)));
                    }

            }
        }
    }




    private void pushPlayer(Player player, int strength, int height) {
        Location playerLocation = player.getLocation();
        Vector direction = playerLocation.getDirection().normalize().multiply(-1);
        Vector velocity = direction.multiply(strength);
        velocity.setY(height);
        player.setVelocity(velocity);
    }
}