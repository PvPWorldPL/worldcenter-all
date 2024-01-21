package pl.textr.core.listeners.other;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import pl.textr.core.LobbyPlugin;
import pl.textr.core.utils.other.ChatUtil;

public class CheckLoginListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onLogin(final PlayerLoginEvent e) {
		final Player p = e.getPlayer();

		if (LobbyPlugin.getPlugin().getConfiguration().enablewhitelist && !LobbyPlugin.getPlugin().getConfiguration().whitelistnicks().contains(p.getName())) {
			e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "\n" + ChatUtil.fixColor(LobbyPlugin.getPlugin().getConfiguration().whitelistreason));
			return;
		}
		if (!p.hasPermission("core.premium") && Bukkit.getOnlinePlayers().size() >= LobbyPlugin.getPlugin().getConfiguration().slot) {
			e.disallow(PlayerLoginEvent.Result.KICK_FULL, ChatUtil.fixColor("\n" + LobbyPlugin.getPlugin().getConfiguration().fullserver));
		}
		
	}

}
