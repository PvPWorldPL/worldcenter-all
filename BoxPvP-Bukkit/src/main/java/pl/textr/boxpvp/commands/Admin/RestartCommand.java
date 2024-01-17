package pl.textr.boxpvp.commands.Admin;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.redis.cache.server.ServerRestartPacketCache;
import api.redis.packet.server.ServerRestartPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
	
@CommandInfo(name = "restarts", description = "Komenda do restartu serwera", usage = "/restarts", permission = "core.cmd.admin", aliases = {"restarts"})
public class RestartCommand extends PlayerCommandExecutor {
  
	    
	    @Override
	    public boolean onCommand(final Player p, final String[] args) {
	        if (ServerRestartPacketCache.restartInProgress) {
	           ChatUtil.sendMessage(p, ChatUtil.fixColor("&8[&C&l!&8] Restart serwera jest już w trakcie!"));
	            return true;
	        }
	        
	      ServerRestartPacketCache.restartInProgress = true;
	      ServerRestartPacket ServerRestartPacket;
	      ServerRestartPacket = new ServerRestartPacket();
		Main.getPlugin().getRedisService().publishAsync("ServerRestart", ServerRestartPacket);	
		return true;
	      
	      
	    }
}