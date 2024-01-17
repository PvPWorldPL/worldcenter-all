package api.redis.packet.broadcast;

import api.redis.BroadcastType;
import api.redis.Packet;

public class BroadcastRangiDropPacket implements Packet {

	private static final long serialVersionUID = 1L;
	private final BroadcastType type;
    private final long content;

    public BroadcastRangiDropPacket(BroadcastType type, long content) {
        this.type = type;
        this.content = content;
    }

    public BroadcastType getType() {
        return type;
    }

    public long getContent() {
        return content;
    }

}
