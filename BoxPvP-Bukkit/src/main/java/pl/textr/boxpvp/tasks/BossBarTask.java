package pl.textr.boxpvp.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.DataUtil;

public class BossBarTask implements Runnable {
    private static final BossBar turboDropBossBar = Bukkit.createBossBar(
            ChatColor.translateAlternateColorCodes('&', "&eNa serwerze jest aktualnie &6drop x" + Main.getPlugin().getConfiguration().turbodropmnoznik() + " &8(&f00:00&8)"),
            BarColor.YELLOW, BarStyle.SEGMENTED_10);
    private static final BossBar turboRangiBossBar = Bukkit.createBossBar(
            ChatColor.translateAlternateColorCodes('&', "&eNa serwerze jest aktualnie &6drop rang &8(&f00:00&8)"),
            BarColor.WHITE, BarStyle.SEGMENTED_10);

    @Override
    public void run() {
        long turbodropTimer = Main.getPlugin().getConfiguration().turbodrop;
        long turboRangiTimer = Main.getPlugin().getConfiguration().vouchery;

        if (turbodropTimer < System.currentTimeMillis() && !turboDropBossBar.getPlayers().isEmpty()) {
            turboDropBossBar.removeAll();
        }
        if (turboRangiTimer < System.currentTimeMillis() && !turboRangiBossBar.getPlayers().isEmpty()) {
            turboRangiBossBar.removeAll();
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (turbodropTimer < System.currentTimeMillis()) {
                turboDropBossBar.addPlayer(onlinePlayer);
                turboDropBossBar.setTitle(ChatColor.translateAlternateColorCodes('&', "&eNa serwerze jest aktualnie &6drop x" + Main.getPlugin().getConfiguration().turbodropmnoznik() + " &8(&f" + DataUtil.secondsToString(turbodropTimer) + "&8)"));
                double progress = 1.0 - ((double) (turbodropTimer - System.currentTimeMillis()) / (double) (Main.getPlugin().getConfiguration().turbodrop - turbodropTimer));
                turboDropBossBar.setProgress(progress);
            } else {
                turboRangiBossBar.addPlayer(onlinePlayer);
                turboRangiBossBar.setTitle(ChatColor.translateAlternateColorCodes('&', "&eNa serwerze jest aktualnie &6drop rang &8(&f" + DataUtil.secondsToString(turboRangiTimer) + "&8)"));
                double progress = 1.0 - ((double) (turboRangiTimer - System.currentTimeMillis()) / (double) (Main.getPlugin().getConfiguration().vouchery - turboRangiTimer));
                turboRangiBossBar.setProgress(progress);
            }
        }
    }
}