package pl.textr.boxpvp.listeners.player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;

import pl.textr.boxpvp.utils.ChatUtil;

public class PlayerCommandPreprocessListener implements Listener {

	private static final HelpMap HELP_MAP = Bukkit.getServer().getHelpMap();
	
	private static final Set<String> BLOCKED_COMMANDS = new HashSet<>(Arrays.asList(
	        "/pl", "/plugins", "/?", "//?", "//", "/bukkit:help",
	        "/skript", "//calc", "/worldedit:/calc", "/worldedit:/calculate",
	        "//eval", "/worldedit:/solve", "//evaluate", "/worldedit:/eval",
	        "/worldedit:/evaluate", "//solve", "//deop", "//calculate", "/logout",
	        "/bukkit:ban", "bukkit:ban", "logout", "sk", "/sk", "/help", "/about",
	        "/bukkit:about", "/ver", "/dh", "/cc", "/crazycrates", "/version",
	        "/bukkit:ver", "/bukkit:version", "/bukkit:?", "/logout", "/me",
	        "/bukkit:me", "/say", "/pay", "/bukkit:say", "/sk"
	));
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onUnknown(PlayerCommandPreprocessEvent event) {
	    if (!event.isCancelled()) {
	        Player player = event.getPlayer();
	        String command = event.getMessage().split(" ")[0].toLowerCase();
	        HelpTopic helpTopic = HELP_MAP.getHelpTopic(command);

	        if (helpTopic == null) {
	            String formattedCommand = command.replace("//", "/").replace("%newline%", "\n");
	           ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&7Komenda  &#ff2609&l" + formattedCommand + " &7nie została odnaleziona"));

	            event.setCancelled(true);
	        }
	    }
	}



    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerCommandPreprocess1(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("core.plugins")) {
            String message = event.getMessage();

            if (message.startsWith("/")) {
                String command = event.getMessage().split(" ")[0].toLowerCase();
                if (BLOCKED_COMMANDS.contains(command)) {
                    event.setCancelled(true);
                    ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&7Komenda &#ff2609&l{command} &7nie została odnaleziona".replace("{command}", message.replace("//", "/").replace("%newline%", "\n"))));
                }
            }
        }
    }
}
