package api.redis.cache.guild;

import api.managers.ClanManager;
import api.redis.PacketListener;
import api.redis.packet.guild.UpdateGuildInformationPacket;

public class UpdateGuildInformationPacketCache implements PacketListener<UpdateGuildInformationPacket> {

    @Override
    public void handle(UpdateGuildInformationPacket packet) {

    	   String content = packet.getContent();     
           ClanManager.downloadGuildInformation(content);    

    

    }
}