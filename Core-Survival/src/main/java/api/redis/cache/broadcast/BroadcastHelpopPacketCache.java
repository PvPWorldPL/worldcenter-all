package api.redis.cache.broadcast;

import org.bukkit.Bukkit;

import api.redis.PacketListener;
import api.redis.packet.broadcast.BroadcastHelpopPacket;
import pl.textr.survival.utils.ChatUtil;


public class BroadcastHelpopPacketCache implements PacketListener<BroadcastHelpopPacket> {

	public void handle(BroadcastHelpopPacket packet) {
	   
	    final String tresc = "&7[&4Zgloszenie&7] &r"  + packet.getPlayerName() + " &8- &e" + packet.getChannelName() + " &8- &r" + packet.getMessage();
	  
             Bukkit.broadcast(ChatUtil.translateHexColorCodes(tresc), "core.cmd.helper");
	}
      
	
}