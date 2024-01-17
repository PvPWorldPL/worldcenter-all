package api.redis.packet.broadcast;

import api.redis.BroadcastType;
import api.redis.Packet;

public class BroadcastPacket implements Packet {

    private static final long serialVersionUID = 1L;
	private final BroadcastType type;
    private final String content;

    public BroadcastPacket(BroadcastType type, String content) {
        this.type = type;
        this.content = content;
    }

    public BroadcastType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

}
