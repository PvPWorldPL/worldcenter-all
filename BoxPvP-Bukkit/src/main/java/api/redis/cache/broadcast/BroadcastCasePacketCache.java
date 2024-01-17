package api.redis.cache.broadcast;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import api.redis.BroadcastType;
import api.redis.PacketListener;
import api.redis.packet.broadcast.BroadcastCasePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class BroadcastCasePacketCache implements PacketListener<BroadcastCasePacket> {


	public void handle(BroadcastCasePacket packet) {
	    BroadcastType broadcastType = packet.getType();
	    Player p = Bukkit.getPlayer(packet.getPlayer());
	    UserProfile u = UserAccountManager.getUser(p);
	    int ilosc = packet.getIlosc(); // Pobranie ilości skrzynek z pakietu
	    String skrzynka = packet.getSkzynka(); // Pobranie nazwy skrzynki z pakietu

	    if (skrzynka.equalsIgnoreCase("zwykla")) {
	     skrzynka = (ilosc == 1) ? "zwyklej" : "zwyklej";
	     
	    } else if (skrzynka.equalsIgnoreCase("epicka")) {
	        skrzynka = (ilosc == 1) ? "epickiej" : "epickiej";
	        
	    } else if (skrzynka.equalsIgnoreCase("rzadka")) {
	        skrzynka = (ilosc == 1) ? "rzadkiej" : "rzadkiej";
	    }
	    
	    if (broadcastType == BroadcastType.CASE_PLAYER) {
	    	 if (p == null) {
	             return;
	         }
	        p.sendMessage(" ");
	        p.sendMessage(ChatUtil.fixColor(" &8 &7Otrzymales klucz do skrzynki &f" + skrzynka + " &8(&fx" + packet.getIlosc() + "&8)"));
	        p.sendMessage(" ");
	        Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
	            Bukkit.dispatchCommand(u.getPlayer(), "getcase give " + packet.getPlayer() + " " + packet.getSkzynka() + " " + packet.getIlosc());
	        });
	        return;
	    }

	    if (broadcastType == BroadcastType.CASE_ALL) {
	        Bukkit.broadcastMessage("");
	        Bukkit.broadcastMessage(ChatUtil.fixColor(" &8 &7Caly serwer otrzymal klucz! do skrzynki &f" + skrzynka + "&8(&fx" + packet.getIlosc() + "&8)"));
	        Bukkit.broadcastMessage("");
	        Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
	            Bukkit.dispatchCommand(u.getPlayer(), "getcase giveall " + packet.getSkzynka() + " " + packet.getIlosc());
	        });
	        return;
	    }
	}
}