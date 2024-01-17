	package api.redis;
	
	import java.nio.CharBuffer;

import org.bukkit.Bukkit;

import com.google.gson.Gson;

import api.redis.cache.broadcast.BroadcastCasePacketCache;
import api.redis.cache.broadcast.BroadcastDeathMessagePacketCache;
import api.redis.cache.broadcast.BroadcastGuildMessagePacketCache;
import api.redis.cache.broadcast.BroadcastHelpopPacketCache;
import api.redis.cache.broadcast.BroadcastItemShopPacketCache;
import api.redis.cache.broadcast.BroadcastMeteorRandomPacketCache;
import api.redis.cache.broadcast.BroadcastPacketCache;
import api.redis.cache.broadcast.BroadcastRangiDropPacketCache;
import api.redis.cache.broadcast.BroadcastTurboDropPacketCache;
import api.redis.cache.chat.ChatClearPacketCache;
import api.redis.cache.chat.ChatDisablePacketCache;
import api.redis.cache.chat.ChatEnablePacketCache;
import api.redis.cache.chat.ChatPacketCache;
import api.redis.cache.chat.ChatRankingPacketCache;
import api.redis.cache.chat.ChatSlowPacketCache;
import api.redis.cache.chat.ChatVipDisablePacketCache;
import api.redis.cache.chat.ChatVipEnablePacketCache;
import api.redis.cache.guild.UpdateGuildAddPlayerPacketCache;
import api.redis.cache.guild.UpdateGuildColorTagPacketCache;
import api.redis.cache.guild.UpdateGuildCreatePacketCache;
import api.redis.cache.guild.UpdateGuildDeletePacketCache;
import api.redis.cache.guild.UpdateGuildInformationPacketCache;
import api.redis.cache.guild.UpdateGuildKickPlayerPacketCache;
import api.redis.cache.guild.UpdateGuildOwnerPacketCache;
import api.redis.cache.guild.UpdateGuildRemovePlayerPacketCache;
import api.redis.cache.player.UpdatePlayerPacketCache;
import api.redis.cache.server.ServerRefreshTopsRankingCache;
import api.redis.cache.server.ServerRestartPacketCache;
import api.redis.packet.broadcast.BroadcastCasePacket;
import api.redis.packet.broadcast.BroadcastDeathMessagePacket;
import api.redis.packet.broadcast.BroadcastGuildMessagePacket;
import api.redis.packet.broadcast.BroadcastHelpopPacket;
import api.redis.packet.broadcast.BroadcastItemShopPacket;
import api.redis.packet.broadcast.BroadcastMeteorRandomPacket;
import api.redis.packet.broadcast.BroadcastPacket;
import api.redis.packet.broadcast.BroadcastRangiDropPacket;
import api.redis.packet.broadcast.BroadcastTurboDropPacket;
import api.redis.packet.chat.ChatClearPacket;
import api.redis.packet.chat.ChatDisablePacket;
import api.redis.packet.chat.ChatEnablePacket;
import api.redis.packet.chat.ChatPacket;
import api.redis.packet.chat.ChatRankingPacket;
import api.redis.packet.chat.ChatSlowPacket;
import api.redis.packet.chat.ChatVipDisablePacket;
import api.redis.packet.chat.ChatVipEnablePacket;
import api.redis.packet.guild.UpdateGuildAddPlayerPacket;
import api.redis.packet.guild.UpdateGuildColorTagPacket;
import api.redis.packet.guild.UpdateGuildCreatePacket;
import api.redis.packet.guild.UpdateGuildDeletePacket;
import api.redis.packet.guild.UpdateGuildInformationPacket;
import api.redis.packet.guild.UpdateGuildKickPlayerPacket;
import api.redis.packet.guild.UpdateGuildOwnerPacket;
import api.redis.packet.guild.UpdateGuildRemovePlayerPacket;
import api.redis.packet.player.ServerRefreshTopsRanking;
import api.redis.packet.player.UpdatePlayerPacket;
import api.redis.packet.server.ServerRestartPacket;
import io.lettuce.core.RedisClient;
	import io.lettuce.core.RedisURI;
	import io.lettuce.core.api.async.RedisAsyncCommands;
	import io.lettuce.core.pubsub.RedisPubSubAdapter;
	import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
	import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
	import pl.textr.boxpvp.Main;
	
	
	public class RedisDatabase {
		
	    private final RedisClient redisClient;
	    private final Gson gson;
	    
	    
	    
	    
	    
	    
	    public RedisDatabase() {
	
			RedisURI redisUri = RedisURI.builder()
	                .withHost("127.0.0.1")
	                .withPort(6379)
	                .withPassword(CharBuffer.wrap("mtk11"))
	                .build();
	
	        this.redisClient = RedisClient.create(redisUri);
	        this.gson = new Gson();
	
	    }
	
	
	
	    public <T extends Packet> void subscribe(String channel, PacketListener<T> packetListener, Class<T> type) {
	        StatefulRedisPubSubConnection<String, String> pubSubConnection = redisClient.connectPubSub();
	        RedisPubSubAsyncCommands<String, String> pubSubAsyncCommands = pubSubConnection.async();
	
	        pubSubAsyncCommands.subscribe(channel)
	            .thenAccept(subscribeResult -> pubSubConnection.addListener(new RedisPubSubAdapter<>() {
	                @Override
	                public void message(String channel, String message) {
	                    T packet = gson.fromJson(message, type);
	
	                    packetListener.handle(packet);
	                }
	            }))
	            .exceptionally(ex -> {
	            	  Bukkit.getLogger().warning("Wystąpił błąd podczas subskrybowania kanału: " + ex.getMessage());
	                pubSubConnection.close();
	                return null;
	            });
	    }
	
	
	    
	    
	
	    public void publishAsync(String channel, Packet packet) {
	        String json = gson.toJson(packet);
	        RedisAsyncCommands<String, String> asyncCommands = redisClient.connect().async();
	        asyncCommands.publish(channel, json);
	        if (Main.getPlugin().getConfiguration().debug) {
	        	  Bukkit.getLogger().info("Wysłano pakiet do kanału: " + channel);
	        }
	    }
	}
