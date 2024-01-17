package api.redis;

public interface PacketListener<T> {

    void handle(T packet);
}
