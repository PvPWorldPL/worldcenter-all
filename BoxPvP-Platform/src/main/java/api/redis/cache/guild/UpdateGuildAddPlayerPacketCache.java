package api.redis.cache.guild;

import org.bukkit.Bukkit;

import api.data.Clans;
import api.managers.ClanManager;
import api.redis.PacketListener;
import api.redis.packet.guild.UpdateGuildAddPlayerPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class UpdateGuildAddPlayerPacketCache implements PacketListener<UpdateGuildAddPlayerPacket> {

    @Override
    public void handle(UpdateGuildAddPlayerPacket packet) {
  
        final Clans guild2 = ClanManager.getGuild(packet.getGuildTag());
        if (guild2 != null) {
            guild2.addMember(packet.getPlayerName());
        }
        ClanManager.downloadGuild(packet.getGuildTag());
            Bukkit.broadcastMessage(ChatUtil.translateHexColorCodes("&c" + packet.getPlayerName() + " &7dolaczyl do klanu &c[" + guild2.getTag() + "&c]"));
           if (Main.getPlugin().getConfiguration().debug) {
           System.out.println("dodano gracza do gildi"); 
    }
    }
}