package pl.textr.connect.utils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ChatUtil
{

    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");
    public final static char COLOR_CHAR = ChatColor.COLOR_CHAR;
    

    
    
    public static String translateHexColorCodes(String str) {
        Matcher matcher = HEX_PATTERN.matcher(ChatColor.translateAlternateColorCodes('&', str));
        StringBuffer buffer = new StringBuffer();

        while (matcher.find())
            matcher.appendReplacement(buffer, ChatColor.of(matcher.group(1)).toString());

        return matcher.appendTail(buffer).toString();
    }
    
    
    
    public static String fixColor(final String string) {
        if (string.equalsIgnoreCase("")) {
            return string;
        }
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    

    public static void sendMessage(final ProxiedPlayer player, final String string) {
        BaseComponent[] message = TextComponent.fromLegacyText(fixColor(string));
        player.sendMessage(message);
    }

    public static void sendMessage(final CommandSender sender, final String string) {
        BaseComponent[] message = TextComponent.fromLegacyText(fixColor(string));
        sender.sendMessage(message);
    }

    
    public static void sendMessage(final Collection<? extends CommandSender> collection, final String msg) {
        BaseComponent[] message = TextComponent.fromLegacyText(fixColor(msg));
        for (final CommandSender cs : collection) {
            cs.sendMessage(message);
        }
    }

}
