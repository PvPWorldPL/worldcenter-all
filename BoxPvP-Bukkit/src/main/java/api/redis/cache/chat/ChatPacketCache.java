package api.redis.cache.chat;

import org.bukkit.Bukkit;

import api.redis.PacketListener;
import api.redis.packet.chat.ChatPacket;
import net.kyori.adventure.text.Component;
import pl.textr.boxpvp.Main;

public class ChatPacketCache implements PacketListener<ChatPacket> {

	@Override

	public void handle(ChatPacket packet) {
	    String content = packet.getContent();
Bukkit.broadcastMessage(content);


	}
}