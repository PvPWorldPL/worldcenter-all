package api.cache;

import org.bukkit.Bukkit;

import api.packet.ChatEnablePacket;
import api.redis.PacketListener;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.utils.other.ChatUtil;

public class ChatEnablePacketCache implements PacketListener<ChatEnablePacket> {

    @Override
    public void handle(ChatEnablePacket packet) {
        String content = packet.getContent();
        LobbyPlugin.getPlugin().getConfiguration().ChatEnable = true;
        LobbyPlugin.getPlugin().getConfiguration().save();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
    }
}