package api.redis.cache.chat;

import org.bukkit.Bukkit;

import api.redis.PacketListener;
import api.redis.packet.chat.ChatRankingPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class ChatRankingPacketCache implements PacketListener<ChatRankingPacket> {

    @Override
    public void handle(ChatRankingPacket packet) {
        int content = packet.getAmount();
        Main.getPlugin().getConfiguration().ranking = content;
        Main.getPlugin().getConfiguration().save();
        Main.getPlugin().getConfiguration().load();
        Bukkit.getServer().broadcastMessage(ChatUtil.fixColor(""));
        Bukkit.getServer().broadcastMessage(ChatUtil.fixColor("&7Chat zostal ustawiony na &a" + content + " &7rankingu"));
        Bukkit.getServer().broadcastMessage(ChatUtil.fixColor(""));
    }
    
}