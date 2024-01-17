package api.redis.cache.broadcast;

import org.bukkit.Bukkit;

import api.redis.BroadcastType;
import api.redis.PacketListener;
import api.redis.packet.broadcast.BroadcastRangiDropPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.DataUtil;

public class BroadcastRangiDropPacketCache implements PacketListener<BroadcastRangiDropPacket> {


	    public void handle(BroadcastRangiDropPacket packet) {
	        BroadcastType broadcastType = packet.getType();

                     if (broadcastType == BroadcastType.TURBOVOUCHERY_CHAT) {
         	            Bukkit.broadcastMessage("");
         	            Bukkit.broadcastMessage(ChatUtil.fixColor(" &8 &7Na serwerze aktywowano &C&lDROP RANG"));
         	            Bukkit.broadcastMessage(ChatUtil.fixColor(" &8 &7Czas trwania: &a" + DataUtil.secondsToString(packet.getContent())));
         	            Bukkit.broadcastMessage("");
         	            Main.getPlugin().getConfiguration().vouchery = packet.getContent();
         	            Main.getPlugin().getConfiguration().save();
                      return;
         	        }

	    }
}
