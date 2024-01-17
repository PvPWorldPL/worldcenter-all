package api.redis.cache.guild;

import org.bukkit.Bukkit;

import api.data.Clans;
import api.managers.ClanManager;
import api.redis.PacketListener;
import api.redis.packet.guild.UpdateGuildDeletePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class UpdateGuildDeletePacketCache implements PacketListener<UpdateGuildDeletePacket> {

    @Override
    public void handle(UpdateGuildDeletePacket packet) {
    final Clans tag = ClanManager.getGuild(packet.getGuildTag());

            ClanManager.deleteGuild(tag);

        Bukkit.broadcastMessage(ChatUtil.translateHexColorCodes("&cKlan [" + packet.getGuildTag() + "&c] &7zostal usuniety przez &c" + packet.getPlayerName() + "&c!"));
        if (Main.getPlugin().getConfiguration().debug) {
            System.out.println("pomyslnie  usunieto klan " + packet.getGuildTag());   
            
    }
    }
}