package api.packet;
import api.redis.Packet;

public class ChatClearPacket implements Packet {

   private final String content;

    public ChatClearPacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}