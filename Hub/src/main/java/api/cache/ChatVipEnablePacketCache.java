package api.cache;

import org.bukkit.Bukkit;

import api.packet.ChatVipEnablePacket;
import api.redis.PacketListener;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.utils.other.ChatUtil;

public class ChatVipEnablePacketCache implements PacketListener<ChatVipEnablePacket> {

    @Override
    public void handle(ChatVipEnablePacket packet) {
        String content = packet.getContent();
        LobbyPlugin.getPlugin().getConfiguration().VipChatEnable = true;
        LobbyPlugin.getPlugin().getConfiguration().save();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
    }
}