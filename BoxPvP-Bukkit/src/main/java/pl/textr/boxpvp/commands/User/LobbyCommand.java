package pl.textr.boxpvp.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.ConnectUtil;

@CommandInfo(name = "lobby", description = "Przejdź do lobby", usage = "/lobby", permission = "core.cmd.user", aliases = {"lobby"})
public class LobbyCommand extends PlayerCommandExecutor {
    
    @Override
    public boolean onCommand(final Player player, final String[] args) {
 ConnectUtil.connect(player, "lobby1");
 return true;
        
    }
}
