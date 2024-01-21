package api.cache;

import org.bukkit.Bukkit;

import api.packet.ChatClearPacket;
import api.redis.PacketListener;
import pl.textr.core.utils.other.ChatUtil;

public class ChatClearPacketCache implements PacketListener<ChatClearPacket> {

    @Override
    public void handle(ChatClearPacket packet) {
        String content = packet.getContent();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));
        for (int i = 0; i < 100; ++i) {
            Bukkit.getServer().broadcastMessage("");
      }
    }
}