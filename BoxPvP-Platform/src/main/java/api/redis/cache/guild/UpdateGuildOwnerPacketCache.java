package api.redis.cache.guild;

import org.bukkit.Bukkit;

import api.data.Clans;
import api.managers.ClanManager;
import api.redis.PacketListener;
import api.redis.packet.guild.UpdateGuildOwnerPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class UpdateGuildOwnerPacketCache implements PacketListener<UpdateGuildOwnerPacket> {

    @Override
    public void handle(UpdateGuildOwnerPacket packet) {

        final Clans g = ClanManager.getGuild(packet.getGuildTag());
        if (g != null) {
            g.setOwner(packet.getPlayerName());
        }
        if (g != null) {
            ClanManager.downloadGuild(g.getTag());
        }
        Bukkit.broadcastMessage(ChatUtil.translateHexColorCodes("&c" + packet.getPlayerName() + " &7zostal nowym liderem klanu &c[" + g.getTag() + "&c]"));
      if (Main.getPlugin().getConfiguration().debug) {
          System.out.println("zmieniono wlasciciela klanu" + g); 
      }
    }
}