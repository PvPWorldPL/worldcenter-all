package pl.textr.core.commands.Admin;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.textr.core.LobbyPlugin;
import pl.textr.core.commands.Api.PlayerCommand;
import pl.textr.core.utils.other.ChatUtil;

public class StpCommand extends PlayerCommand
{
    public StpCommand() {
        super("stp", "stp", "/stp <gracz>", "core.cmd.helper", "tphere", "s");
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(p, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
            
        }
        final String nickja = args[0];
        if (nickja.equalsIgnoreCase(p.getName())) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie mozesz przeteleportowac sie sam do siebie! ;(");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
        }
        o.teleport(p.getLocation());
        ChatUtil.sendMessage(p, "&7Przeteleportowales gracza &f" + o.getName() + " &7do gracza &f" + p.getName());
        return ChatUtil.sendMessage(o, "&7Zostales przeteleportowany do gracza &f" + p.getName());
    }
}
