package pl.textr.boxpvp.commands.User;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "repair", description = "Naprawia przedmioty w ekwipunku", usage = "/naprawa", permission = "core.cmd.user", aliases = {"repair"})
public class RepairCommand extends PlayerCommandExecutor {


    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(Player player, String[] args) {
        if (!player.hasPermission("core.premium")) {
            ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&7Ta czynność jest dostępna tylko dla rangi &6premium"));
            return true;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("all")) {
            boolean hasTool = false;
            for (ItemStack itemStack : player.getInventory().getContents()) {
                if (itemStack != null && isTool(itemStack.getType())) {
                    hasTool = true;
                    itemStack.setDurability((short) 0);
                }
            }

            if (!hasTool) {
                ChatUtil.sendMessage(player, "&8[&C&l!&8] &cTwój ekwipunek nie zawiera narzędzi");
                return true;
            }

            player.updateInventory();
            ChatUtil.sendTitle(player, "", "&aWszystkie przedmioty zostały naprawione");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 10.0F, 5.0F);
            return true;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        
        // Sprawdź, czy gracz trzyma przedmiot
        if (itemInHand == null || itemInHand.getType() == Material.AIR) {
            ChatUtil.sendMessage(player, "&8[&C&l!&8] &cNie trzymasz żadnego przedmiotu");
            return true;
        }

        // Sprawdź, czy przedmiot jest narzędziem
        if (!isTool(itemInHand.getType())) {
            ChatUtil.sendMessage(player, "&8[&C&l!&8] &cTego przedmiotu nie możesz naprawić!");
            return true;
        }

        itemInHand.setDurability((short) 0);
        player.getInventory().setItemInMainHand(itemInHand);
        ChatUtil.sendTitle(player, "", "&aPrzedmiot został naprawiony");
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 10.0F, 5.0F);
        return true;
    }



    // Metoda sprawdzająca, czy typ przedmiotu jest narzędziem
    public static boolean isTool(Material type) {
        return type == Material.WOODEN_PICKAXE || type == Material.WOODEN_AXE || type == Material.WOODEN_SHOVEL
                || type == Material.WOODEN_HOE || type == Material.STONE_PICKAXE || type == Material.STONE_AXE
                || type == Material.STONE_SHOVEL || type == Material.STONE_HOE || type == Material.IRON_PICKAXE
                || type == Material.IRON_AXE || type == Material.IRON_SHOVEL || type == Material.IRON_HOE
                || type == Material.DIAMOND_PICKAXE || type == Material.DIAMOND_AXE || type == Material.DIAMOND_SHOVEL
                || type == Material.DIAMOND_HOE || type == Material.NETHERITE_PICKAXE || type == Material.NETHERITE_AXE
                || type == Material.NETHERITE_SHOVEL || type == Material.NETHERITE_HOE || type == Material.LEATHER_HELMET
                || type == Material.LEATHER_CHESTPLATE || type == Material.LEATHER_LEGGINGS || type == Material.LEATHER_BOOTS
                || type == Material.CHAINMAIL_HELMET || type == Material.CHAINMAIL_CHESTPLATE || type == Material.CHAINMAIL_LEGGINGS
                || type == Material.CHAINMAIL_BOOTS || type == Material.IRON_HELMET || type == Material.IRON_CHESTPLATE
                || type == Material.IRON_LEGGINGS || type == Material.IRON_BOOTS || type == Material.DIAMOND_HELMET
                || type == Material.DIAMOND_CHESTPLATE || type == Material.DIAMOND_LEGGINGS || type == Material.DIAMOND_BOOTS
                || type == Material.NETHERITE_HELMET || type == Material.NETHERITE_CHESTPLATE || type == Material.NETHERITE_LEGGINGS
                || type == Material.NETHERITE_BOOTS || type == Material.IRON_SWORD || type == Material.NETHERITE_SWORD 
                || type == Material.DIAMOND_SWORD ;
    }

}
