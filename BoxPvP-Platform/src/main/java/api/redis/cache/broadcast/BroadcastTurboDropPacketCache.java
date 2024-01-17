package api.redis.cache.broadcast;

import org.bukkit.Bukkit;

import api.redis.BroadcastType;
import api.redis.PacketListener;
import api.redis.packet.broadcast.BroadcastTurboDropPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.DataUtil;

public class BroadcastTurboDropPacketCache implements PacketListener<BroadcastTurboDropPacket> {


	    public void handle(BroadcastTurboDropPacket packet) {
	        BroadcastType broadcastType = packet.getType();

	        if (broadcastType == BroadcastType.TURBODROP_CHAT) {
	            Bukkit.broadcastMessage("");
	            Bukkit.broadcastMessage(ChatUtil.fixColor(" &8 &7Na serwerze aktywowano &C&lDROP DROP &8(&aX" + Main.getPlugin().getConfiguration().turbodropmnoznik() + "&8)"));
	            Bukkit.broadcastMessage(ChatUtil.fixColor(" &8 &7Czas trwania: &a" + DataUtil.secondsToString(packet.getContent())));
	            Bukkit.broadcastMessage("");
	            Main.getPlugin().getConfiguration().turbodrop = packet.getContent();
	            Main.getPlugin().getConfiguration().save();
			}
       
	    }
}
