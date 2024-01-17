package api.redis.packet.chat;

import api.redis.Packet;

public class ChatRankingPacket implements Packet {


	private static final long serialVersionUID = 1L;
	private final int amount;

    public ChatRankingPacket(int amount) {
 
        this.amount = amount;
    }

 
    public int getAmount() {
        return amount;
    }

}
