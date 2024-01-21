package api.packet;
import api.redis.Packet;

public class ChatDisablePacket implements Packet {

   private final String content;

    public ChatDisablePacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}