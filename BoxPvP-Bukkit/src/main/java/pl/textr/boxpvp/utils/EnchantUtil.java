package pl.textr.boxpvp.utils;

import java.util.HashMap;

import org.bukkit.enchantments.Enchantment;

public final class EnchantUtil {
    private static final HashMap<String, Enchantment> enchants;

    public static Enchantment get(final String name) {
        return EnchantUtil.enchants.get(name.toLowerCase());
    }

    public static HashMap<String, Enchantment> getEnchants() {
        return EnchantUtil.enchants;
    }

    static {
        (enchants = new HashMap<>()).put("alldamage", Enchantment.DAMAGE_ALL);
        EnchantUtil.enchants.put("alldmg", Enchantment.DAMAGE_ALL);
        EnchantUtil.enchants.put("sharpness", Enchantment.DAMAGE_ALL);
        EnchantUtil.enchants.put("sharp", Enchantment.DAMAGE_ALL);
        EnchantUtil.enchants.put("dal", Enchantment.DAMAGE_ALL);
        EnchantUtil.enchants.put("ardmg", Enchantment.DAMAGE_ARTHROPODS);
        EnchantUtil.enchants.put("baneofarthropods", Enchantment.DAMAGE_ARTHROPODS);
        EnchantUtil.enchants.put("baneofarthropod", Enchantment.DAMAGE_ARTHROPODS);
        EnchantUtil.enchants.put("arthropod", Enchantment.DAMAGE_ARTHROPODS);
        EnchantUtil.enchants.put("dar", Enchantment.DAMAGE_ARTHROPODS);
        EnchantUtil.enchants.put("undeaddamage", Enchantment.DAMAGE_UNDEAD);
        EnchantUtil.enchants.put("smite", Enchantment.DAMAGE_UNDEAD);
        EnchantUtil.enchants.put("du", Enchantment.DAMAGE_UNDEAD);
        EnchantUtil.enchants.put("digspeed", Enchantment.DIG_SPEED);
        EnchantUtil.enchants.put("efficiency", Enchantment.DIG_SPEED);
        EnchantUtil.enchants.put("minespeed", Enchantment.DIG_SPEED);
        EnchantUtil.enchants.put("cutspeed", Enchantment.DIG_SPEED);
        EnchantUtil.enchants.put("ds", Enchantment.DIG_SPEED);
        EnchantUtil.enchants.put("eff", Enchantment.DIG_SPEED);
        EnchantUtil.enchants.put("durability", Enchantment.DURABILITY);
        EnchantUtil.enchants.put("dura", Enchantment.DURABILITY);
        EnchantUtil.enchants.put("unbreaking", Enchantment.DURABILITY);
        EnchantUtil.enchants.put("d", Enchantment.DURABILITY);
        EnchantUtil.enchants.put("thorns", Enchantment.THORNS);
        EnchantUtil.enchants.put("highcrit", Enchantment.THORNS);
        EnchantUtil.enchants.put("thorn", Enchantment.THORNS);
        EnchantUtil.enchants.put("highercrit", Enchantment.THORNS);
        EnchantUtil.enchants.put("t", Enchantment.THORNS);
        EnchantUtil.enchants.put("fireaspect", Enchantment.FIRE_ASPECT);
        EnchantUtil.enchants.put("fire", Enchantment.FIRE_ASPECT);
        EnchantUtil.enchants.put("meleefire", Enchantment.FIRE_ASPECT);
        EnchantUtil.enchants.put("meleeflame", Enchantment.FIRE_ASPECT);
        EnchantUtil.enchants.put("fa", Enchantment.FIRE_ASPECT);
        EnchantUtil.enchants.put("knockback", Enchantment.KNOCKBACK);
        EnchantUtil.enchants.put("kback", Enchantment.KNOCKBACK);
        EnchantUtil.enchants.put("kb", Enchantment.KNOCKBACK);
        EnchantUtil.enchants.put("k", Enchantment.KNOCKBACK);
        EnchantUtil.enchants.put("blockslootbonus", Enchantment.LOOT_BONUS_BLOCKS);
        EnchantUtil.enchants.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
        EnchantUtil.enchants.put("fort", Enchantment.LOOT_BONUS_BLOCKS);
        EnchantUtil.enchants.put("lbb", Enchantment.LOOT_BONUS_BLOCKS);
        EnchantUtil.enchants.put("mobslootbonus", Enchantment.LOOT_BONUS_MOBS);
        EnchantUtil.enchants.put("mobloot", Enchantment.LOOT_BONUS_MOBS);
        EnchantUtil.enchants.put("looting", Enchantment.LOOT_BONUS_MOBS);
        EnchantUtil.enchants.put("lbm", Enchantment.LOOT_BONUS_MOBS);
        EnchantUtil.enchants.put("oxygen", Enchantment.OXYGEN);
        EnchantUtil.enchants.put("respiration", Enchantment.OXYGEN);
        EnchantUtil.enchants.put("breathing", Enchantment.OXYGEN);
        EnchantUtil.enchants.put("breath", Enchantment.OXYGEN);
        EnchantUtil.enchants.put("o", Enchantment.OXYGEN);
        EnchantUtil.enchants.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
        EnchantUtil.enchants.put("prot", Enchantment.PROTECTION_ENVIRONMENTAL);
        EnchantUtil.enchants.put("protect", Enchantment.PROTECTION_ENVIRONMENTAL);
        EnchantUtil.enchants.put("p", Enchantment.PROTECTION_ENVIRONMENTAL);
        EnchantUtil.enchants.put("explosionsprotection", Enchantment.PROTECTION_EXPLOSIONS);
        EnchantUtil.enchants.put("explosionprotection", Enchantment.PROTECTION_EXPLOSIONS);
        EnchantUtil.enchants.put("expprot", Enchantment.PROTECTION_EXPLOSIONS);
        EnchantUtil.enchants.put("blastprotection", Enchantment.PROTECTION_EXPLOSIONS);
        EnchantUtil.enchants.put("blastprotect", Enchantment.PROTECTION_EXPLOSIONS);
        EnchantUtil.enchants.put("pe", Enchantment.PROTECTION_EXPLOSIONS);
        EnchantUtil.enchants.put("fallprotection", Enchantment.PROTECTION_FALL);
        EnchantUtil.enchants.put("fallprot", Enchantment.PROTECTION_FALL);
        EnchantUtil.enchants.put("featherfall", Enchantment.PROTECTION_FALL);
        EnchantUtil.enchants.put("featherfalling", Enchantment.PROTECTION_FALL);
        EnchantUtil.enchants.put("pfa", Enchantment.PROTECTION_FALL);
        EnchantUtil.enchants.put("fireprotection", Enchantment.PROTECTION_FIRE);
        EnchantUtil.enchants.put("flameprotection", Enchantment.PROTECTION_FIRE);
        EnchantUtil.enchants.put("fireprotect", Enchantment.PROTECTION_FIRE);
        EnchantUtil.enchants.put("flameprotect", Enchantment.PROTECTION_FIRE);
        EnchantUtil.enchants.put("fireprot", Enchantment.PROTECTION_FIRE);
        EnchantUtil.enchants.put("flameprot", Enchantment.PROTECTION_FIRE);
        EnchantUtil.enchants.put("pf", Enchantment.PROTECTION_FIRE);
        EnchantUtil.enchants.put("projectileprotection", Enchantment.PROTECTION_PROJECTILE);
        EnchantUtil.enchants.put("projprot", Enchantment.PROTECTION_PROJECTILE);
        EnchantUtil.enchants.put("pp", Enchantment.PROTECTION_PROJECTILE);
        EnchantUtil.enchants.put("silktouch", Enchantment.SILK_TOUCH);
        EnchantUtil.enchants.put("softtouch", Enchantment.SILK_TOUCH);
        EnchantUtil.enchants.put("st", Enchantment.SILK_TOUCH);
        EnchantUtil.enchants.put("waterworker", Enchantment.WATER_WORKER);
        EnchantUtil.enchants.put("aquaaffinity", Enchantment.WATER_WORKER);
        EnchantUtil.enchants.put("watermine", Enchantment.WATER_WORKER);
        EnchantUtil.enchants.put("ww", Enchantment.WATER_WORKER);
        EnchantUtil.enchants.put("firearrow", Enchantment.ARROW_FIRE);
        EnchantUtil.enchants.put("flame", Enchantment.ARROW_FIRE);
        EnchantUtil.enchants.put("flamearrow", Enchantment.ARROW_FIRE);
        EnchantUtil.enchants.put("af", Enchantment.ARROW_FIRE);
        EnchantUtil.enchants.put("arrowdamage", Enchantment.ARROW_DAMAGE);
        EnchantUtil.enchants.put("power", Enchantment.ARROW_DAMAGE);
        EnchantUtil.enchants.put("arrowpower", Enchantment.ARROW_DAMAGE);
        EnchantUtil.enchants.put("ad", Enchantment.ARROW_DAMAGE);
        EnchantUtil.enchants.put("arrowknockback", Enchantment.ARROW_KNOCKBACK);
        EnchantUtil.enchants.put("arrowkb", Enchantment.ARROW_KNOCKBACK);
        EnchantUtil.enchants.put("punch", Enchantment.ARROW_KNOCKBACK);
        EnchantUtil.enchants.put("arrowpunch", Enchantment.ARROW_KNOCKBACK);
        EnchantUtil.enchants.put("ak", Enchantment.ARROW_KNOCKBACK);
        EnchantUtil.enchants.put("infinitearrows", Enchantment.ARROW_INFINITE);
        EnchantUtil.enchants.put("infarrows", Enchantment.ARROW_INFINITE);
        EnchantUtil.enchants.put("infinity", Enchantment.ARROW_INFINITE);
        EnchantUtil.enchants.put("infinite", Enchantment.ARROW_INFINITE);
        EnchantUtil.enchants.put("unlimited", Enchantment.ARROW_INFINITE);
        EnchantUtil.enchants.put("unlimitedarrows", Enchantment.ARROW_INFINITE);
        EnchantUtil.enchants.put("ai", Enchantment.ARROW_INFINITE);
    }


}
