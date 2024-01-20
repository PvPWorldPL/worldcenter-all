package pl.textr.connect;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.serdes.standard.StandardSerdes;
import eu.okaeri.configs.yaml.bungee.YamlBungeeConfigurer;
import net.md_5.bungee.api.plugin.Plugin;
import pl.textr.connect.commands.*;
import pl.textr.connect.lang.PluginConfiguration;
import pl.textr.connect.listeners.PreLoginListener;
import pl.textr.connect.listeners.ProxyPingListener;
import pl.textr.connect.listeners.TabCompleteListener;
import pl.textr.connect.utils.MessageManager;

import java.io.File;
import java.util.logging.Level;

public class AuthPlugin extends Plugin {
    private static AuthPlugin plugin;
    private PluginConfiguration configuration;
    private MessageManager messageManager;

	public PluginConfiguration getConfiguration() {
	return this.configuration;
	    }
    
    
    public void onEnable() {
    	 AuthPlugin.plugin = this;
    	  messageManager = new MessageManager();

        setupConfiguration();
    
        getProxy().getPluginManager().registerListener(this, new PreLoginListener());
        getProxy().getPluginManager().registerListener(this, new ProxyPingListener());
        getProxy().getPluginManager().registerListener(this, new TabCompleteListener());
        getProxy().getPluginManager().registerCommand(this, new WhiteListCommand());
        getProxy().getPluginManager().registerCommand(this, new SlotCommand());
        getProxy().getPluginManager().registerCommand(this, new ConfigCommand());
        getProxy().getPluginManager().registerCommand(this, new AdminCommand());
        getProxy().getPluginManager().registerCommand(this, new MsgCommand(messageManager));
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand(messageManager));

   
        
    }

    public void onDisable() {
        plugin = null;
    }


    
    private void setupConfiguration() {
        try {
            this.configuration = ConfigManager.create(PluginConfiguration.class, it -> {
                it.withBindFile(new File(this.getDataFolder(), "configuration.yml"));
                it.withConfigurer(new YamlBungeeConfigurer(), new StandardSerdes());
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            this.getLogger().log(Level.SEVERE, "Caught exception while loading plugin's configuration.. ", exception);
         
        }
    }

    
    public static AuthPlugin getPlugin() {
        return plugin;
    }
}
 