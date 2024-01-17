package api.redis.cache.chat;

import org.bukkit.Bukkit;

import api.redis.PacketListener;
import api.redis.packet.chat.ChatDisablePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class ChatDisablePacketCache implements PacketListener<ChatDisablePacket> {

	   @Override
	    public void handle(ChatDisablePacket packet) {
	        String content = packet.getContent();
	        Main.getPlugin().getConfiguration().ChatEnable = false;
	        Main.getPlugin().getConfiguration().save();
	        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
	    }
	}