package pl.worldcenter.commands.api;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
import pl.worldcenter.util.Reflection;

import java.util.HashMap;

public class CommandRegistry {
    public static final HashMap<String, BaseCommand> registeredCommands;
    private static final Reflection.FieldAccessor<SimpleCommandMap> commandMapField;
    private static CommandMap serverCommandMap;

    static {
        registeredCommands = new HashMap<>();
        commandMapField = Reflection.getField(SimplePluginManager.class, "commandMap", SimpleCommandMap.class);
        serverCommandMap = commandMapField.get(Bukkit.getServer().getPluginManager());
    }

    public static void registerCommand(BaseCommand baseCommand) {
        if (serverCommandMap == null) {
            serverCommandMap = commandMapField.get(Bukkit.getServer().getPluginManager());
        }
        serverCommandMap.register(baseCommand.getName(), baseCommand);
        registeredCommands.put(baseCommand.getName(), baseCommand);
    }
}
