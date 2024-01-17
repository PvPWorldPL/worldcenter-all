package pl.textr.boxpvp.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.teleport.TeleportTimer;



	@CommandInfo(name = "spawn", description = "Teleportuj się na spawn", usage = "/spawn", permission = "core.cmd.user", aliases = {"spawn"})
	public class SpawnCommand extends PlayerCommandExecutor {
		
	
    @Override
    public boolean onCommand(final Player player, final String[] args) {
    	int teleport = 10;

		if (player.hasPermission("core.premium")) {
			teleport = 5;
		} 
		if (player.getWorld().getName().equals("world")) {
			teleport = 0;
		}
		TeleportTimer.teleport(player, teleport);
	
 return true;
        
    }
}
