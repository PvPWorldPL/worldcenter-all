package api.packet;
import api.redis.Packet;

public class ChatEnablePacket implements Packet {

   private final String content;

    public ChatEnablePacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}