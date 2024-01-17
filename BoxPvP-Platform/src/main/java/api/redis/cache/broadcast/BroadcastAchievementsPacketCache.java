package api.redis.cache.broadcast;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import api.redis.BroadcastType;
import api.redis.PacketListener;
import api.redis.packet.broadcast.BroadcastAchievementsPacket;
import api.redis.packet.broadcast.BroadcastCasePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class BroadcastAchievementsPacketCache implements PacketListener<BroadcastAchievementsPacket> {

  
  
    public void handle(BroadcastAchievementsPacket packet) {
        Player p = Bukkit.getPlayer(packet.getPlayer());

        int achievementNumber = getAchievementNumber(packet.getAchievementCategory());
        if (achievementNumber != -1) {
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&4OS&8]" + packet.getPlayer() +
                    "&codblokowal osiagniecie &4" + packet.getAchievementCategory() + " #" + achievementNumber));
            Bukkit.broadcastMessage(" ");
        }
    }
    
    private int getAchievementNumber(String achievementCategory) {
        if (achievementCategory.startsWith("KILLS_")) {
            return Integer.parseInt(achievementCategory.substring("KILLS_".length()));
       //dodajesz kolejna tak jak tutaj masz czyli else
        } else if (achievementCategory.startsWith("DEATHS_")) {
            return Integer.parseInt(achievementCategory.substring("DEATHS_".length()));
        }
        return -1; // Or handle the case where the achievement category is not recognized
    }

}
