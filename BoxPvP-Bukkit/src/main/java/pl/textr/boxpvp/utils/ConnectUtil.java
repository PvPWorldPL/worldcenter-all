package pl.textr.boxpvp.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.entity.Player;

import pl.textr.boxpvp.Main;

public class ConnectUtil {
   
	
	  
	
	
	
	
	public static void connect(final Player p, final String srv) {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(srv);
            b.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        p.sendPluginMessage(Main.getPlugin(), "BungeeCord", b.toByteArray());
    }
}