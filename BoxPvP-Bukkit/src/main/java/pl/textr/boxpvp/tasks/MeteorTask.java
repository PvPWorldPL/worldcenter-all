package pl.textr.boxpvp.tasks;

import api.redis.packet.broadcast.BroadcastMeteorRandomPacket;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.DataUtil;

import java.util.Calendar;

public class MeteorTask implements Runnable {
    private static final BossBar METEOR_BOSS_BAR = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&7Za &f60 &7sekund pojawi się deszcz meteorytów na strefie PvP"), BarColor.YELLOW, BarStyle.SOLID, BarFlag.values());
    private static final int INITIAL_COUNTDOWN = 60;
    private int currentCountdown = INITIAL_COUNTDOWN;
    private static final int MIN_PLAYERS = 1; // Minimalna liczba graczy
    private static final int ACTIVE_HOUR_START = 16;
    private static final int ACTIVE_HOUR_END = 20;

    public void run() {
        Calendar currentCalendar = Calendar.getInstance();
        int currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY);

        if (currentHour < ACTIVE_HOUR_START || currentHour >= ACTIVE_HOUR_END) {
            return;
        }

        int onlinePlayersCount = Bukkit.getOnlinePlayers().size();
        if (onlinePlayersCount < MIN_PLAYERS) {
            return;
        }
        Bukkit.getOnlinePlayers().forEach(METEOR_BOSS_BAR::addPlayer);
        if (currentCountdown > 0) {
            METEOR_BOSS_BAR.setTitle(ChatUtil.translateHexColorCodes("&7Za &f" + DataUtil.convertSecondsToTime(currentCountdown) + " &7sekund pojawi się deszcz meteorytów na strefie PvP"));
            METEOR_BOSS_BAR.setProgress((double) currentCountdown / INITIAL_COUNTDOWN);
            currentCountdown--;
        } else {
            executeMeteorSpawn();
            METEOR_BOSS_BAR.setProgress(1.0);
            METEOR_BOSS_BAR.removeAll();
            currentCountdown = INITIAL_COUNTDOWN;
        }
    }


public void executeMeteorSpawn() {
    BroadcastMeteorRandomPacket BroadcastMeteorRandomPacket;
    BroadcastMeteorRandomPacket = new BroadcastMeteorRandomPacket();
    Main.getPlugin().getRedisService().publishAsync("BroadcastMeteorRandom", BroadcastMeteorRandomPacket);

}
}
