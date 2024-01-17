package api.redis.packet.broadcast;

import api.redis.Packet;

public class BroadcastDeathMessagePacket implements Packet {
    private static final long serialVersionUID = 1L;



   
	private final String p;
    private final String killer;
    private final int plus;
    private final int minus;
    

    public BroadcastDeathMessagePacket(String p, String killer,int plus, int minus) {
  
        this.p = p;
        this.killer = killer;
        this.plus = plus;
        this.minus = minus;
    }

  



	public String getVictim() {
        return p;
    }
	public String getKiller() {
        return killer;
    }
	public int getplus() {
        return plus;
    }
	public int getminus() {
        return minus;
    }
}
