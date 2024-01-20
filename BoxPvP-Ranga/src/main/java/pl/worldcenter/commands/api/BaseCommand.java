package pl.worldcenter.commands.api;

import org.bukkit.command.CommandSender;
import pl.worldcenter.util.ChatUtil;


import java.util.Collections;
import java.util.List;




@CommandInfo(name = "", description = "", usage = "", permission = "")
public abstract class BaseCommand extends org.bukkit.command.Command {

    CommandInfo annotation = getClass().getAnnotation(CommandInfo.class);

    public BaseCommand() {
        super("", "", "", Collections.emptyList());
        if (annotation != null) {
            setName(annotation.name());
            setDescription(annotation.description());
            setUsage(annotation.usage());
            setPermission(annotation.permission());
            setAliases(List.of(annotation.aliases()));
        } else {
            throw new IllegalArgumentException("CommandInfo annotation is missing.");
        }
    }

    public boolean execute(final CommandSender player, final String label, final String[] args) {

        if (annotation != null && !annotation.permission().isEmpty() && !player.hasPermission(annotation.permission())) {
            String msg = "&cBrak permisji do " + annotation.permission();
            return ChatUtil.sendMessage(player, ChatUtil.fixColor(msg));
        }
        return this.onExecute(player, args);
    }

    public abstract boolean onExecute(CommandSender sender, String[] args);
}