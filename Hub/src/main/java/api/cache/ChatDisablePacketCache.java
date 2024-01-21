package api.cache;

import org.bukkit.Bukkit;

import api.packet.ChatDisablePacket;
import api.redis.PacketListener;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.utils.other.ChatUtil;

public class ChatDisablePacketCache implements PacketListener<ChatDisablePacket> {

	   @Override
	    public void handle(ChatDisablePacket packet) {
	        String content = packet.getContent();
	        LobbyPlugin.getPlugin().getConfiguration().ChatEnable = false;
	        LobbyPlugin.getPlugin().getConfiguration().save();
	        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
	    }
	}