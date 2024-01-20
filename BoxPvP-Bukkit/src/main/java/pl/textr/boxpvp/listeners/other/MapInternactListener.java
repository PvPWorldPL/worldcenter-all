package pl.textr.boxpvp.listeners.other;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import api.managers.ItemsManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import pl.textr.boxpvp.utils.ChatUtil;
import api.managers.CooldownManager;

public class MapInternactListener implements Listener {


      private final List<Material> allowedBlocks = Arrays.asList(Material.COBWEB, Material.WARPED_STEM);

      private final List<Material> allowedBreakKopalnia = Arrays.asList( Material.WARPED_STEM, Material.BLACK_WOOL, Material.WHITE_WOOL, Material.HONEYCOMB_BLOCK, Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.DIAMOND_BLOCK, Material.REDSTONE_BLOCK, Material.EMERALD_BLOCK, Material.LAPIS_BLOCK, Material.NETHERITE_BLOCK, Material.COAL_BLOCK, Material.QUARTZ_BLOCK, Material.IRON_ORE, Material.GOLD_ORE,Material.DIAMOND_ORE, Material.REDSTONE_ORE, Material.EMERALD_ORE, Material.LAPIS_ORE, Material.ANCIENT_DEBRIS, Material.COAL_ORE, Material.NETHER_QUARTZ_ORE);
      
        private final List<Material> alloweBreakPvP = Arrays.asList(Material.COBWEB, Material.BLACK_WOOL,Material.WHITE_WOOL, Material.WARPED_STEM, Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.DIAMOND_BLOCK, Material.REDSTONE_BLOCK, Material.EMERALD_BLOCK, Material.LAPIS_BLOCK, Material.NETHERITE_BLOCK, Material.COAL_BLOCK, Material.QUARTZ_BLOCK, Material.IRON_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.REDSTONE_ORE, Material.EMERALD_ORE, Material.LAPIS_ORE, Material.ANCIENT_DEBRIS, Material.COAL_ORE, Material.NETHER_QUARTZ_ORE);
          
      
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = block.getType();
      
        if (player.getWorld().getName().equals("world") || player.getWorld().getName().equals("kopalnia") || player.getWorld().getName().equals("kopalniapremium")) {
            if (!player.hasPermission("core.arena.budowanie")) {
                event.setCancelled(true);
                ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&#FF0000Ta interakcja została wyłączona"));
            }
        } else if (player.getWorld().getName().equals("pvp")) {
            if (!allowedBlocks.contains(blockType)) {
                if (!player.hasPermission("core.arena.budowanie")) {                
                    event.setCancelled(true);
                    ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&#FF0000Ta interakcja została wyłączona"));
                }
            }
        }
    }



    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void handlePlayerDropItem(PlayerDropItemEvent event) {
        if (event.getPlayer().getWorld().getName().equals("world")) {
            event.setCancelled(true);
            ChatUtil.sendMessage(event.getPlayer(), "&cNie możesz w tym miejscu wyrzucić itemów! Szukasz kosza? Wpisz &6/kosz");
        }

    }

    @EventHandler
    public void onClickMeteor(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (event.getPlayer() != null && event.getClickedBlock() != null && clickedBlock.getType() == Material.DRAGON_EGG) {
            Player player = event.getPlayer();
            event.setCancelled(true);
            clickedBlock.setType(Material.AIR);
            player.spawnParticle(Particle.FLAME, clickedBlock.getLocation().add(0.5, 0.5, 0.5), 10, 0, 0, 0, 0.1);
            player.playEffect(EntityEffect.FIREWORK_EXPLODE);
            Bukkit.broadcastMessage(ChatUtil.fixColor("&fMeteor &7zostal zdobyty przez gracza: &f" + player.getName()));
            player.getInventory().addItem(ItemsManager.getodlamek(1));
            String[] keyTypes = {"zwykla", "epicka", "rzadka"};
            String keyType = keyTypes[new Random().nextInt(keyTypes.length)];
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "klucz give " + player.getName() + " " + keyType + " 1");
        }
    }


    
    
    @EventHandler
    public void onMoveElytra(PlayerMoveEvent e) {
      Player player = e.getPlayer();
      if (ChatUtil.isInElytra(player.getLocation()) && 
        player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() == Material.ELYTRA) {
        player.getInventory().addItem(new ItemStack(new ItemStack(player.getInventory().getChestplate().clone())));
        player.getInventory().setChestplate(null);
      
        ChatUtil.sendMessage(player, "&8[&C&l!&8] &cNie mozesz uzywac elytry w tej strefie!");
      } 
    }





    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = block.getType();

        if (player.getWorld().getName().equals("kopalnia")) {
            if (allowedBreakKopalnia.contains(blockType)) {
            	return;
            }
            if (!player.hasPermission("core.arena.budowanie")) {
                event.setCancelled(true);
                ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&#FF0000Ta interakcja została wyłączona"));
            return;
            }

        }
        if (player.getWorld().getName().equals("kopalniapremium")) {
            if (allowedBreakKopalnia.contains(blockType)) {
            	return;
            }
            if (!player.hasPermission("core.arena.budowanie")) {
                event.setCancelled(true);
                ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&#FF0000Ta interakcja została wyłączona"));
                return;
            }
        }

        if (player.getWorld().getName().equals("world")) {
            if (!player.hasPermission("core.arena.budowanie")) {
                event.setCancelled(true);
                ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&#FF0000Ta interakcja została wyłączona"));
            return;
            }
        }

        if (player.getWorld().getName().equals("pvp")) {
        	  if (alloweBreakPvP.contains(blockType)) {
                return;
            }
            if (!player.hasPermission("core.arena.budowanie")) {
                event.setCancelled(true);
                ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&#FF0000Ta interakcja została wyłączona"));
            }
        }
    }

}
