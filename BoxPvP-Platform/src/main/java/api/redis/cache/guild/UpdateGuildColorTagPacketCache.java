package api.redis.cache.guild;

import api.data.Clans;
import api.managers.ClanManager;
import api.redis.PacketListener;
import api.redis.packet.guild.UpdateGuildColorTagPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class UpdateGuildColorTagPacketCache implements PacketListener<UpdateGuildColorTagPacket> {

    @Override
    public void handle(UpdateGuildColorTagPacket packet) {
  
        final Clans guild2 = ClanManager.getGuild(packet.getGuildTag());

            guild2.setColorTag(packet.getColor());

        ClanManager.downloadGuildInformation(packet.getGuildTag());
            if (Main.getPlugin().getConfiguration().debug) {
           System.out.println(ChatUtil.translateHexColorCodes("zaktualizowano kolor klanu " + packet.getGuildTag() + " na " + packet.getColor())); 
    }
    }
}