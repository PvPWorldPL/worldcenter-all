package pl.textr.core.utils.other;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutTitle;

public class ChatUtil {
    public static String iiIi;
    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");
    public final static char COLOR_CHAR = ChatColor.COLOR_CHAR;
    private static final List<String> blockedWords = List.of(".pl", ".eu", ".net", ".com", ".", "zapraszamy na", "zapraszam", "ip", "wbijajcie na");




    public static String fixColor(final String s) {
        if (s == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace(">>", "»").replace("<<", "«"));
    }

    public static String translateHexColorCodes(String str) {
        Matcher matcher = HEX_PATTERN.matcher(ChatColor.translateAlternateColorCodes('&', str));
        StringBuffer buffer = new StringBuffer();

        while (matcher.find())
            matcher.appendReplacement(buffer, ChatColor.of(matcher.group(1)).toString());

        return matcher.appendTail(buffer).toString();
    }


    public static boolean isBlocked(final String mess) {
        return blockedWords.stream()
                .anyMatch(s -> mess.toLowerCase().contains(s.toLowerCase()));
    }

    
    public static boolean isAlphaNumeric(final String s) {
        return s.matches("^[a-zA-Z0-9_]*$");
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



    public static void sendTitle(final Player p, final String title, final String subttitle) {
        sendTitle(p, title, subttitle, 30, 40, 30);
    }

    public static void sendTitle(final Player player, String title, String subtitle, final int fadeIn, final int stay, final int fadeOut) {
        if (title == null) {
            title = "";
        }
        if (subtitle == null) {
            subtitle = "";
        }
        final CraftPlayer craftPlayer = (CraftPlayer) player;
        final PacketPlayOutTitle packetTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);
        craftPlayer.getHandle().playerConnection.sendPacket(packetTimes);
        final IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + translateHexColorCodes(fixColor(title)) + "\"}");
        final PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        craftPlayer.getHandle().playerConnection.sendPacket(packetTitle);
        final IChatBaseComponent chatSubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + translateHexColorCodes(fixColor(subtitle)) + "\"}");
        final PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubtitle);
        craftPlayer.getHandle().playerConnection.sendPacket(packetSubtitle);
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