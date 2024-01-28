package api.redis.cache.server;

import api.placeholders.PlayerTabInfoUtil;
import api.redis.BroadcastType;
import api.redis.PacketListener;
import api.redis.packet.player.ServerRefreshTopsRanking;
import api.redis.packet.server.ServerRefreshConfigurationPacket;
import org.bukkit.Bukkit;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.tasks.TabCategoryTask;
import pl.textr.boxpvp.utils.ChatUtil;

public class ServerRefreshConfigurationCache implements PacketListener<ServerRefreshConfigurationPacket> {


    @Override
    public void handle(ServerRefreshConfigurationPacket packet) {
        Main.getPlugin().getConfiguration().load();
       if (Main.getPlugin().getConfiguration().debug) {
         Bukkit.getLogger().info("Konfiguracja zastala przeladowana");

       }
    }


}