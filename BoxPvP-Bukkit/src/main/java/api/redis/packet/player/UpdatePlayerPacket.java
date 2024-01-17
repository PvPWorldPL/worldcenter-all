package api.redis.packet.player;
import api.redis.Packet;

public class UpdatePlayerPacket implements Packet {


private static final long serialVersionUID = 1L;
private final String content;

    public UpdatePlayerPacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}