package pl.textr.boxpvp.commands.Admin;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import net.md_5.bungee.api.ChatColor;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "HideEnchantments", description = "ukrywanie enchantow", usage = "/HideEnchantments <hide>|<rename>", permission = "core.cmd.admin")
public class HideEnchantments extends PlayerCommandExecutor {

    public boolean onCommand(Player p, String[] args) {
        ItemStack itemInHand = p.getInventory().getItemInMainHand();
        if (itemInHand.getType() == Material.AIR) {
            return ChatUtil.sendMessage(p, "&cNie masz żadnego przedmiotu w ręce!");
        }

        ItemMeta itemMeta = itemInHand.getItemMeta();
        if (itemMeta != null) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

            Map<Enchantment, String> enchantmentTextMap = createEnchantmentTextMap();

            List<String> lore = new ArrayList<>();

            for (Map.Entry<Enchantment, Integer> entry : itemMeta.getEnchants().entrySet()) {
                Enchantment enchantment = entry.getKey();
                int level = entry.getValue();

                if (enchantmentTextMap.containsKey(enchantment)) {
                    String enchantText = enchantmentTextMap.get(enchantment);
                    lore.add(enchantText + " " + level);
                }
            }

            if (itemInHand.getType() == Material.PLAYER_HEAD) {
                EquipmentSlot slot = EquipmentSlot.HAND;
                addAttributeModifiers(itemMeta, slot);
            }

            itemMeta.setLore(lore);
            itemInHand.setItemMeta(itemMeta);
            return ChatUtil.sendMessage(p, "&aUsunięto enchanty!");
        }

        return false;
    }

    private Map<Enchantment, String> createEnchantmentTextMap() {
        Map<Enchantment, String> map = new HashMap<>();

        map.put(Enchantment.PROTECTION_ENVIRONMENTAL, ChatUtil.translateHexColorCodes("&#20dad8&o⛨ &#1dccd4&oO&#19bdcf&oc&#16afcb&oh&#12a1c6&or&#0f93c2&oo&#0b84bd&on&#0876b9&oa"));
        map.put(Enchantment.DURABILITY, ChatUtil.translateHexColorCodes("&#ff89c7&o&#fb85c2&o🛡 &#f681be&oN&#f27cb9&oi&#ed78b4&oe&#e974b0&oz&#e470ab&on&#e06ca6&oi&#db68a2&os&#d7639d&oz&#d25f99&oc&#ce5b94&oz&#c9578f&oa&#c5538b&ol&#c04f86&on&#bc4a81&oo&#b7467d&os&#b34278&oc"));
        map.put(Enchantment.DAMAGE_ALL, ChatUtil.translateHexColorCodes("&#ffe22a&o&#ffd927&o🗡 &#ffd024&oO&#fec721&os&#febe1f&ot&#feb51c&or&#feac19&oo&#fda316&os&#fd9a13&oc"));
        map.put(Enchantment.FIRE_ASPECT, ChatUtil.translateHexColorCodes("&#ff9808&o&#ff8e0c&o🔥 &#ff8511&oZ&#ff7b15&oa&#ff721a&ok&#ff681e&ol&#ff5f22&oe&#ff5527&ot&#ff4c2b&oy &#ff422f&oo&#ff3934&og&#ff2f38&oi&#ff263d&oe&#ff1c41&on"));
        map.put(Enchantment.DIG_SPEED, ChatUtil.translateHexColorCodes("&#00fbff&o⛏ &#1ce3f9&oW&#38caf4&oy&#54b2ee&od&#7099e8&oa&#8b81e3&oj&#a768dd&on&#c350d7&oo&#df37d2&os&#fb1fcc&oc"));
        map.put(Enchantment.LOOT_BONUS_BLOCKS, ChatUtil.translateHexColorCodes("&#06d282&o☘ &#08cf7d&oS&#0acd78&oz&#0dca73&oc&#0fc86e&oz&#11c568&oe&#13c363&os&#16c05e&oc&#18be59&oi&#1abb54&oe"));

        return map;
    }

    private void addAttributeModifiers(ItemMeta itemMeta, EquipmentSlot slot) {
        AttributeModifier armor = new AttributeModifier(UUID.randomUUID(), "armorModifier", 3.0, AttributeModifier.Operation.ADD_NUMBER, slot);
        AttributeModifier armorToughness = new AttributeModifier(UUID.randomUUID(), "armorToughnessModifier", 3.0, AttributeModifier.Operation.ADD_NUMBER, slot);
        AttributeModifier knockResist = new AttributeModifier(UUID.randomUUID(), "armorToughnessModifier", 0.1, AttributeModifier.Operation.ADD_NUMBER, slot);

        itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, knockResist);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, armorToughness);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armor);
    }
}
