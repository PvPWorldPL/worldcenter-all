package api.redis.packet.chat;
import api.redis.Packet;

public class ChatClearPacket implements Packet {

private static final long serialVersionUID = 1L;
private final String content;

    public ChatClearPacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}