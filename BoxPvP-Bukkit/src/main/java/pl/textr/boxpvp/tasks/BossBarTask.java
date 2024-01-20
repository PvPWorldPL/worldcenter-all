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
    private static final BossBar bossBar = Bukkit.createBossBar(
            ChatColor.translateAlternateColorCodes('&', "&eɴᴀ sᴇʀᴡᴇʀᴢᴇ ᴊᴇsᴛ ᴀᴋᴜᴛᴀʟɴɪᴇ &6ᴅʀᴏᴘ x2 &8(&f00:00&8)"),
            BarColor.YELLOW, BarStyle.SEGMENTED_12);

    @Override
    public void run() {
        long turbodropTimer = Main.getPlugin().getConfiguration().turbodrop;

        if (turbodropTimer < System.currentTimeMillis()) {
            if (!bossBar.getPlayers().isEmpty()) {
                bossBar.removeAll();
            }
        } else {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                bossBar.addPlayer(onlinePlayer);
                bossBar.setTitle(ChatColor.translateAlternateColorCodes('&', "&eɴᴀ sᴇʀᴡᴇʀᴢᴇ ᴊᴇsᴛ ᴀᴋᴜᴛᴀʟɴɪᴇ &6ᴅʀᴏᴘ x2 &8(&f" + DataUtil.secondsToString(turbodropTimer) + "&8)"));
                double progress = (double) (turbodropTimer - System.currentTimeMillis()) / (double) turbodropTimer;
                bossBar.setProgress(progress);

            }
        }
    }
}
