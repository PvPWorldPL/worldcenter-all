package pl.textr.boxpvp.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.DataUtil;

public class BossBarTask implements Runnable {

    private static final BossBar turboDropBossBar = Bukkit.createBossBar(
            ChatColor.translateAlternateColorCodes('&', "&eɴᴀ sᴇʀᴡᴇʀᴢᴇ ᴊᴇsᴛ ᴀᴋᴜᴛᴀʟɴɪᴇ &6ᴅʀᴏᴘ x" + Main.getPlugin().getConfiguration().turbodropmnoznik() + " &8(&f00:00&8)"),
            BarColor.YELLOW, BarStyle.SEGMENTED_10);
    private static final BossBar turboRangiBossBar = Bukkit.createBossBar(
            ChatColor.translateAlternateColorCodes('&', "&bɴᴀ sᴇʀᴡᴇʀᴢᴇ ᴊᴇsᴛ ᴀᴋᴜᴛᴀʟɴɪᴇ &6ᴅʀᴏᴘ ʀᴀɴɢ &8(&f00:00&8)"),
            BarColor.WHITE, BarStyle.SEGMENTED_10);

    @Override
    public void run() {
        long turbodropTimer = Main.getPlugin().getConfiguration().turbodrop;
        long turboRangiTimer = Main.getPlugin().getConfiguration().vouchery;

        Bukkit.getOnlinePlayers().forEach(player -> {
            turboDropBossBar.addPlayer(player);
            turboRangiBossBar.addPlayer(player);
        });


        if (turbodropTimer < System.currentTimeMillis()) {
            turboDropBossBar.setTitle(ChatColor.translateAlternateColorCodes('&', "&eɴᴀ sᴇʀᴡᴇʀᴢᴇ ᴊᴇsᴛ ᴀᴋᴜᴛᴀʟɴɪᴇ &6ᴅʀᴏᴘ x" + Main.getPlugin().getConfiguration().turbodropmnoznik() + " &8(&f" + DataUtil.secondsToString(turbodropTimer) + "&8)"));
            double progress = 1.0 - ((double) (System.currentTimeMillis() - turbodropTimer) / (double) (turbodropTimer - System.currentTimeMillis()));
            turboDropBossBar.setProgress(Math.max(0.0, Math.min(1.0, progress)));
        } else {
            turboDropBossBar.setProgress(1.0);
            turboDropBossBar.removeAll();
        }

        if (turboRangiTimer < System.currentTimeMillis()) {
            turboRangiBossBar.setTitle(ChatColor.translateAlternateColorCodes('&', "&eɴᴀ sᴇʀᴡᴇʀᴢᴇ ᴊᴇsᴛ ᴀᴋᴜᴛᴀʟɴɪᴇ &6ᴅʀᴏᴘ ʀᴀɴɢ &8(&f" + DataUtil.secondsToString(turboRangiTimer) + "&8)"));
            double progress = 1.0 - ((double) (System.currentTimeMillis() - turboRangiTimer) / (double) (turboRangiTimer - System.currentTimeMillis()));
            turboRangiBossBar.setProgress(Math.max(0.0, Math.min(1.0, progress)));
        } else {
            turboRangiBossBar.setProgress(1.0);
            turboRangiBossBar.removeAll();
        }
    }
}