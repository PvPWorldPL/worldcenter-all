package pl.textr.connect.listeners;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.ServerPing.PlayerInfo;
import net.md_5.bungee.api.ServerPing.Players;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.textr.connect.AuthPlugin;
import pl.textr.connect.utils.ChatUtil;

public class ProxyPingListener implements Listener
{
 

    
	@EventHandler
	public void onProxyPing(final ProxyPingEvent e) {
	    ServerPing ping = e.getResponse();
	

	    List<String> lines = AuthPlugin.getPlugin().getConfiguration().getProxyPingLines();
	    String loadValue = String.format("%.2f", getLoad());


	    ServerPing.PlayerInfo[] sample = new ServerPing.PlayerInfo[lines.size()];
	         
	    for (int i = 0; i < sample.length; i++) {
	    	  sample[i] = new ServerPing.PlayerInfo(ChatUtil.translateHexColorCodes(lines.get(i)).replace("{LOAD}", loadValue), "");
	    }

	    ping.getPlayers().setSample(sample);
	}
 
	
	/*
	 * @EventHandler public void onProxyPing(final ProxyPingEvent e) { ServerPing
	 * ping = e.getResponse();
	 * 
	 * List<String> lines =
	 * AuthPlugin.getPlugin().getConfiguration().getProxyPingLines(); String
	 * loadValue = String.format("%.2f", getLoad());
	 * 
	 * ServerPing.PlayerInfo[] sample = new ServerPing.PlayerInfo[lines.size()];
	 * 
	 * 
	 * 
	 * 
	 * for (int i = 0; i < sample.length; i++) { sample[i] = new
	 * ServerPing.PlayerInfo(ChatUtil.translateHexColorCodes(lines.get(i)).replace(
	 * "{LOAD}", loadValue), ""); }
	 * 
	 * ping.getPlayers().setSample(sample); }
	 */
 
    
	public static double getLoad() {
	    try {
	        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
	        return os.getSystemLoadAverage();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return 0;
	}
}