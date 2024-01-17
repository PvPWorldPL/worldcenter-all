package api.redis.cache.guild;

import org.bukkit.Bukkit;

import api.data.Clans;
import api.managers.ClanManager;
import api.redis.PacketListener;
import api.redis.packet.guild.UpdateGuildKickPlayerPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class UpdateGuildKickPlayerPacketCache implements PacketListener<UpdateGuildKickPlayerPacket> {

    @Override
    public void handle(UpdateGuildKickPlayerPacket packet) {
      final Clans guild2 = ClanManager.getGuild(packet.getGuildTag());
        if (guild2 != null) {
            guild2.removeMember(packet.getPlayerName());
        }
        ClanManager.downloadGuild(packet.getGuildTag());
            Bukkit.broadcastMessage(ChatUtil.translateHexColorCodes("&c" + packet.getPlayerName() + " &7zostal wyrzucony z klanu &c" + guild2.getTag() + ""));
           if (Main.getPlugin().getConfiguration().debug) {
        	      System.out.println("usunieto gracza " + packet.getPlayerName() + " z klanu " + guild2); 
        	       
           }
    }
    
}