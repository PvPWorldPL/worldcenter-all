package api.redis.packet.guild;
import api.redis.Packet;

public class UpdateGuildInformationPacket implements Packet {

private static final long serialVersionUID = 1L;
private final String content;

    public UpdateGuildInformationPacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}