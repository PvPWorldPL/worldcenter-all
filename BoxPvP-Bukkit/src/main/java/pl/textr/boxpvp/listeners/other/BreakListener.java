package pl.textr.boxpvp.listeners.other;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import api.data.UserProfile;
import api.managers.ItemsManager;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.RandomUtil;


public class BreakListener implements Listener {


	@EventHandler(priority = EventPriority.MONITOR)
	  public void drop2inventory(BlockBreakEvent event) {
        Player player = event.getPlayer();
        UserProfile user = UserAccountManager.getUser(player);
        Block block = event.getBlock();
        if (event.isCancelled()) {
            event.setCancelled(true);
            return;
        }
        int fortuneLevel = 0;
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand(); 

        if (itemInMainHand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
            fortuneLevel = itemInMainHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
        }

        ArrayList<ItemStack> drops = new ArrayList<>();

        for (ItemStack item : event.getBlock().getDrops()) {
            drops.add(new ItemStack(item));
            event.setDropItems(false);
            player.giveExp(RandomUtil.getRandInt(4, 8));
        }
        for (ItemStack item : drops) {
            if (fortuneLevel > 0 && (
                    block.getType() == Material.EMERALD_ORE
                            || block.getType() == Material.DIAMOND_ORE
                            || block.getType() == Material.NETHER_QUARTZ_ORE
                            || block.getType() == Material.ANCIENT_DEBRIS
                            || block.getType() == Material.GOLD_ORE
                            || block.getType() == Material.IRON_ORE
                            || block.getType() == Material.LAPIS_ORE
                            || block.getType() == Material.COAL_ORE
                            || block.getType() == Material.REDSTONE_ORE
                            || block.getType() == Material.BLACK_WOOL
                            || block.getType() == Material.WHITE_WOOL))
                item = applyFortune(item, fortuneLevel);
            item = blocktoignot(item);

            HashMap<Integer, ItemStack> leftOver = player.getInventory().addItem(new ItemStack(item));
            for (ItemStack leftoverItem : leftOver.values()) {
                if (leftoverItem.getType() != Material.AIR && leftoverItem.getAmount() != 0) {
                    player.getWorld().dropItemNaturally(player.getLocation(), leftoverItem);
                }
            }

            if (user.getPerkDropu() >= 1 && user.getPerkDropu() <= 4) {
                double multiplier = switch (user.getPerkDropu()) {
                    case 1 -> Main.getPlugin().getConfiguration().getPerkDropu1();
                    case 2 -> Main.getPlugin().getConfiguration().getPerkDropu2();
                    case 3 -> Main.getPlugin().getConfiguration().getPerkDropu3();
                    case 4 -> Main.getPlugin().getConfiguration().getPerkDropu4();
                    default -> 1.0;
                };

                // Informacje dla gracza o użyciu perku dropu
                String perkLevelMessage = "&7Użyto perku &adropu&7, zostałeś uleczony &8(&aPoziom " + user.getPerkDropu() + "&8)";
                ChatUtil.sendMessage(player, perkLevelMessage);

                ItemStack multipliedItem = new ItemStack(item.getType(), (int) (item.getAmount() * multiplier));
                HashMap<Integer, ItemStack> leftOver1 = player.getInventory().addItem(multipliedItem);
                leftOver1.values().removeIf(leftoverItem1 -> leftoverItem1.getType() != Material.AIR && leftoverItem1.getAmount() != 0);
                leftOver1.forEach((index, leftoverItem1) -> player.getWorld().dropItemNaturally(player.getLocation(), leftoverItem1));
                Bukkit.getLogger().info("Gracz " + player.getName() + " używa perkDropu " + user.getPerkDropu() + " z mnożnikiem " + multiplier);
            }


            if (Main.getPlugin().getConfiguration().turbodrop > System.currentTimeMillis()) {
                ItemStack multipliedItem = new ItemStack(item.getType(), item.getAmount() * Main.getPlugin().getConfiguration().turbodropmnoznik());
                HashMap<Integer, ItemStack> leftOver1 = player.getInventory().addItem(multipliedItem);
                for (ItemStack leftoverItem1 : leftOver1.values()) {
                    if (leftoverItem1.getType() != Material.AIR && leftoverItem1.getAmount() != 0) {
                        player.getWorld().dropItemNaturally(player.getLocation(), leftoverItem1);
                    }
                }
            }

            checkInventory(player);
            //  if (itemInMainHand != null)
            ItemsManager.recalculateDurability(player, itemInMainHand);
        }
    }


    
    

	  ItemStack blocktoignot(ItemStack itemStack) {
		    Material resultMaterial;

	      switch (itemStack.getType()) {
	          case IRON_ORE -> resultMaterial = Material.IRON_INGOT;
	          case GOLD_ORE -> resultMaterial = Material.GOLD_INGOT;
	          default -> {
	              return itemStack;
	          }
	      }

		    itemStack.setType(resultMaterial);
		    return itemStack;
		}

  
  
  public static void checkInventory(Player p) {
    PlayerInventory playerInventory = p.getInventory();
    UserProfile user = UserAccountManager.getUser(p);
    if (user.isPrzerabianieBlokow()) {

      if (playerInventory.contains(Material.EMERALD, 64)) {
    	  ItemsManager.change(p, Material.EMERALD, Material.EMERALD_BLOCK);
      }
      if (playerInventory.contains(Material.QUARTZ, 64)) {
    	  ItemsManager.change(p, Material.QUARTZ, Material.QUARTZ_BLOCK);
      }
      if (playerInventory.contains(Material.DIAMOND, 64)) {
    	  ItemsManager.change(p, Material.DIAMOND, Material.DIAMOND_BLOCK);
      }
      if (playerInventory.contains(Material.IRON_INGOT, 64)) {
    	  ItemsManager.change(p, Material.IRON_INGOT, Material.IRON_BLOCK);
      }
      if (playerInventory.contains(Material.GOLD_INGOT, 64)) {
    	  ItemsManager.change(p, Material.GOLD_INGOT, Material.GOLD_BLOCK);
      }
      if (playerInventory.contains(Material.LAPIS_LAZULI, 64)) {
    	  ItemsManager. change(p, Material.LAPIS_LAZULI, Material.LAPIS_BLOCK);
      }
      if (playerInventory.contains(Material.REDSTONE, 64)) {
    	  ItemsManager.change(p, Material.REDSTONE, Material.REDSTONE_BLOCK);
      }
      if (playerInventory.contains(Material.COAL, 64)) {
    	  ItemsManager. change(p, Material.COAL, Material.COAL_BLOCK);
      } 
    }
    
    
    
    if (user.isPrzerabianieMonet()) {
    	
    	//JAKIE MONETY MAJA WYPADAC Z BLOKOW
    	//domyslnie wymagane 9 i 3 do przerobienia na monete
      if (playerInventory.contains(Material.EMERALD_BLOCK, 64)) {
    	  ItemsManager. change2(p, Material.EMERALD_BLOCK, 64,  ItemsManager.getMoneta1(1));
      }
      
      if (playerInventory.contains(Material.DIAMOND_BLOCK, 64)) {
    	  ItemsManager. change2(p, Material.DIAMOND_BLOCK, 64, ItemsManager.getMoneta1(1));
        }
      
      if (playerInventory.contains(Material.GOLD_BLOCK, 64)) {
    	  ItemsManager.  change2(p, Material.GOLD_BLOCK, 64, ItemsManager.getMoneta1(1));
        }  
      
      if (playerInventory.contains(Material.LAPIS_BLOCK, 64)) {
    	  ItemsManager.change2(p, Material.LAPIS_BLOCK, 64, ItemsManager.getMoneta1(1));
        }  
      
      if (playerInventory.contains(Material.REDSTONE_BLOCK, 64)) {
    	  ItemsManager.change2(p, Material.REDSTONE_BLOCK, 64, ItemsManager.getMoneta1(1));
        } 
      
      if (playerInventory.contains(Material.COAL_BLOCK, 64)) {
    	  ItemsManager. change2(p, Material.COAL_BLOCK, 64, ItemsManager.getMoneta1(1));
        }  
      
      if (playerInventory.contains(Material.IRON_BLOCK, 64)) {
    	  ItemsManager. change2(p, Material.IRON_BLOCK, 64, ItemsManager.getMoneta1(1));
        }
      
      //domyslnie 3
      if (playerInventory.contains(Material.QUARTZ_BLOCK, 64)) {
    	  ItemsManager.change2(p, Material.QUARTZ_BLOCK, 64, ItemsManager.getMoneta1(3));
      }
      //domyslnie 3
      if (playerInventory.contains(Material.BLACK_WOOL, 64)) {
    	  ItemsManager. change2(p, Material.BLACK_WOOL, 64, ItemsManager.getMoneta2(4));
      }
      //domyslnie 3
      if (playerInventory.contains(Material.WHITE_WOOL, 64)) {
    	  ItemsManager. change2(p, Material.WHITE_WOOL, 64, ItemsManager.getMoneta2(4));
      }
      
      if (playerInventory.contains(Material.NETHERITE_BLOCK, 64)) {
    	  ItemsManager. change2(p, Material.NETHERITE_BLOCK, 64, ItemsManager.getMoneta1(2));
      }
      if (playerInventory.contains(Material.ANCIENT_DEBRIS, 64)) {
    	  ItemsManager.change2(p, Material.ANCIENT_DEBRIS, 64, ItemsManager.getMoneta1(2));
      }
      
      //MONETY NA DALSZE MONETY
      if (playerInventory.contains(ItemsManager.getMoneta1(64))) {
    	  ItemsManager.change3(p, ItemsManager.getMoneta1(64), ItemsManager.getMoneta2(1));
      }
      if (playerInventory.contains(ItemsManager.getMoneta2(64))) {
    	  ItemsManager.change3(p, ItemsManager.getMoneta2(64), ItemsManager.getMoneta3(1));
      }
      if (playerInventory.contains(ItemsManager.getMoneta3(64))) {
    	  ItemsManager.change3(p, ItemsManager.getMoneta3(64), ItemsManager.getMoneta4(1));
      }
      if (playerInventory.contains(ItemsManager.getMoneta4(64))) {
    	  ItemsManager.change3(p, ItemsManager.getMoneta4(64), ItemsManager.getMoneta5(1));
      }
      if (playerInventory.contains(ItemsManager.getMoneta5(64))) {
    	  ItemsManager.change3(p, ItemsManager.getMoneta5(64), ItemsManager.getMoneta6(1));
        }
      if (playerInventory.contains(ItemsManager.getMoneta6(64))) {
    	  ItemsManager.change3(p, ItemsManager.getMoneta6(64), ItemsManager.getMoneta7(1));
        }
      if (playerInventory.contains(ItemsManager.getMoneta7(64))) {
    	  ItemsManager.change3(p, ItemsManager.getMoneta7(64), ItemsManager.getMoneta8(1));
        }
    } 
    //PRZERABIANIE MONET NA KASE 
    if (user.isPrzerabianieKasy()) {
      if (playerInventory.containsAtLeast(new ItemStack(ItemsManager.getMoneta1(1)), 1)) {
    	  ItemsManager.kasakasa(p, ItemsManager.getMoneta1(1), 1.0D);
      }
      
      if (playerInventory.containsAtLeast(new ItemStack(ItemsManager.getMoneta2(1)), 1)) {
    	  ItemsManager.kasakasa(p, ItemsManager.getMoneta2(1), 64.0D);
      }
      
      if (playerInventory.containsAtLeast(new ItemStack(ItemsManager.getMoneta3(1)), 1)) {
    	  ItemsManager.kasakasa(p, ItemsManager.getMoneta3(1), 4096.0D);
      }
      
      if (playerInventory.containsAtLeast(new ItemStack(ItemsManager.getMoneta4(1)), 1)) {
    	  ItemsManager.kasakasa(p, ItemsManager.getMoneta4(1), 262144.0D);
      }
      
      if (playerInventory.containsAtLeast(new ItemStack(ItemsManager.getMoneta5(1)), 1)) {
    	  ItemsManager.kasakasa(p, ItemsManager.getMoneta5(1), 1.6777216E7D); //16m
      }
      if (playerInventory.containsAtLeast(new ItemStack(ItemsManager.getMoneta6(1)), 1)) {
    	  ItemsManager.kasakasa(p, ItemsManager.getMoneta6(1), 2.6777216E7D); //26m
      }
      if (playerInventory.containsAtLeast(new ItemStack(ItemsManager.getMoneta7(1)), 1)) {
    	  ItemsManager.kasakasa(p, ItemsManager.getMoneta7(1), 3.6777216E7D); //36m
      }
      if (playerInventory.containsAtLeast(new ItemStack(ItemsManager.getMoneta8(1)), 1)) {
    	  ItemsManager.kasakasa(p, ItemsManager.getMoneta8(1), 4.6777216E7D); //46m
      }
      
    }
    
  }





  ItemStack applyFortune(ItemStack itemStack, int fortuneLevel) {
	    if (fortuneLevel == 1) {
	        itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(1, 2));
	    }
	    if (fortuneLevel == 2) {
	        itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(2, 3));
	    }
	    if (fortuneLevel == 3) {
	        itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(3, 4));
	    }
	    if (fortuneLevel > 3) {
	        itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(4, 5));
	    }
	    if (fortuneLevel == 4) {
	        itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(5, 6));
	    }
	    if (fortuneLevel > 4) {
	        itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(6, 7));
	    }
		/*
		 * // if (fortuneLevel == 5) { itemStack.setAmount(itemStack.getAmount() +
		 * RandomUtil.getRandInt(7, 8)); } if (fortuneLevel > 5) {
		 * itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(8, 9)); }
		 * if (fortuneLevel == 10) { itemStack.setAmount(itemStack.getAmount() +
		 * RandomUtil.getRandInt(9, 10)); } if (fortuneLevel > 10) {
		 * itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(10, 11)); }
		 * if (fortuneLevel == 15) { itemStack.setAmount(itemStack.getAmount() +
		 * RandomUtil.getRandInt(11, 12)); } if (fortuneLevel > 15) {
		 * itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(12, 13)); }
		 * if (fortuneLevel == 20) { itemStack.setAmount(itemStack.getAmount() +
		 * RandomUtil.getRandInt(13, 14)); } if (fortuneLevel > 20) {
		 * itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(14, 15)); }
		 * if (fortuneLevel == 25) { itemStack.setAmount(itemStack.getAmount() +
		 * RandomUtil.getRandInt(15, 16)); } if (fortuneLevel > 25) {
		 * itemStack.setAmount(itemStack.getAmount() + RandomUtil.getRandInt(16, 17)); }
		 */
	    return itemStack;
	}
}