package api.redis.cache.guild;

import org.bukkit.Bukkit;

import api.data.Clans;
import api.managers.ClanManager;
import api.redis.PacketListener;
import api.redis.packet.guild.UpdateGuildRemovePlayerPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class UpdateGuildRemovePlayerPacketCache implements PacketListener<UpdateGuildRemovePlayerPacket> {

    @Override
    public void handle(UpdateGuildRemovePlayerPacket packet) {
    final Clans guild2 = ClanManager.getGuild(packet.getGuildTag());
        if (guild2 != null) {
            guild2.removeMember(packet.getPlayerName());
        }

        ClanManager.downloadGuild(packet.getGuildTag());
            Bukkit.broadcastMessage(ChatUtil.translateHexColorCodes("&7Gracz &c" + packet.getPlayerName() + " &7opuscil klan &c" + packet.getGuildTag()));
           if (Main.getPlugin().getConfiguration().debug) {
        	      System.out.println("usunieto gracza " + packet.getPlayerName() + "z klanu " + guild2); 
           }    
    }
}