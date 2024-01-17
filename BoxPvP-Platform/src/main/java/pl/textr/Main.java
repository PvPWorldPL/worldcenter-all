package pl.textr;

import api.configurations.PluginConfiguration;
import api.redis.RedisDatabase;
import api.redis.cache.broadcast.*;
import api.redis.cache.chat.*;
import api.redis.cache.guild.*;
import api.redis.cache.player.UpdatePlayerPacketCache;
import api.redis.cache.server.ServerRefreshTopsRankingCache;
import api.redis.cache.server.ServerRestartPacketCache;
import api.redis.packet.broadcast.*;
import api.redis.packet.chat.*;
import api.redis.packet.guild.*;
import api.redis.packet.player.ServerRefreshTopsRanking;
import api.redis.packet.player.UpdatePlayerPacket;
import api.redis.packet.server.ServerRestartPacket;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

public class Main extends JavaPlugin {

    public final RedisDatabase redisDatabase = new RedisDatabase();
    private static Main plugin;
    private HikariDataSource hikari;
    private PluginConfiguration Pluginconfiguration;
    public RedisDatabase getRedisService() {
        return redisDatabase;
    }

    public static Main getPlugin() {
        return plugin;
    }
    public HikariDataSource getHikari() {
        return hikari;
    }
    public PluginConfiguration getConfiguration() {
        return this.Pluginconfiguration;
    }


    private void connectDatabase() {
        hikari = new HikariDataSource();
        hikari.addDataSourceProperty("cachePrepStmts", true);
        hikari.addDataSourceProperty("prepStmtCacheSize", 250);
        hikari.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        hikari.addDataSourceProperty("useServerPrepStmts", true);
        hikari.addDataSourceProperty("rewriteBatchedStatements", true);
        hikari.setMaxLifetime(1800000); // Wartość w milisekundach (np. 30 minut)

        hikari.addDataSourceProperty("useSSL", false);
        hikari.addDataSourceProperty("requireSSL", false);
        hikari.addDataSourceProperty("characterEncoding", "utf8");
        hikari.addDataSourceProperty("encoding", "UTF-8");
        hikari.addDataSourceProperty("useUnicode", true);
        hikari.setDriverClassName("org.postgresql.Driver");
        hikari.setJdbcUrl("jdbc:postgresql://" + Main.getPlugin().getConfiguration().hostpostgresql + ":" + Main.getPlugin().getConfiguration().portpostgresql + "/" + Main.getPlugin().getConfiguration().databasepostgresql);
        hikari.setUsername(Main.getPlugin().getConfiguration().userpostgresql);
        hikari.setPassword(Main.getPlugin().getConfiguration().passwordpostgresql);
        hikari.setMaximumPoolSize(20);
        hikari.setConnectionTimeout(Duration.ofSeconds(30L).toMillis());
    }

    private void registerDatabase() {
        try (Connection connection = hikari.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(32) NOT NULL, " +


                    "money INTEGER NOT NULL, " +
                    "balance NUMERIC(32, 2) NOT NULL, " +
                    "points INTEGER NOT NULL, " +
                    "kills INTEGER NOT NULL, " +
                    "deaths INTEGER NOT NULL, " +
                    "lastKill VARCHAR(32) NOT NULL, " +
                    "lastKillTime BIGINT NOT NULL, " +
                    "teczowy INTEGER NOT NULL, " +
                    "god INTEGER NOT NULL, " +
                    "PrzerabianieKasy INTEGER NOT NULL, " +
                    "PrzerabianieMonet INTEGER NOT NULL, " +
                    "PrzerabianieBlokow INTEGER NOT NULL, " +
                    "perkZycia INTEGER NOT NULL, " +
                    "perkSzybkosci INTEGER NOT NULL, " +
                    "perkSily INTEGER NOT NULL, " +
                    "perkWampiryzmu INTEGER NOT NULL, " +
                    "perkSzybkosciAtaku INTEGER NOT NULL, " +
                    "vanish INTEGER NOT NULL)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS clans " +
                    "(id SERIAL PRIMARY KEY, " +
                    "tag VARCHAR(5) NOT NULL, " +
                    "name VARCHAR(32) NOT NULL, " +
                    "owner VARCHAR(64) NOT NULL, " +
                    "pvp INTEGER NOT NULL, " +
                    "createtime BIGINT NOT NULL, " +
                    "colortag VARCHAR(25) NOT NULL, " +
                    "points INTEGER NOT NULL)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS discord_users " +
                    "(id SERIAL PRIMARY KEY, " +
                    "nick VARCHAR(32) NOT NULL, " +
                    "discord_id VARCHAR(64) NOT NULL)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS members " +
                    "(id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(32) NOT NULL, " +
                    "tag VARCHAR(5) NOT NULL)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS player_data " +
                    "(id SERIAL PRIMARY KEY, " +
                    "nick VARCHAR(32) NOT NULL, " +
                    "world VARCHAR(32) NOT NULL, " +
                    "x INTEGER NOT NULL, " +
                    "y INTEGER NOT NULL, " +
                    "z BIGINT NOT NULL, " +
                    "yaw BIGINT NOT NULL, " +
                    "pitch INTEGER NOT NULL, " +
                    "hp INTEGER NOT NULL, " +
                    "inventory TEXT NOT NULL, " +
                    "armor TEXT NOT NULL, " +
                    "enderchest TEXT NOT NULL, " +
                    "potions TEXT NOT NULL, " +
                    "level INTEGER NOT NULL, " +
                    "exp BIGINT NOT NULL, " +
                    "antylogout BIGINT NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public void redisInit() {
        Bukkit.getLogger().info("zaladowano redis");
        this.redisDatabase.subscribe("UpdatePlayer", new UpdatePlayerPacketCache(), UpdatePlayerPacket.class);

        this.redisDatabase.subscribe("broadcast", new BroadcastPacketCache(), BroadcastPacket.class);
        this.redisDatabase.subscribe("broadcastItemShop", new BroadcastItemShopPacketCache(), BroadcastItemShopPacket.class);
        this.redisDatabase.subscribe("broadcastHelpop", new BroadcastHelpopPacketCache(), BroadcastHelpopPacket.class);
        this.redisDatabase.subscribe("BroadcastGuild", new BroadcastGuildMessagePacketCache(), BroadcastGuildMessagePacket.class);
        this.redisDatabase.subscribe("BroadcastCase", new BroadcastCasePacketCache(), BroadcastCasePacket.class);
        this.redisDatabase.subscribe("BroadcastMeteorRandom", new BroadcastMeteorRandomPacketCache(), BroadcastMeteorRandomPacket.class);
        this.redisDatabase.subscribe("BroadcastTurboDrop", new BroadcastTurboDropPacketCache(), BroadcastTurboDropPacket.class);
        this.redisDatabase.subscribe("BroadcastRangiDrop", new BroadcastRangiDropPacketCache(), BroadcastRangiDropPacket.class);
        this.redisDatabase.subscribe("BroadcastDeathMessage", new BroadcastDeathMessagePacketCache(), BroadcastDeathMessagePacket.class);
        this.redisDatabase.subscribe("chatGlobal", new ChatPacketCache(), ChatPacket.class);
        this.redisDatabase.subscribe("ChatEnable", new ChatEnablePacketCache(), ChatEnablePacket.class);
        this.redisDatabase.subscribe("ChatDisable", new ChatDisablePacketCache(), ChatDisablePacket.class);
        this.redisDatabase.subscribe("ChatClear", new ChatClearPacketCache(), ChatClearPacket.class);
        this.redisDatabase.subscribe("ChatVipEnable", new ChatVipEnablePacketCache(), ChatVipEnablePacket.class);
        this.redisDatabase.subscribe("ChatVipDisable", new ChatVipDisablePacketCache(), ChatVipDisablePacket.class);
        this.redisDatabase.subscribe("ChatSlow", new ChatSlowPacketCache(), ChatSlowPacket.class);
        this.redisDatabase.subscribe("ChatRanking", new ChatRankingPacketCache(), ChatRankingPacket.class);
        this.redisDatabase.subscribe("UpdateGuildInformation", new UpdateGuildInformationPacketCache(), UpdateGuildInformationPacket.class);
        this.redisDatabase.subscribe("UpdateGuildAddPlayer", new UpdateGuildAddPlayerPacketCache(), UpdateGuildAddPlayerPacket.class);
        this.redisDatabase.subscribe("UpdateGuildRemovePlayer", new UpdateGuildRemovePlayerPacketCache(), UpdateGuildRemovePlayerPacket.class);
        this.redisDatabase.subscribe("UpdateGuildKickPlayer", new UpdateGuildKickPlayerPacketCache(), UpdateGuildKickPlayerPacket.class);
        this.redisDatabase.subscribe("UpdateGuildDelete", new UpdateGuildDeletePacketCache(), UpdateGuildDeletePacket.class);
        this.redisDatabase.subscribe("UpdateGuildCreate", new UpdateGuildCreatePacketCache(), UpdateGuildCreatePacket.class);
        this.redisDatabase.subscribe("UpdateGuildOwner", new UpdateGuildOwnerPacketCache(), UpdateGuildOwnerPacket.class);
        this.redisDatabase.subscribe("UpdateGuildColorTag", new UpdateGuildColorTagPacketCache(), UpdateGuildColorTagPacket.class);
        this.redisDatabase.subscribe("ServerRefreshTops", new ServerRefreshTopsRankingCache(), ServerRefreshTopsRanking.class);
        this.redisDatabase.subscribe("ServerRestart", new ServerRestartPacketCache(), ServerRestartPacket.class);
    }
}