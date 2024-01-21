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
    private static final BossBar METEOR_BOSS_BAR_ACTIVE = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&aAktualnie trwa deszcz meteorytów"), BarColor.GREEN, BarStyle.SEGMENTED_20, BarFlag.values());
    private static final BossBar METEOR_BOSS_BAR = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&7Za &f60 &7sekund pojawi się deszcz meteorytów na strefie PvP"), BarColor.RED, BarStyle.SEGMENTED_10, BarFlag.values());

    private static final int INITIAL_COUNTDOWN = 60;
    private int currentCountdown = INITIAL_COUNTDOWN;
    private static final int MIN_PLAYERS = 1; // Minimalna liczba graczy
    private static final int ACTIVE_HOUR_START = 15;
    private static final int ACTIVE_HOUR_END = 18;

    public void run() {
        Calendar currentCalendar = Calendar.getInstance();
        int currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY);

        if (currentHour < ACTIVE_HOUR_START || currentHour >= ACTIVE_HOUR_END) {
            METEOR_BOSS_BAR.removeAll();
            METEOR_BOSS_BAR_ACTIVE.removeAll();
            return;
        }

        int onlinePlayersCount = Bukkit.getOnlinePlayers().size();
        if (onlinePlayersCount < MIN_PLAYERS) {
            return;
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            METEOR_BOSS_BAR_ACTIVE.addPlayer(player);
            METEOR_BOSS_BAR.addPlayer(player);

        });

        if (currentCountdown > 0) {

            METEOR_BOSS_BAR.setTitle(ChatUtil.translateHexColorCodes("&7Za &f" + DataUtil.convertSecondsToTime(currentCountdown) + " &7sekund pojawi się deszcz meteorytów na strefie PvP"));
            METEOR_BOSS_BAR.setProgress((double) currentCountdown / INITIAL_COUNTDOWN);
            currentCountdown--;

        } else {
            METEOR_BOSS_BAR_ACTIVE.setTitle(ChatUtil.translateHexColorCodes("&aAktualnie trwa deszcz meteorytów"));
            METEOR_BOSS_BAR_ACTIVE.setProgress(1.0);
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
