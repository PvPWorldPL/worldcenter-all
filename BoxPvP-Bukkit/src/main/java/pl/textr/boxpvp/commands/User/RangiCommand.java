package pl.textr.boxpvp.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.menu.RanksMenu;

@CommandInfo(name = "rangi", description = "Wyświetla dostępne rangi", usage = "/rangi", permission = "core.cmd.user", aliases = {"rangi"})
public class RangiCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player player, final String[] args) {
        RanksMenu.show(player);
        return false;
    }
}
