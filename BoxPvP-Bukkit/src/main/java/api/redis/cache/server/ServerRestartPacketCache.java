package api.redis.cache.server;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

import api.redis.PacketListener;
import api.redis.packet.server.ServerRestartPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;


public class ServerRestartPacketCache implements PacketListener<ServerRestartPacket> {

    public static boolean restartInProgress = false; // Zmienna flagowa śledząca stan restartu
    private int initialCountdown = 10; // Początkowa wartość odliczania (np. 10 sekund)
    private int countdown = initialCountdown; // Inicjalizacja zmiennej countdown z wartością początkowego odliczania


    public void handle(ServerRestartPacket packet) {

        final BossBar bossBar = Bukkit.createBossBar((ChatUtil.translateHexColorCodes("&#06d282&oR&#07d07f&oe&#09cf7c&os&#0acd79&ot&#0bcc76&oa&#0dca73&or&#0ec970&ot &#0fc76d&os&#11c669&oe&#12c466&or&#13c363&ow&#15c160&oe&#16c05d&or&#17be5a&oa &#19bd57&oz&#1abb54&oa &#d20000")) + countdown + " &#06d282&os&#0dca73&oe&#13c363&ok&#1abb54&o.", BarColor.RED, BarStyle.SEGMENTED_20, new org.bukkit.boss.BarFlag[0]);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (countdown == 0) {

                    cancel(); ///cancel
                    bossBar.setVisible(false);
                    restartInProgress = false;
                    Bukkit.getServer().spigot().restart();
                    return;
                }

                Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                    bossBar.addPlayer(onlinePlayer);


                    bossBar.setTitle(ChatUtil.translateHexColorCodes("&#06d282&oR&#07d07f&oe&#09cf7c&os&#0acd79&ot&#0bcc76&oa&#0dca73&or&#0ec970&ot &#0fc76d&os&#11c669&oe&#12c466&or&#13c363&ow&#15c160&oe&#16c05d&or&#17be5a&oa &#19bd57&oz&#1abb54&oa &#d20000" + countdown + " &#06d282&os&#0dca73&oe&#13c363&ok&#1abb54&o."));
                    bossBar.setProgress((double) countdown / initialCountdown);
                });

                countdown--;
            }
        }.runTaskTimer(Main.getPlugin(), 0, 20);

    }

}