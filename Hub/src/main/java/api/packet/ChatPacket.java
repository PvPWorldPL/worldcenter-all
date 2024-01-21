package api.packet;
import api.redis.Packet;

public class ChatPacket implements Packet {

   private final String content;

    public ChatPacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}