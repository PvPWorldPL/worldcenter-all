package pl.textr.boxpvp.listeners.player;


import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.context.MutableContextSet;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import net.luckperms.api.node.types.InheritanceNode;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static net.luckperms.api.context.DefaultContextKeys.SERVER_KEY;

public class PlayerQuitJoinListener implements Listener {


	private static Location SPAWN_LOCATION;


	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		ChatUtil.sendTitle(player, "", "§7Pomyślnie przeteleportowano na §f" + Main.getPlugin().getConfiguration().boxpvpName());
		UserAccountManager.downloadPlayerInfo(player);

	}


	public void handle(Player player) {
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(),
				() -> {
					if (player.getWorld().equals(Bukkit.getWorld("pvp"))) {
						if (SPAWN_LOCATION == null) {
							SPAWN_LOCATION = Main.getPlugin().getConfiguration().getSpawnLocation();
						}
						player.teleport(SPAWN_LOCATION);
					}
				},
				10L
		);
	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {

		e.setLeaveMessage(null);
		this.handle(e.getPlayer());
	}


	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		this.handle(e.getPlayer());
	}
}





