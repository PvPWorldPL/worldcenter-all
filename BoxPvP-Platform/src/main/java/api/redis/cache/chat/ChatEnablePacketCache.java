package api.redis.cache.chat;

import org.bukkit.Bukkit;

import api.redis.PacketListener;
import api.redis.packet.chat.ChatEnablePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class ChatEnablePacketCache implements PacketListener<ChatEnablePacket> {

    @Override
    public void handle(ChatEnablePacket packet) {
        String content = packet.getContent();
        Main.getPlugin().getConfiguration().ChatEnable = true;
        Main.getPlugin().getConfiguration().save();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
    }
}