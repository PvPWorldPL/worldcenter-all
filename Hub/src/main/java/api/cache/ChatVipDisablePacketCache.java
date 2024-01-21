package api.cache;

import org.bukkit.Bukkit;

import api.packet.ChatVipDisablePacket;
import api.redis.PacketListener;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.utils.other.ChatUtil;

public class ChatVipDisablePacketCache implements PacketListener<ChatVipDisablePacket> {

    @Override
    public void handle(ChatVipDisablePacket packet) {
        String content = packet.getContent();
        LobbyPlugin.getPlugin().getConfiguration().VipChatEnable = false;
        LobbyPlugin.getPlugin().getConfiguration().save();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
    }
}