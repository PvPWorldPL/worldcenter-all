package api.packet;
import api.redis.Packet;

public class ChatVipDisablePacket implements Packet {

   private final String content;

    public ChatVipDisablePacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}