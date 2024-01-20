package pl.textr.boxpvp;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.zaxxer.hikari.HikariDataSource;

import api.Commands.BaseCommand;
import api.Commands.CommandRegistry;
import api.configurations.DiscordConfiguration;
import api.configurations.PluginConfiguration;
import api.data.VaultEconomyProvider;
import api.discord.main.DiscordBot;
import api.managers.ClanManager;
import api.managers.KillContractManager;
import api.managers.UserAccountManager;
import api.placeholders.PlaceHolderRegisterAPI;
import api.redis.RedisDatabase;
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
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.serdes.standard.StandardSerdes;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.economy.Economy;
import pl.textr.boxpvp.tasks.*;
import pl.textr.boxpvp.utils.ChatUtil;

public class Main extends JavaPlugin {

    //test
    private static Main plugin;
    public WorldGuardPlugin wgPlugin;
    public final RedisDatabase redisDatabase = new RedisDatabase();
    private PluginConfiguration Pluginconfiguration;
    public DiscordConfiguration DiscordConfiguration;	
   private HikariDataSource hikari;
    public static Economy econ = null;
    private KillContractManager killContractManager;

  
    
    public static Main getPlugin() {
        return Main.plugin;
    }


    public PluginConfiguration getConfiguration() {
        return this.Pluginconfiguration;
    }
    
    public DiscordConfiguration getDiscordConfiguration() {
        return this.DiscordConfiguration;
    }
    
 
    public HikariDataSource getHikari() {
 
    	return hikari;
    }

    

    public RedisDatabase getRedisService() {
        return redisDatabase;
    }

    public KillContractManager getKillContractManager() {
        return killContractManager;
    }

    @Override
    public void onEnable() {
        // Inicjalizacja pluginu
        Main.plugin = this;
        Main.plugin.getServer().getMessenger().registerOutgoingPluginChannel(Main.plugin, "BungeeCord");
 
        // Inicjalizacja zarządzania kontraktami i WorldGuard
        this.killContractManager = new KillContractManager();
        this.wgPlugin = this.getWGPlugin();

        // Konfiguracja i połączenie z bazą danych
        setupConfiguration();
        connectDatabase();
        registerDatabase();
        redisInit();

           this.registerDiscordBot();
       
        // Wczytanie danych użytkowników i gildii
        UserAccountManager.loadUsers();
        ClanManager.loadGuilds();

        // Rejestracja listenerów i komend
        loadListeners();
        loadCommands();

        // Rejestracja zadań
        registerTasks();

        // Rejestracja PlaceholderAPI
        new PlaceHolderRegisterAPI().register();

        // Tworzenie światów
        Bukkit.createWorld(new WorldCreator("pvp").type(WorldType.FLAT));
        Bukkit.createWorld(new WorldCreator("kopalnia").type(WorldType.FLAT));
        Bukkit.createWorld(new WorldCreator("kopalniapremium").type(WorldType.FLAT));

        // Sprawdzenie WorldGuard i Vault
        if (wgPlugin == null) {
            getLogger().warning("WorldGuard nie jest zainstalowany na serwerze.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (!setupEconomy()) {
        	 getLogger().warning("Vault nie jest zainstalowany na serwerze.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        
    
        // Sprawdzenie dostawcy LuckPerms
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            provider.getProvider();
        }
    }

    
 

    @Override
    public void onDisable() {
        if (hikari != null) {
            hikari.close();
        }      
        DiscordBot.getBot().shutdown();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(ChatUtil.fixColor("&cSerwer jest aktualnie restartowany!"));
        }
    }

    public void redisInit() {
        this.getLogger().warning("zaladowano redis");
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


    public void registerDiscordBot() {
        if (!Main.getPlugin().getConfiguration().RewardsDiscord()) {
            getLogger().warning("Funkcja discord bot jest wyłączona!");
        } else {
            new DiscordBot("MTE1OTYwNTA3MTU2NzQwOTE1NQ.G04WwZ.nzxyb1eSkKJ8gUBrVhVIx1A86NNWgIu3YMJTTM");
            getLogger().info("Discord bot is registered");
        }
    }


    public void registerTasks() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new ActionbarTask(), 40L, 20L);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new ItemClearTask(), 10 * 60 * 20L, 10 * 60 * 20L); //10m
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new TabCategoryTask(), 0L, 6000L);
       Bukkit.getServer().getScheduler().runTaskTimer(this, new TopPlayersNpc(), 0L, 30L * 60 * 20L);
        Bukkit.getScheduler().runTaskLaterAsynchronously(this, new MeteorTask(), 5 * 20L);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new BossBarTask(), 40L, 20L);
        Bukkit.getScheduler().runTaskTimer(this, new PlayerTimeRunnable(), 20L * 60 * 5, 20L * 60 * 5);
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
        hikari.setJdbcUrl("jdbc:postgresql://" + this.getConfiguration().hostpostgresql + ":" + this.getConfiguration().portpostgresql + "/" + this.getConfiguration().databasepostgresql);
        hikari.setUsername(this.getConfiguration().userpostgresql);
        hikari.setPassword(this.getConfiguration().passwordpostgresql);
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


    public void loadCommands() {
        int totalCommands = 0;

        try {
            ClassPath classPath = ClassPath.from(getClass().getClassLoader());

            ImmutableSet<ClassPath.ClassInfo> classInfos = ImmutableSet.<ClassPath.ClassInfo>builder()
                    .addAll(classPath.getTopLevelClasses("pl.textr.boxpvp.commands.Admin"))
                    .addAll(classPath.getTopLevelClasses("pl.textr.boxpvp.commands.HeadAdmin"))
                    .addAll(classPath.getTopLevelClasses("pl.textr.boxpvp.commands.Helper"))
                    .addAll(classPath.getTopLevelClasses("pl.textr.boxpvp.commands.Moderator"))
                    .addAll(classPath.getTopLevelClasses("pl.textr.boxpvp.commands.User"))
                    .addAll(classPath.getTopLevelClasses("pl.textr.boxpvp.commands.Guild"))

                    .build();

            for (ClassPath.ClassInfo classInfo : classInfos) {
                Class<?> commandClass = classInfo.load();
                if (BaseCommand.class.isAssignableFrom(commandClass) && !commandClass.isInterface()) {
                    try {
                        BaseCommand baseCommand = (BaseCommand) commandClass.getDeclaredConstructor().newInstance();
                        CommandRegistry.registerCommand(baseCommand);
                        totalCommands++;
               
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException e) {
                        if (this.getConfiguration().debug) {
                            this.getLogger().warning("Wystąpił błąd podczas rejestrowania komendy: " + commandClass.getName());
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            if (this.getConfiguration().debug) {
                this.getLogger().warning("Wystąpił błąd podczas wczytywania klas komend: " + e);
                e.printStackTrace();
            }
        }
        this.getLogger().info("Załadowano " + totalCommands + " komend");
    }

    public void loadListeners() {
        int totalListener = 0;

        try {
            final ClassPath cp = ClassPath.from(getClass().getClassLoader());
            ImmutableSet<ClassPath.ClassInfo> classInfos = ImmutableSet.<ClassPath.ClassInfo>builder()

                    .addAll(cp.getTopLevelClassesRecursive("pl.textr.boxpvp.listeners.player"))
                    .addAll(cp.getTopLevelClassesRecursive("pl.textr.boxpvp.listeners.inventory"))
                    .addAll(cp.getTopLevelClassesRecursive("pl.textr.boxpvp.listeners.chat"))
                    .addAll(cp.getTopLevelClassesRecursive("pl.textr.boxpvp.listeners.other"))
                    .addAll(cp.getTopLevelClassesRecursive("pl.textr.boxpvp.listeners.items"))

                    .build();

            for (ClassPath.ClassInfo classInfo : classInfos) {
                Class<?> listenerClass = classInfo.load();
                Listener listener;
                try {
                    listener = (Listener) listenerClass.getDeclaredConstructor().newInstance();
                } catch (ReflectiveOperationException | ExceptionInInitializerError | SecurityException ignored) {
                    listener = new Listener() {
                    };
                }
                Bukkit.getServer().getPluginManager().registerEvents(listener, this);
                totalListener++;
                
                
            }
        } catch (IOException ex) {
            if (this.getConfiguration().debug) {
                this.getLogger().warning("Wystąpił błąd: " + ex);
                ex.printStackTrace();
            }
        }
        this.getLogger().info("Załadowano " + totalListener + " listeners");
    }


    private void setupConfiguration() {
        try {
            this.Pluginconfiguration = ConfigManager.create(PluginConfiguration.class, it -> {
        
                it.withBindFile(new File(this.getDataFolder(), "configuration.yml"));
                it.withConfigurer(new YamlBukkitConfigurer(), new StandardSerdes());
                it.saveDefaults();
                it.load(true);
            });              
    
            // Konfiguracja dla pliku "discord.yml"
            this.DiscordConfiguration = ConfigManager.create(DiscordConfiguration.class, it -> {
                File configFile = new File("/home/1.16.5/Rewards-BoxPvP/", "discord.yml"); // Ścieżka do docelowego folderu i pliku
                it.withBindFile(configFile);
                it.withConfigurer(new YamlBukkitConfigurer(), new StandardSerdes());
                it.saveDefaults();
                it.load(true);
            });

            
        } catch (Exception exception) {
            this.getLogger().log(Level.SEVERE, "Caught exception while loading plugin's configuration.. ", exception);
            this.getPluginLoader().disablePlugin(this);
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        getServer().getServicesManager().register(Economy.class, new VaultEconomyProvider(), this, ServicePriority.Normal);
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private WorldGuardPlugin getWGPlugin() {
        final Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin instanceof WorldGuardPlugin) {
            return (WorldGuardPlugin) plugin;
        }
        return null;
    }
}
