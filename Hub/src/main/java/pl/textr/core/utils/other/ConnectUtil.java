package pl.textr.core.utils.other;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.textr.core.LobbyPlugin;

public class ConnectUtil
{
    public static void connect(final Player p, final String srv) {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(srv);
            b.close();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().severe("Blad podczas laczenia z " + srv);
            ChatUtil.sendMessage(p, "Blad podczas laczenia z " + srv);
        }
        p.sendPluginMessage(LobbyPlugin.getPlugin(), "BungeeCord", b.toByteArray());
    }
}
