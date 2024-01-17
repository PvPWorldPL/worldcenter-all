package api.redis.cache.chat;

import org.bukkit.Bukkit;

import api.redis.PacketListener;
import api.redis.packet.chat.ChatVipDisablePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class ChatVipDisablePacketCache implements PacketListener<ChatVipDisablePacket> {

    @Override
    public void handle(ChatVipDisablePacket packet) {
        String content = packet.getContent();
        Main.getPlugin().getConfiguration().VipChatEnable = false;
        Main.getPlugin().getConfiguration().save();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
    }
}