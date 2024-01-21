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

        if (turbodropTimer < System.currentTimeMillis()) {
            if (!turboDropBossBar.getPlayers().isEmpty()) {
                turboDropBossBar.removeAll();
            }
        } else {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                turboDropBossBar.addPlayer(onlinePlayer);
                turboDropBossBar.setTitle(ChatColor.translateAlternateColorCodes('&', "&eɴᴀ sᴇʀᴡᴇʀᴢᴇ ᴊᴇsᴛ ᴀᴋᴜᴛᴀʟɴɪᴇ &6ᴅʀᴏᴘ x" + Main.getPlugin().getConfiguration().turbodropmnoznik() + " &8(&f" + DataUtil.secondsToString(turbodropTimer) + "&8)"));
                double progress = 1.0 - ((double) (System.currentTimeMillis() - turbodropTimer) / (double) (turbodropTimer - System.currentTimeMillis()));
                turboDropBossBar.setProgress(Math.max(0.0, Math.min(1.0, progress)));
            }

            if (turboRangiTimer < System.currentTimeMillis()) {
                if (!turboRangiBossBar.getPlayers().isEmpty()) {
                    turboRangiBossBar.removeAll();
                }
            } else {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    turboRangiBossBar.addPlayer(onlinePlayer);
                    turboRangiBossBar.setTitle(ChatColor.translateAlternateColorCodes('&', "&eɴᴀ sᴇʀᴡᴇʀᴢᴇ ᴊᴇsᴛ ᴀᴋᴜᴛᴀʟɴɪᴇ &6ᴅʀᴏᴘ ʀᴀɴɢ &8(&f" + DataUtil.secondsToString(turboRangiTimer) + "&8)"));
                    double progress = 1.0 - ((double) (System.currentTimeMillis() - turboRangiTimer) / (double) (turboRangiTimer - System.currentTimeMillis()));
                    turboDropBossBar.setProgress(Math.max(0.0, Math.min(1.0, progress)));
                }

            }
        }
    }
}
