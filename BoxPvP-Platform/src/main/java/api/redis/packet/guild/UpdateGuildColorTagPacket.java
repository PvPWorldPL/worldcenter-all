package api.redis.packet.guild;


import api.redis.Packet;

public class UpdateGuildColorTagPacket implements Packet {

	private static final long serialVersionUID = 1L;
	private final String guildTag;
    private final String color;

    public UpdateGuildColorTagPacket(String guildTag, String color) {
        this.guildTag = guildTag;
        this.color = color;
    }

    public String getGuildTag() {
        return guildTag;
    }

    public String getColor() {
        return color;
    }
}