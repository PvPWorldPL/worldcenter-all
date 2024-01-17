package api.redis.cache.broadcast;

import org.bukkit.Bukkit;

import api.redis.BroadcastType;
import api.redis.PacketListener;
import api.redis.packet.broadcast.BroadcastPacket;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import pl.textr.boxpvp.utils.ChatUtil;


public class BroadcastPacketCache implements PacketListener<BroadcastPacket> {

    public void handle(BroadcastPacket packet) {
    	
        BroadcastType broadcastType = packet.getType();       
        if (broadcastType == BroadcastType.CHAT) {
            Bukkit.broadcastMessage(ChatUtil.translateHexColorCodes(packet.getContent()));
            return;
        }
        if (broadcastType == BroadcastType.TITLE) {
            Bukkit.getServer().getOnlinePlayers().forEach(p -> ChatUtil.sendTitle(p, packet.getContent(), " ", 55, 55, 55));
            return;
        }
        if (broadcastType == BroadcastType.SUBTITLE) {
            Bukkit.getServer().getOnlinePlayers().forEach(p -> ChatUtil.sendTitle(p, " ", packet.getContent(), 55, 55, 55));
            return;
        }
        if (broadcastType == BroadcastType.ACTIONBAR) {
            Bukkit.getServer().getOnlinePlayers().forEach(p -> p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(packet.getContent())));
        }

       
    }
    }





