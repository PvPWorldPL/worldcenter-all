package pl.worldcenter;


import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.worldcenter.commands.api.BaseCommand;
import pl.worldcenter.commands.api.CommandRegistry;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main extends JavaPlugin {

    //test

    private static Main plugin;

    public static Main getPlugin() {
        return Main.plugin;
    }

    @Override
    public void onEnable() {
        Main.plugin = this;
        getServer().getMessenger().registerOutgoingPluginChannel(Main.plugin, "BungeeCord");
        loadCommands();
        new PlaceHolderRegisterAPI().register();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
        }
    }

    public void loadCommands() {
        int totalCommands = 0;

        try {
            ClassPath classPath = ClassPath.from(getClass().getClassLoader());

            ImmutableSet<ClassPath.ClassInfo> classInfos = ImmutableSet.<ClassPath.ClassInfo>builder()
                    .addAll(classPath.getTopLevelClasses("pl.worldcenter.commands"))
                    .build();

            for (ClassPath.ClassInfo classInfo : classInfos) {
                Class<?> commandClass = classInfo.load();
                if (BaseCommand.class.isAssignableFrom(commandClass) && !commandClass.isInterface()) {
                    try {
                        BaseCommand baseCommand = (BaseCommand) commandClass.getDeclaredConstructor().newInstance();
                        CommandRegistry.registerCommand(baseCommand);
                        totalCommands++;

                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException ignored) {
                    }
                }
            }
        } catch (IOException ignored) {}
        this.getLogger().info("Załadowano " + totalCommands + " komend");
    }
}