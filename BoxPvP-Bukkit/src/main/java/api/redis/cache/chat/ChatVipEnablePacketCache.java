package api.redis.cache.chat;

import org.bukkit.Bukkit;

import api.redis.PacketListener;
import api.redis.packet.chat.ChatVipEnablePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class ChatVipEnablePacketCache implements PacketListener<ChatVipEnablePacket> {

    @Override
    public void handle(ChatVipEnablePacket packet) {
        String content = packet.getContent();
        Main.getPlugin().getConfiguration().VipChatEnable = true;
        Main.getPlugin().getConfiguration().save();
        
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
    }
}