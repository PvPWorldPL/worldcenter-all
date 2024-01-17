package api.redis.packet.chat;
import api.redis.Packet;

public class ChatPacket implements Packet {
  private static final long serialVersionUID = 1L;
   private String content;


	public  ChatPacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}