package api.redis.cache.chat;

import api.redis.PacketListener;
import api.redis.packet.chat.ChatSlowPacket;
import pl.textr.boxpvp.Main;

public class ChatSlowPacketCache implements PacketListener<ChatSlowPacket> {

    @Override
    public void handle(ChatSlowPacket packet) {
        int content = packet.getAmount();
        Main.getPlugin().getConfiguration().chatslowmode = content;
        Main.getPlugin().getConfiguration().save();
        Main.getPlugin().getConfiguration().load();
    }
    
}