// BroadcastAchievementsPacket
package api.redis.packet.broadcast;

import api.redis.BroadcastType;
import api.redis.Packet;

public class BroadcastAchievementsPacket implements Packet {

    private static final long serialVersionUID = 1L;
    private final BroadcastType type;
    private final String nick;
    private final String achievementCategory;

    public BroadcastAchievementsPacket(BroadcastType type, String nick, String achievementCategory) {
        this.type = type;
        this.nick = nick;
        this.achievementCategory = achievementCategory;
    }

    public BroadcastType getType() {
        return type;
    }

    public String getPlayer() {
        return nick;
    }

    public String getAchievementCategory() {
        return achievementCategory;
    }
}
