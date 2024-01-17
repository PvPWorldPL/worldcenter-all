package api.redis.cache.guild;

import org.bukkit.Bukkit;

import api.managers.ClanManager;
import api.redis.PacketListener;
import api.redis.packet.guild.UpdateGuildCreatePacket;
import pl.textr.boxpvp.utils.ChatUtil;

public class UpdateGuildCreatePacketCache implements PacketListener<UpdateGuildCreatePacket> {

    @Override
    public void handle(UpdateGuildCreatePacket packet) {
      ClanManager.downloadGuild(packet.getGuildTag());    
      Bukkit.broadcastMessage(ChatUtil.translateHexColorCodes("&cKlan [" + packet.getGuildTag() + "&c]" +  " &czostal utworzony przez &c" + packet.getPlayerName() + "&c!"));
  
    }
}