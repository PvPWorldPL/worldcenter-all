package pl.textr.boxpvp.listeners.other;

import java.util.*;

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
    public void dropToInventory(BlockBreakEvent event) {
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

        // Warunki dla bloków, które mają być obsługiwane
        if ((block.getType() == Material.EMERALD_ORE
                || block.getType() == Material.DIAMOND_ORE
                || block.getType() == Material.NETHER_QUARTZ_ORE
                || block.getType() == Material.ANCIENT_DEBRIS
                || block.getType() == Material.GOLD_ORE
                || block.getType() == Material.IRON_ORE
                || block.getType() == Material.LAPIS_ORE
                || block.getType() == Material.COAL_ORE
                || block.getType() == Material.REDSTONE_ORE
                || block.getType() == Material.BLACK_WOOL
                || block.getType() == Material.WHITE_WOOL
                || block.getType() == Material.EMERALD_BLOCK
                || block.getType() == Material.QUARTZ_BLOCK
                || block.getType() == Material.DIAMOND_BLOCK
                || block.getType() == Material.IRON_BLOCK
                || block.getType() == Material.GOLD_BLOCK
                || block.getType() == Material.LAPIS_BLOCK
                || block.getType() == Material.REDSTONE_BLOCK
                || block.getType() == Material.COAL_BLOCK)) {

            for (ItemStack item : event.getBlock().getDrops()) {
                drops.add(new ItemStack(item));
                event.setDropItems(false);
                player.giveExp(RandomUtil.getRandInt(4, 8));
            }

            double totalMultiplier = 1.0;

            for (ItemStack item : drops) {
                if (fortuneLevel > 0) {
                    item = isFortuneApplicable(item, fortuneLevel);
                }
                item = blockToIngot(item);

                HashMap<Integer, ItemStack> leftOver = player.getInventory().addItem(new ItemStack(item));
                leftOver.values().removeIf(leftoverItem -> leftoverItem.getType() != Material.AIR && leftoverItem.getAmount() != 0);
                leftOver.forEach((index, leftoverItem) -> player.getWorld().dropItemNaturally(player.getLocation(), leftoverItem));
            }

            // PerkDropu logic
            if (user.getPerkDropu() >= 1 && user.getPerkDropu() <= 4) {
                double perkDropuMultiplier = switch (user.getPerkDropu()) {
                    case 1 -> Main.getPlugin().getConfiguration().getPerkDropu1();
                    case 2 -> Main.getPlugin().getConfiguration().getPerkDropu2();
                    case 3 -> Main.getPlugin().getConfiguration().getPerkDropu3();
                    case 4 -> Main.getPlugin().getConfiguration().getPerkDropu4();
                    default -> 1.0;
                };

                totalMultiplier *= perkDropuMultiplier;

                for (ItemStack item : drops) {
                    ItemStack multipliedItem = new ItemStack(item.getType(), (int) (item.getAmount() * perkDropuMultiplier));
                    HashMap<Integer, ItemStack> leftOver1 = player.getInventory().addItem(multipliedItem);
                    leftOver1.values().removeIf(leftoverItem1 -> leftoverItem1.getType() != Material.AIR && leftoverItem1.getAmount() != 0);
                    leftOver1.forEach((index, leftoverItem1) -> player.getWorld().dropItemNaturally(player.getLocation(), leftoverItem1));

                }
            }

            // Turbodrop logic
            if (Main.getPlugin().getConfiguration().turbodrop > System.currentTimeMillis()) {
                double turbodropMultiplier = Main.getPlugin().getConfiguration().turbodropmnoznik();
                totalMultiplier *= turbodropMultiplier;

                for (ItemStack item : drops) {
                    ItemStack multipliedItem = new ItemStack(item.getType(), (int) (item.getAmount() * turbodropMultiplier));
                    HashMap<Integer, ItemStack> leftOver1 = player.getInventory().addItem(multipliedItem);
                    leftOver1.values().removeIf(leftoverItem1 -> leftoverItem1.getType() != Material.AIR && leftoverItem1.getAmount() != 0);
                    leftOver1.forEach((index, leftoverItem1) -> player.getWorld().dropItemNaturally(player.getLocation(), leftoverItem1));
                }
            }
            ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("Twój ogólny mnożnik: " + totalMultiplier));
            checkInventory(player);
            // if (itemInMainHand != null)
            ItemsManager.recalculateDurability(player, itemInMainHand);
        }
    }


    ItemStack blockToIngot(ItemStack itemStack) {
        Material resultMaterial;

        if (itemStack.getType() == Material.IRON_ORE) {
            resultMaterial = Material.IRON_INGOT;
        } else if (itemStack.getType() == Material.GOLD_ORE) {
            resultMaterial = Material.GOLD_INGOT;
        } else {
            return itemStack;
        }

        itemStack.setType(resultMaterial);
        return itemStack;
    }

    ItemStack isFortuneApplicable(ItemStack itemStack, int fortuneLevel) {
        if (fortuneLevel > 0) {
            int additionalAmount = switch (fortuneLevel) {
                case 1 -> RandomUtil.getRandInt(1, 2);
                case 2 -> RandomUtil.getRandInt(2, 3);
                case 3 -> RandomUtil.getRandInt(3, 4);
                case 4 -> RandomUtil.getRandInt(5, 6);
                default -> RandomUtil.getRandInt(4, 5);
            };
            itemStack.setAmount(itemStack.getAmount() + additionalAmount);
        }
        return itemStack;
    }

    public static void checkInventory(Player p) {
        PlayerInventory playerInventory = p.getInventory();
        UserProfile user = UserAccountManager.getUser(p);

        if (user.isPrzerabianieBlokow()) {
            HashMap<Material, Material> materialMappings = new HashMap<>();
            materialMappings.put(Material.EMERALD, Material.EMERALD_BLOCK);
            materialMappings.put(Material.QUARTZ, Material.QUARTZ_BLOCK);
            materialMappings.put(Material.DIAMOND, Material.DIAMOND_BLOCK);
            materialMappings.put(Material.IRON_INGOT, Material.IRON_BLOCK);
            materialMappings.put(Material.GOLD_INGOT, Material.GOLD_BLOCK);
            materialMappings.put(Material.LAPIS_LAZULI, Material.LAPIS_BLOCK);
            materialMappings.put(Material.REDSTONE, Material.REDSTONE_BLOCK);
            materialMappings.put(Material.COAL, Material.COAL_BLOCK);

            for (Map.Entry<Material, Material> entry : materialMappings.entrySet()) {
                Material fromMaterial = entry.getKey();
                Material toMaterial = entry.getValue();
                if (playerInventory.contains(fromMaterial, 64)) {
                    ItemsManager.change(p, fromMaterial, toMaterial);
                }
            }
        }



        if (user.isPrzerabianieMonet()) {

            //JAKIE MONETY MAJA WYPADAC Z BLOKOW
            //domyslnie wymagane 9 i 3 do przerobienia na monete
            if (playerInventory.contains(Material.EMERALD_BLOCK, 64)) {
                ItemsManager.change2(p, Material.EMERALD_BLOCK, 64, ItemsManager.getMoneta1(1));
            }

            if (playerInventory.contains(Material.DIAMOND_BLOCK, 64)) {
                ItemsManager.change2(p, Material.DIAMOND_BLOCK, 64, ItemsManager.getMoneta1(1));
            }

            if (playerInventory.contains(Material.GOLD_BLOCK, 64)) {
                ItemsManager.change2(p, Material.GOLD_BLOCK, 64, ItemsManager.getMoneta1(1));
            }

            if (playerInventory.contains(Material.LAPIS_BLOCK, 64)) {
                ItemsManager.change2(p, Material.LAPIS_BLOCK, 64, ItemsManager.getMoneta1(1));
            }

            if (playerInventory.contains(Material.REDSTONE_BLOCK, 64)) {
                ItemsManager.change2(p, Material.REDSTONE_BLOCK, 64, ItemsManager.getMoneta1(1));
            }

            if (playerInventory.contains(Material.COAL_BLOCK, 64)) {
                ItemsManager.change2(p, Material.COAL_BLOCK, 64, ItemsManager.getMoneta1(1));
            }

            if (playerInventory.contains(Material.IRON_BLOCK, 64)) {
                ItemsManager.change2(p, Material.IRON_BLOCK, 64, ItemsManager.getMoneta1(1));
            }

            //domyslnie 3
            if (playerInventory.contains(Material.QUARTZ_BLOCK, 64)) {
                ItemsManager.change2(p, Material.QUARTZ_BLOCK, 64, ItemsManager.getMoneta1(3));
            }
            //domyslnie 3
            if (playerInventory.contains(Material.BLACK_WOOL, 64)) {
                ItemsManager.change2(p, Material.BLACK_WOOL, 64, ItemsManager.getMoneta2(4));
            }
            //domyslnie 3
            if (playerInventory.contains(Material.WHITE_WOOL, 64)) {
                ItemsManager.change2(p, Material.WHITE_WOOL, 64, ItemsManager.getMoneta2(4));
            }

            if (playerInventory.contains(Material.NETHERITE_BLOCK, 64)) {
                ItemsManager.change2(p, Material.NETHERITE_BLOCK, 64, ItemsManager.getMoneta1(2));
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


}