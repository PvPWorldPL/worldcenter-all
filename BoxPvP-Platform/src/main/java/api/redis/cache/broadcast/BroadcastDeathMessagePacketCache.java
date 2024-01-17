package api.redis.cache.broadcast;

import org.bukkit.Bukkit;

import api.redis.PacketListener;
import api.redis.packet.broadcast.BroadcastDeathMessagePacket;
import pl.textr.boxpvp.utils.ChatUtil;


public class BroadcastDeathMessagePacketCache implements PacketListener<BroadcastDeathMessagePacket> {

    public void handle(BroadcastDeathMessagePacket packet) {
    Bukkit.broadcastMessage(ChatUtil.fixColor("&7Gracz &r" + packet.getVictim() + "&r" + " &8(&c-" + packet.getminus() + "&8) &7zostal zabity przez &r" + (packet.getKiller() + " &8(&a" + ((packet.getplus() >= 0) ? ("+" + packet.getplus()) : Integer.valueOf(packet.getplus())) + "&8")));		
        }
          
    }





