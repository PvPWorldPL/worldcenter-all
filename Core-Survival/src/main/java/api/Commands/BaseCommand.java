package api.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.textr.survival.Main;
import pl.textr.survival.utils.ChatUtil;

import java.util.Collections;
import java.util.List;




@CommandInfo(name = "", description = "", usage = "", permission = "")
public abstract class BaseCommand extends org.bukkit.command.Command {

    CommandInfo annotation = getClass().getAnnotation(CommandInfo.class);

    @Override
    public String toString() {
        return "BaseCommand{" +
                "annotation=" + annotation +
                ", description='" + description + '\'' +
                ", usageMessage='" + usageMessage + '\'' +
                ", timings=" + timings +
                '}';
    }

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
            String msg = Main.getPlugin().getConfiguration().errordonthavepermission().replace("{PERM}", annotation.permission());
            if (Main.getPlugin().getConfiguration().debug()) {
                Bukkit.getLogger().info("Brak uprawnień do Komendy: " + annotation.name());
                Bukkit.getLogger().info("Potrzebne uprawnienie: " + annotation.permission());
            }
            return ChatUtil.sendMessage(player, ChatUtil.fixColor(msg));
        }
        if (Main.getPlugin().getConfiguration().debug()) {
            Bukkit.getLogger().info("Komenda: " + annotation.permission());
            Bukkit.getLogger().info("Opis: " + annotation.description());
            Bukkit.getLogger().info("Użycie: " + List.of(annotation.aliases()));
            Bukkit.getLogger().info("Uprawnienie: " + annotation.permission());
        }
        return this.onExecute(player, args);
    }

    public abstract boolean onExecute(CommandSender sender, String[] args);
}