package api.redis.packet.player;
import api.redis.BroadcastType;
import api.redis.Packet;

public class ServerRefreshTopsRanking implements Packet {

	
   
	private static final long serialVersionUID = 1L;
	private final BroadcastType type;
	
	
	public ServerRefreshTopsRanking(BroadcastType type) {
	    this.type = type;
   
    }
    

public BroadcastType getType() {
    return type;
}
}