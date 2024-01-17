package pl.textr.boxpvp.listeners.chat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import api.data.Clans;
import api.managers.ClanManager;
import api.redis.BroadcastType;
import api.redis.packet.broadcast.BroadcastGuildMessagePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class ChatGuildListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		Clans g1 = ClanManager.getGuild(player);

		if (event.getMessage().startsWith("!")) {
			if (g1 == null) {
				ChatUtil.sendMessage(player, "§8[§C§l!§8] §cNie posiadasz klanu!");
				return;
			}

			String message = event.getMessage().substring(1);
			BroadcastGuildMessagePacket broadcastPacket = new BroadcastGuildMessagePacket(BroadcastType.G_CHAT, message, g1.getName(), player.getName());
			Main.getPlugin().getRedisService().publishAsync("BroadcastGuild", broadcastPacket);

			event.setCancelled(true);
		}
	}
}