package api.redis.cache.player;

import api.managers.UserAccountManager;
import api.redis.PacketListener;
import api.redis.packet.player.UpdatePlayerPacket;

public class UpdatePlayerPacketCache implements PacketListener<UpdatePlayerPacket> {

    @Override
    public void handle(UpdatePlayerPacket packet) {
        String content = packet.getContent(); 
   
        UserAccountManager.downloadPlayerInfo(content);  
      
 
        }
    
}