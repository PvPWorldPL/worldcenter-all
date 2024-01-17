package api.redis.packet.guild;


import api.redis.Packet;

public class UpdateGuildInvitePacket implements Packet {
  
	private static final long serialVersionUID = 1L;
	private final String guildTag;
    private final String playerName;
    private final String PlayerNameinvite;

    public UpdateGuildInvitePacket(String guildTag, String playerName, String PlayerNameinvite) {
        this.guildTag = guildTag;
        this.playerName = playerName;
        this.PlayerNameinvite = PlayerNameinvite;
    }

    public String getGuildTag() {
        return guildTag;
    }

    public String getPlayerName() {
        return playerName;
    }

	  public String getPlayerNameinvite() {
        return PlayerNameinvite;
    }
}