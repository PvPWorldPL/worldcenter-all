package pl.textr;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.serdes.standard.StandardSerdes;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.textr.commands.ManageCommand;
import pl.textr.configurations.PluginConfiguration;
import pl.textr.listeners.BlockActionListener;

import java.io.File;
import java.util.logging.Level;

public class Main extends JavaPlugin {

    private static Main plugin;
    private PluginConfiguration pluginConfiguration;

    public static Main getPlugin() {
        return Main.plugin;
    }

    public PluginConfiguration getConfiguration() {
        return this.pluginConfiguration;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("pomyślnie włączono plugin");
        // Inicjalizacja pluginu
        Main.plugin = this;

        // Ładowanie konfiguracji
        setupConfiguration();

        // Ładowanie Listenera
        getServer().getPluginManager().registerEvents(new BlockActionListener(), this);
        // Ładowanie komendy
        getCommand("manage").setExecutor(new ManageCommand());


    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("pomyślnie wyłączono plugin");
    }

    private void setupConfiguration() {
        try {
            this.pluginConfiguration = ConfigManager.create(PluginConfiguration.class, it -> {
                it.withBindFile(new File(this.getDataFolder(), "configuration.yml"));
                it.withConfigurer(new YamlBukkitConfigurer(), new StandardSerdes());
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            this.getLogger().log(Level.SEVERE, "Wystąpił błąd podczas ładowania konfiguracji pluginu.", exception);
            this.getPluginLoader().disablePlugin(this);
        }
    }
}
