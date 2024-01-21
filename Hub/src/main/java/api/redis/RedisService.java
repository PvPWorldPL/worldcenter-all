package api.redis;

import com.google.gson.Gson;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import pl.textr.core.LobbyPlugin;


public class RedisService {
	
    private final RedisClient redisClient;
    
    
    RedisURI redisUri = RedisURI.create("redis://localhost:6379/");
    private final Gson gson;

    public RedisService() {
        redisUri.setPassword("mtk11");
        this.redisClient = RedisClient.create(redisUri);
        redisClient.connect();
        this.gson = new Gson();
    }




    public <T extends Packet> void subscribe(String channel, PacketListener<T> packetListener, Class<T> type) {
        StatefulRedisPubSubConnection<String, String> pubSubConnection = redisClient.connectPubSub();
        RedisPubSubAsyncCommands<String, String> pubSubAsyncCommands = pubSubConnection.async();

        pubSubAsyncCommands.subscribe(channel)
            .thenAccept(subscribeResult -> {
                pubSubConnection.addListener(new RedisPubSubAdapter<>() {
                    @Override
                    public void message(String channel, String message) {
                    	T packet = gson.fromJson(message, type);

                        packetListener.handle(packet);
                    }
                });
            })
            .exceptionally(ex -> {
                System.out.println("Wystąpił błąd podczas subskrybowania kanału: " + ex.getMessage());
                pubSubConnection.close();
                return null;
            });
    }


    
    

    public void publishAsync(String channel, Packet packet) {
        String json = gson.toJson(packet);
        RedisAsyncCommands<String, String> asyncCommands = redisClient.connect().async();
        asyncCommands.publish(channel, json);
        if (LobbyPlugin.getPlugin().getConfiguration().debug) {
        System.out.println("Wysłano pakiet do kanału: " + channel + " " + packet.toString());
    }
    }

}
