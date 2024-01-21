package api.redis.cache.broadcast;

import org.bukkit.entity.Player;

import api.data.Clans;
import api.managers.ClanManager;
import api.redis.BroadcastType;
import api.redis.PacketListener;
import api.redis.packet.broadcast.BroadcastGuildMessagePacket;
import pl.textr.survival.utils.ChatUtil;

public class BroadcastGuildMessagePacketCache implements PacketListener<BroadcastGuildMessagePacket> {

    public void handle(BroadcastGuildMessagePacket packet) {
        BroadcastType broadcastType = packet.getType();   
        final Clans g = ClanManager.getGuild(packet.getGuildTag());      
        if (broadcastType == BroadcastType.G_CHAT) {
            for (final Player o : g.getOnlineMembers()) {     		
                ChatUtil.sendMessage(o, "&8[&2Klan&8] &7" + packet.getPlayerName() + " &8- &a" + packet.getMessage());
            }
            return;
        }
    }
}






