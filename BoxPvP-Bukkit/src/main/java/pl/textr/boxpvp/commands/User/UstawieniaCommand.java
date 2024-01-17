package pl.textr.boxpvp.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.menu.UstawieniaMenu;

@CommandInfo(name = "ustawienia", description = "Otwórz panel ustawień", usage = "/ustawienia", permission = "core.cmd.user")
public class UstawieniaCommand extends PlayerCommandExecutor {
	
    @Override
    public boolean onCommand(final Player player, final String[] args) {
        UstawieniaMenu.show(player);
        return false;
    }
}
