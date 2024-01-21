package pl.textr.core.commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.textr.core.LobbyPlugin;
import pl.textr.core.commands.Api.Command;
import pl.textr.core.utils.other.ChatUtil;

public class GamemodeCommand extends Command
{
    public GamemodeCommand() {
        super("gamemode", "Zmiana trybu gry graczy", "/gamemode [gracz] <tryb>", "core.cmd.admin", "gm", "gmode");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
            
        }
        if (this.getMode(args[0]) == null) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cTryb gamemode nie odnaleziono!");
        }
        if (args.length == 1) {
            final Player p = (Player)sender;
            p.setGameMode(this.getMode(args[0]));
            return ChatUtil.sendMessage(sender, "&7Twoj tryb gamemode zostal zmieniony na &f" + p.getGameMode().toString().toLowerCase());
        }
        if (args.length != 2) {
            return ChatUtil.sendMessage(sender, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
            
        }
        if (!sender.hasPermission("core.cmd.Headadmin")) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cNie masz uprawnien!");
        }
        final Player o = Bukkit.getPlayer(args[1]);
        if (o == null) {
            return ChatUtil.sendMessage(sender, "&8[&c&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
        }
        o.setGameMode(this.getMode(args[0]));
        ChatUtil.sendMessage(o, "&7Twoj tryb gamemode zostal zmieniony na &f" + o.getGameMode().toString().toLowerCase() + "&7 przez &f" + sender.getName() + "&7!");
        return ChatUtil.sendMessage(sender, "&7Zmieniles tryb gamemode graczowi &f" + o.getName() + " &7na &f" + o.getGameMode().toString().toLowerCase() + "&7!");
    }
    
    private GameMode getMode(final String args) {
        if (args.equalsIgnoreCase("1") || args.equalsIgnoreCase("creative") || args.equalsIgnoreCase("true")) {
            return GameMode.CREATIVE;
        }
        if (args.equalsIgnoreCase("0") || args.equalsIgnoreCase("survival") || args.equalsIgnoreCase("false")) {
            return GameMode.SURVIVAL;
        }
        if (args.equalsIgnoreCase("2") || args.equalsIgnoreCase("adventure")) {
            return GameMode.ADVENTURE;
        }
        if (args.equalsIgnoreCase("3") || args.equalsIgnoreCase("spectator")) {
            return GameMode.SPECTATOR;
        }
        return null;
    }
}
