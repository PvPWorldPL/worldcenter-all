package pl.textr.core;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import api.cache.BroadcastItemShopPacketCache;
import api.cache.BroadcastPacketCache;
import api.cache.ChatClearPacketCache;
import api.cache.ChatDisablePacketCache;
import api.cache.ChatEnablePacketCache;
import api.cache.ChatPacketCache;
import api.cache.ChatVipDisablePacketCache;
import api.cache.ChatVipEnablePacketCache;
import api.configurations.PluginConfiguration;
import api.packet.BroadcastItemShopPacket;
import api.packet.BroadcastPacket;
import api.packet.ChatClearPacket;
import api.packet.ChatDisablePacket;
import api.packet.ChatEnablePacket;
import api.packet.ChatPacket;
import api.packet.ChatVipDisablePacket;
import api.packet.ChatVipEnablePacket;
import api.redis.RedisService;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.serdes.standard.StandardSerdes;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import pl.textr.core.commands.Api.Command;
import pl.textr.core.commands.Api.CommandManager;
import pl.textr.core.task.ActionBarTask;

import javax.swing.*;

public class LobbyPlugin extends JavaPlugin implements Listener {

    private static LobbyPlugin plugin;
    public WorldGuardPlugin wgPlugin;
    private PluginConfiguration configuration;
    public final RedisService redisService = new RedisService();
 
    public RedisService getRedisService() {
        return redisService;
    }

	public PluginConfiguration getConfiguration() {
	return this.configuration;
	    }
    

    public static LobbyPlugin getPlugin() {
        return LobbyPlugin.plugin;
    }

  
 //trzeba tego maina uporzadkowac xd
    public void onEnable() {
	    LobbyPlugin.plugin = this;
        setupConfiguration();
        redisInit();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        onRegion();
        loadListeners();
        loadCommands();
        registerTasks();
    }



    public void registerTasks() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new ActionBarTask(), 40L, 20L);
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.kickPlayer("§cSerwer jest aktualnie restartowany!");
        }
    }
  

    

    
    private void setupConfiguration() {
        try {
            this.configuration = ConfigManager.create(PluginConfiguration.class, it -> {
                it.withBindFile(new File(this.getDataFolder(), "configuration.yml"));
                it.withConfigurer(new YamlBukkitConfigurer(), new StandardSerdes());
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            this.getLogger().log(Level.SEVERE, "Caught exception while loading plugin's configuration.. ", exception);
            this.getPluginLoader().disablePlugin(this);
        }
    }
    
    
    
    public void loadCommands() {
        try {
            ClassPath classPath = ClassPath.from(getClass().getClassLoader());

            ImmutableSet<ClassPath.ClassInfo> classInfos = ImmutableSet.<ClassPath.ClassInfo>builder()
                    .addAll(classPath.getTopLevelClasses("pl.textr.core.commands.Admin"))
                    .addAll(classPath.getTopLevelClasses("pl.textr.core.commands.HeadAdmin"))
                    .addAll(classPath.getTopLevelClasses("pl.textr.core.commands.Helper"))
                    .addAll(classPath.getTopLevelClasses("pl.textr.core.commands.Moderator"))
                    .addAll(classPath.getTopLevelClasses("pl.textr.core.commands.User"))
                 .addAll(classPath.getTopLevelClasses("pl.textr.core.commands.Guild"))
                    
                    .build();

            for (ClassPath.ClassInfo classInfo : classInfos) {
                Class<?> commandClass = classInfo.load();
                if (Command.class.isAssignableFrom(commandClass) && !commandClass.isInterface()) {
                    try {
                        Command command = (Command) commandClass.getDeclaredConstructor().newInstance();
                        CommandManager.register(command);
                        if (this.getConfiguration().debug) {
                            this.getLogger().warning("Zarejestrowano komendę: " + commandClass.getName());
                        }
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
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
    }



    private WorldGuardPlugin getWGPlugin() {
        final Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin)plugin;
    }

	 public void onRegion() {
	        this.wgPlugin = this.getWGPlugin();
	        if (this.wgPlugin == null) {
	            this.getLogger().warning("Could not find World Guard, disabling.");
	            this.getServer().getPluginManager().disablePlugin(this);

            }
	  }

    @SuppressWarnings("deprecation")
    public void loadListeners() {
        try {
            final ClassPath cp = ClassPath.from(getClass().getClassLoader());
            PluginManager manager = Bukkit.getPluginManager();

            for (ClassPath.ClassInfo classInfo : cp.getTopLevelClassesRecursive("pl.textr.core.listeners.other")) {
                Class<?> listenerClass = classInfo.load();
                Listener listener;
                try {
                    listener = (Listener) listenerClass.newInstance();
                } catch (ReflectiveOperationException | ExceptionInInitializerError | SecurityException e) {
                    continue;
                }
                manager.registerEvents(listener, this);
                if (this.getConfiguration().debug) {
                this.getLogger().warning("Zarejestrowano " + classInfo);
            }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

	 public void redisInit() {
		    this.getLogger().warning("zaladowano redis");
		  this.redisService.subscribe("broadcastlobby", new BroadcastPacketCache(), BroadcastPacket.class);		  
		  this.redisService.subscribe("chatlobby", new ChatPacketCache(), ChatPacket.class);
		  this.redisService.subscribe("Chaton", new ChatEnablePacketCache(), ChatEnablePacket.class);
		  this.redisService.subscribe("Chatoff", new ChatDisablePacketCache(), ChatDisablePacket.class);
		  this.redisService.subscribe("clearchat", new ChatClearPacketCache(), ChatClearPacket.class);
		  this.redisService.subscribe("ChatonVip", new ChatVipEnablePacketCache(), ChatVipEnablePacket.class);
		  this.redisService.subscribe("ChatoffVip", new ChatVipDisablePacketCache(), ChatVipDisablePacket.class);
		  this.redisService.subscribe("broadcastItemShop", new BroadcastItemShopPacketCache(), BroadcastItemShopPacket.class);
		      
			 
	 }

}
   
    
   
    
 
