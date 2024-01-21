package api.cache;

import org.bukkit.Bukkit;

import api.packet.ChatPacket;
import api.redis.PacketListener;
import pl.textr.core.utils.other.ChatUtil;

public class ChatPacketCache implements PacketListener<ChatPacket> {

    @Override
    public void handle(ChatPacket packet) {
        String content = packet.getContent();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));
    }
}