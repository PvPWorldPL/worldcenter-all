package api.redis.packet.broadcast;

import api.redis.BroadcastType;
import api.redis.Packet;

public class BroadcastCasePacket implements Packet {

	private static final long serialVersionUID = 1L;
	private final BroadcastType type;
    private final String skrzynka;
    private final int ilosc;
	public String nick;

    public BroadcastCasePacket(BroadcastType type,  String nick, String skrzynka, int ilosc) {
        this.type = type;
        this.nick = nick;
        this.skrzynka = skrzynka;
        this.ilosc = ilosc;
    }

    public BroadcastType getType() {
        return type;
    }

    public String getPlayer() {
        return nick;
    }
    
    public String getSkzynka() {
        return skrzynka;
    }
    public int getIlosc() {
        return ilosc;
    }
}
