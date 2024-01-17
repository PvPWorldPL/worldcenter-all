package pl.textr.boxpvp.commands.Admin;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.redis.packet.broadcast.BroadcastMeteorRandomPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "meteorspawn", description = "Spawnowanie meteorów", usage = "/meteorspawn <random>", permission = "core.cmd.admin")
public class MeteorSpawnCommand extends PlayerCommandExecutor {
	
  public boolean onCommand(Player p, String[] args) {
    if (args.length == 0) {
        return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));

    } 
    if (!args[0].equalsIgnoreCase("tutaj") && !args[0].equalsIgnoreCase("random")) {
        return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        
    }
 
    if (args[0].equalsIgnoreCase("random")) {

	    BroadcastMeteorRandomPacket BroadcastMeteorRandomPacket;
	    BroadcastMeteorRandomPacket = new BroadcastMeteorRandomPacket();
		Main.getPlugin().getRedisService().publishAsync("BroadcastMeteorRandom", BroadcastMeteorRandomPacket);	
   
      return true;
    } 
    return false;
  }
}
