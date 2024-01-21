package pl.textr.utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageFormatter {
    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");

    public static String translateHexColorCodes(String str) {
        Matcher matcher = HEX_PATTERN.matcher(ChatColor.translateAlternateColorCodes('&', str));
        StringBuilder buffer = new StringBuilder();
        while (matcher.find())
         matcher.appendReplacement(buffer, ChatColor.of(matcher.group(1)).toString());
        return matcher.appendTail(buffer).toString();
    }

    public static void sendFormattedMessage(final Player p, final String message) {
        p.sendMessage(formatColor(message));
    }
    public static String formatColor(final String s) {
        if (s == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace(">>", "»").replace("<<", "«"));
    }

}
