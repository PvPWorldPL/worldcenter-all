package pl.worldcenter.commands.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.worldcenter.util.ChatUtil;

import java.util.List;

@CommandInfo(name = "", description = "", usage = "", permission = "")
public abstract class PlayerCommandExecutor extends BaseCommand {

    CommandInfo annotation = getClass().getAnnotation(CommandInfo.class);
	public PlayerCommandExecutor() {

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

	@Override
	public boolean onExecute(final CommandSender sender, final String[] args) {
	    if (!(sender instanceof Player)) {
	        return ChatUtil.sendMessage(sender, "&cTej komendy nie możesz użyć z poziomu konsoli!");
	    }
	    return this.onCommand((Player) sender, args);
	}


    public abstract boolean onCommand(Player player, String[] args);
}
