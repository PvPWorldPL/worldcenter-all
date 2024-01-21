package api.redis.packet.broadcast;

import api.redis.BroadcastType;
import api.redis.Packet;

public class BroadcastGuildMessagePacket implements Packet {

	private static final long serialVersionUID = 1L;
	private final String message;
    private final String guildTag;
    private final String playerName;
    private final BroadcastType type;
    
    public BroadcastGuildMessagePacket(BroadcastType type, String message, String guildTag,String playerName) {
        this.message = message;
        this.type = type;
        this.guildTag = guildTag;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
    
    public String getMessage() {
        return message;
    }
    
	public BroadcastType getType() {
		return type;
	}

	public String getGuildTag() {
		 return guildTag;
    
	}
}
