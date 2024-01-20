package pl.textr.connect.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.textr.connect.AuthPlugin;
import pl.textr.connect.utils.ChatUtil;
import pl.textr.connect.utils.DataUtil;

public class PreLoginListener implements Listener
{
    private static final Pattern wzorzec = Pattern.compile("^[0-9a-zA-Z-_]+$");
    private static final Map<String, Long> times = new HashMap<>();

    
    
	@EventHandler
    public void onPreLogin(final PreLoginEvent e) {
        if (e.isCancelled()) {
            return;
        }

        final String nick = e.getConnection().getName();
        if (AuthPlugin.getPlugin().getConfiguration().enablewhitelist() &&!AuthPlugin.getPlugin().getConfiguration().adminsnicks().contains(nick) && !AuthPlugin.getPlugin().getConfiguration().whitelistnicks().contains(nick)) {
        	  String whitelistReason = AuthPlugin.getPlugin().getConfiguration().whitelistreason();
              BaseComponent[] cancelReason = TextComponent.fromLegacyText(ChatUtil.fixColor(whitelistReason));
            e.setCancelled(true);
            e.setCancelReason(cancelReason);
            return;
        }
        
        if (BungeeCord.getInstance().getPlayers().size() >= AuthPlugin.getPlugin().getConfiguration().slot && !AuthPlugin.getPlugin().getConfiguration().adminsnicks().contains(nick) && !AuthPlugin.getPlugin().getConfiguration().slotsnicks().contains(nick)) {
        	 String cancelReasonMessage = "&7Serwer jest pełen graczy!\n&cSpróbuj za chwilę!";
             BaseComponent[] cancelReason = TextComponent.fromLegacyText(ChatUtil.fixColor(cancelReasonMessage));
             e.setCancelled(true);  
             e.setCancelReason(cancelReason);
              return;
        }
              
        final Long l = PreLoginListener.times.get(e.getConnection().getAddress().getAddress().getHostAddress());
        if (l != null && l > System.currentTimeMillis() && !AuthPlugin.getPlugin().getConfiguration().adminsnicks().contains(nick)) {      
        String cancelReasonMessage = ChatUtil.fixColor(AuthPlugin.getPlugin().getConfiguration().message_cantlogin.replace("{TIME}", DataUtil.secondsToString(l)));
        BaseComponent[] cancelReason = TextComponent.fromLegacyText(ChatUtil.fixColor(cancelReasonMessage));
        e.setCancelled(true);
        e.setCancelReason(cancelReason);
        return;
    }
        
        if (!PreLoginListener.wzorzec.matcher(nick).find()) {
            e.setCancelled(true);
            e.setCancelReason(new ComponentBuilder(ChatUtil.fixColor("&cTwoj nick zawiera niedozwolone znaki!")).create());
            return;
        }
        PreLoginListener.times.put(e.getConnection().getAddress().getAddress().getHostAddress(), System.currentTimeMillis() + 5000L);
    
    }
}
