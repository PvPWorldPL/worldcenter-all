package api.redis.cache.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.redis.PacketListener;
import api.redis.packet.chat.ChatClearPacket;
import pl.textr.boxpvp.utils.ChatUtil;

public class ChatClearPacketCache implements PacketListener<ChatClearPacket> {

    @Override
    public void handle(ChatClearPacket packet) {
        String content = packet.getContent();
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 100; ++i) {
                player.sendMessage("");
            }
        }

        Bukkit.broadcastMessage(ChatUtil.fixColor(content));
    }
}