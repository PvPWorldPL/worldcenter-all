package api.redis.packet.broadcast;

import api.redis.Packet;

public class BroadcastHelpopPacket implements Packet {
    

	private static final long serialVersionUID = 1L;
	private final String playerName;
    private final String ChannelName;

    private final String message;

    
    public BroadcastHelpopPacket(String playerName, String ChannelName, String message) {
        this.playerName = playerName;
        this.ChannelName = ChannelName;       
    	this.message = message;
    }

    public String getPlayerName() {
        return playerName;
    }
    
    public String getChannelName() {
        return ChannelName;
    }
    
    public String getMessage() {
        return message;
    }
    

	
}
