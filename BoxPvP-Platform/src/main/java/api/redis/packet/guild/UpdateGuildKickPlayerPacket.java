package api.redis.packet.guild;


import api.redis.Packet;

public class UpdateGuildKickPlayerPacket implements Packet {
   
	private static final long serialVersionUID = 1L;
	private final String guildTag;
    private final String playerName;

    public UpdateGuildKickPlayerPacket(String guildTag, String playerName) {
        this.guildTag = guildTag;
        this.playerName = playerName;
    }

    public String getGuildTag() {
        return guildTag;
    }

    public String getPlayerName() {
        return playerName;
    }
}