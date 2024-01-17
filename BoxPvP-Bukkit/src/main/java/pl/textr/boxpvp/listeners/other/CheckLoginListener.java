package pl.textr.boxpvp.listeners.other;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class CheckLoginListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onLogin(final PlayerLoginEvent e) {
		final Player p = e.getPlayer();

		if (Main.getPlugin().getConfiguration().enablewhitelist() && !Main.getPlugin().getConfiguration().whitelistnicks().contains(p.getName())) {
			e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "\n" + ChatUtil.fixColor(Main.getPlugin().getConfiguration().whitelistreason()));
			return;
		}
		if (!p.hasPermission("core.premium") && Bukkit.getOnlinePlayers().size() >= Main.getPlugin().getConfiguration().slot()) {
			e.disallow(PlayerLoginEvent.Result.KICK_FULL, ChatUtil.translateHexColorCodes("\n" + Main.getPlugin().getConfiguration().fullserver()));

		}
		
	}

}
