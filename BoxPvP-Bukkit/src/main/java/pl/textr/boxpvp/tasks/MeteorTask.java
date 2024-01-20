package pl.textr.boxpvp.tasks;
import java.util.Calendar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.RandomUtil;


public class MeteorTask implements Runnable {
    public void run() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);


        if (hour < 12 || hour > 18) {
            return;
        }

        if (Bukkit.getOnlinePlayers().size() < 10) {
            return;
        }

        Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "meteorspawn random" + RandomUtil.getRandInt(1, 20));
        });

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            ChatUtil.sendTitle(onlinePlayer, "", ChatUtil.translateHexColorCodes("&7za &f60 &7sekund pojawi się &adeszcz meteorytów &7na &cstrefie PvP"));
        }
    }
}

