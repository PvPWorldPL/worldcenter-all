package pl.textr.boxpvp.listeners.items;

import api.managers.CooldownManager;
import api.managers.ItemsManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuwaczListener implements Listener {

    @EventHandler
    public void handleUsuwacz(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_BLOCK && action != Action.LEFT_CLICK_BLOCK) {
            return;
        }

        ItemStack item = event.getItem();
        if (item == null || !item.isSimilar(ItemsManager.getUsuwacz(1))) {
            return;
        }


        if (CooldownManager.isCooldownActive(player)) {
            long remainingCooldown = CooldownManager.getRemainingCooldown(player);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&cUsuwacz pajęczyn możesz użyć za &f" + remainingCooldown + " &7sek")));
            return;
        }

        Location location = player.getLocation();
        List<Block> nearbyBlocks = getNearbyBlocks(location);
        boolean deletedBlocks = false;

        for (Block block : nearbyBlocks) {
            if (block.getType() == Material.COBWEB) {
                block.setType(Material.AIR);
                deletedBlocks = true;
            }
        }

        if (deletedBlocks) {
            player.getInventory().removeItem(new ItemStack(ItemsManager.getUsuwacz(1)));
            CooldownManager.setCooldownTime(30);
        }
    }

    private List<Block> getNearbyBlocks(Location location) {
        if (location == null || location.getWorld() == null) {
            return Collections.emptyList();
        }
        World world = location.getWorld();
        List<Block> blocks = new ArrayList<>();
        int minX = location.getBlockX() - 4;
        int minY = location.getBlockY() - 4;
        int minZ = location.getBlockZ() - 4;
        int maxX = location.getBlockX() + 4;
        int maxY = location.getBlockY() + 4;
        int maxZ = location.getBlockZ() + 4;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    blocks.add(world.getBlockAt(x, y, z));
                }
            }
        }

        return blocks;
    }


}
