package pl.textr.boxpvp.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.menu.WarpMenu;

@CommandInfo(name = "warp", description = "Przenosi cię do wybranego warp pointu", usage = "/warp", permission = "core.cmd.user")
public class WarpCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player player, final String[] args) {
        WarpMenu.show(player);
        return true;
    }
}
