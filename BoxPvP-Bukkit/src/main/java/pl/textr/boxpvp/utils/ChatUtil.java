package pl.textr.boxpvp.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;


import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import net.md_5.bungee.api.ChatColor;

public class ChatUtil {
    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");
    private static final List<String> blockedWords = List.of(".pl", ".eu", ".net", ".com", ".", "zapraszamy na", "zapraszam", "ip", "wbijajcie na");
	

    public static String fixColor(final String s) {
        if (s == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace(">>", "»").replace("<<", "«"));
    }

    public static List<String> createList(String... lines) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, lines);
        return list;
    }


    public static String translateHexColorCodes(String str) {
        Matcher matcher = HEX_PATTERN.matcher(ChatColor.translateAlternateColorCodes('&', str));
        StringBuilder buffer = new StringBuilder();

        while (matcher.find())
            matcher.appendReplacement(buffer, ChatColor.of(matcher.group(1)).toString());

        return matcher.appendTail(buffer).toString();
    }

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isInElytra(Location loc) {
        RegionContainer rg = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = rg.createQuery();
        for (ProtectedRegion protectedRegion : query.getApplicableRegions(BukkitAdapter.adapt(loc))) {
          if (protectedRegion.getId().equalsIgnoreCase("elytra"))
            return true; 
        } 
        return false;
      }

    
    public static String formatAmount(Double money) {
        double amount = Math.abs(money);

        if (amount >= 1_000_000_000_000_000_000L) {
            return format(amount / 1_000_000_000_000_000_000L) + "Q";
        } else if (amount >= 1_000_000_000_000_000L) {
            return format(amount / 1_000_000_000_000_000L) + "T";
        } else if (amount >= 1_000_000_000_000_000L) {
            return format(amount / 1_000_000_000_000_000L) + "BLD";
        } else if (amount >= 1_000_000_000_000L) {
            return format(amount / 1_000_000_000_000L) + "B";
        } else if (amount >= 1_000_000_000) {
            return format(amount / 1_000_000_000) + "MLD";
        } else if (amount >= 1_000_000) {
            return format(amount / 1_000_000) + "M";
        } else if (amount >= 1_000) {
            return format(amount / 1_000) + "K";
        } else {
            return format(amount);
        }
    }

    
    public static String formatAmount(BigDecimal bigDecimal) {
        double amount = Math.abs(bigDecimal.doubleValue());

        if (amount >= 1_000_000_000_000_000_000L) {
            return format(amount / 1_000_000_000_000_000_000L) + "Q";
        } else if (amount >= 1_000_000_000_000_000L) {
            return format(amount / 1_000_000_000_000_000L) + "T";
        } else if (amount >= 1_000_000_000_000_000L) {
            return format(amount / 1_000_000_000_000_000L) + "BLD";
        } else if (amount >= 1_000_000_000_000L) {
            return format(amount / 1_000_000_000_000L) + "B";
        } else if (amount >= 1_000_000_000) {
            return format(amount / 1_000_000_000) + "MLD";
        } else if (amount >= 1_000_000) {
            return format(amount / 1_000_000) + "M";
        } else if (amount >= 1_000) {
            return format(amount / 1_000) + "K";
        } else {
            return format(amount);
        }
    }




    public static double roundTwoDecimals(String amount) {
        return Double.parseDouble(new DecimalFormat("##.00").format(Double.parseDouble(amount)));
    }


    public static String format(final Double d) {
        final DecimalFormat df = new DecimalFormat("#.##");
        return df.format(d);
    }

    public static boolean isBlocked(final String mess) {
        return blockedWords.stream()
                .anyMatch(s -> mess.toLowerCase().contains(s.toLowerCase()));
    }




    public static boolean isAlphaNumeric(final String s) {
        return s.matches("^[a-zA-Z0-9_]+$");
    }
    
    public static boolean isInteger(final String string) {
        return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
    }


    public static boolean sendMessage(final CommandSender p, final String message) {
        p.sendMessage(fixColor(message));
        return false;
    }
    
    public static boolean sendMessage(final Collection<? extends CommandSender> collection, final String message) {
        for (final CommandSender cs : collection) {
            sendMessage(cs, message);
        }
        return true;
    }



    public static void sendTitle(final Player player, String title, String subtitle) {
        sendTitle(player, title, subtitle, 10, 20, 10); // Provide default values or adjust them accordingly
    }

    public static void sendTitle(final Player player, String title, String subtitle, final int fadeIn, final int stay, final int fadeOut) {
        if (title == null) {
            title = "";
        }
        if (subtitle == null) {
            subtitle = "";
        }

        // Send title
        player.sendTitle(translateHexColorCodes(fixColor(title)), translateHexColorCodes(fixColor(subtitle)), fadeIn, stay, fadeOut);
    }




    public static void giveItems(final Player p, final ItemStack... items) {
        final Inventory i = p.getInventory();
        final HashMap<Integer, ItemStack> notStored = i.addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItem(p.getLocation(), e.getValue());
            sendMessage(p, "&cNie miales miejsca w ekwipunku, przedmioty wypadly na ziemie.");
        }
    }

    public static int getAmount(final Player arg0, final ItemStack arg1) {
        if (arg1 == null) {
            return 0;
        }
        int amount = 0;
        for (int i = 0; i < 36; ++i) {
            final ItemStack slot = arg0.getInventory().getItem(i);
            if (slot != null && slot.isSimilar(arg1)) {
                amount += slot.getAmount();
            }
        }
        return amount;
    }


    public static Material getMaterial(final String materialName) {
        Material returnMaterial = null;
        if (isInteger(materialName)) {
            returnMaterial = Material.getMaterial(materialName);
        } else {
            returnMaterial = Material.matchMaterial(materialName);
        }
        return returnMaterial;
    }



}