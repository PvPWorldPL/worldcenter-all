package pl.textr.lobby.ping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;


public class HexTransform {

    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");
    public final static char COLOR_CHAR = ChatColor.COLOR_CHAR;
    
    
    public static String translateHexColorCodes(String str) {
        Matcher matcher = HEX_PATTERN.matcher(ChatColor.translateAlternateColorCodes('&', str));
        StringBuffer buffer = new StringBuffer();

        while (matcher.find())
            matcher.appendReplacement(buffer, ChatColor.of(matcher.group(1)).toString());

        return matcher.appendTail(buffer).toString();
    }

	
	
	
}
