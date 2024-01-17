package pl.textr.boxpvp.commands.Admin;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "stp", description = "Teleportacja gracza do Ciebie", usage = "/stp <gracz>", permission = "core.cmd.helper", aliases = {"tphere", "s"})
public class StpCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
    		return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
    		
        }
        final String nickja = args[0];
        if (nickja.equalsIgnoreCase(p.getName())) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie mozesz przeteleportowac sie sam do siebie! ;(");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
        }
        o.teleport(p.getLocation());
        ChatUtil.sendMessage(p, "&fPrzeteleportowales gracza &f" + o.getName() + " &7do gracza &f" + p.getName());
        return ChatUtil.sendMessage(o, "&7Zostales przeteleportowany do gracza &f" + p.getName());
    }
}
