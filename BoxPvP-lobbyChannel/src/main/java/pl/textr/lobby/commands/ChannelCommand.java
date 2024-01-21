package pl.textr.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.textr.lobby.Lobby;

public class ChannelCommand implements CommandExecutor {

 
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("core.cmd.user")) {     
         Player player = (Player) sender;
          Lobby.getInstance().openGUI(player);
                }
		return true;
            
	
        }
    
}