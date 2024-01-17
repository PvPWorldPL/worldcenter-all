package pl.textr.boxpvp.commands.Helper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "fly", description = "Zarządzanie trybem latania graczy", usage = "/fly [gracz]", permission = "core.cmd.helper")
public class FlyCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            toggleFly(p);
            return true;
        }

        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
          
            return true;
        }
        toggleFly(o);
        ChatUtil.sendMessage(o, "&7Fly zostal " + (o.getAllowFlight() ? "&awlaczony" : "&cwylaczony") + " &7przez &f" + p.getName());
        ChatUtil.sendMessage(p, "&7Fly zostal " + (o.getAllowFlight() ? "&awlaczony" : "&cwylaczony") + " &7dla &f" + o.getName());
        return true;
    }

    private void toggleFly(Player player) {
        player.setAllowFlight(!player.getAllowFlight());
        ChatUtil.sendMessage(player, "&7Fly zostal " + (player.getAllowFlight() ? "&awlaczony" : "&cwylaczony"));
    }
}
