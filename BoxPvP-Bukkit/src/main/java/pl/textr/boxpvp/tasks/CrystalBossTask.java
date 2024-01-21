package pl.textr.boxpvp.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.DataUtil;

import java.util.Calendar;

public class CrystalBossTask implements Runnable {
    private static final BossBar ENDERMAN_BOSS_BAR_ACTIVE = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&aAktualnie trwa boss MIREK"), BarColor.GREEN, BarStyle.SOLID, BarFlag.values());
    private static final BossBar ENDERMAN_BOSS_BAR = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&7Za &f1h 30sek pojawi się boss MIREK na strefie PvP"), BarColor.PURPLE, BarStyle.SEGMENTED_10, BarFlag.values());

    private static final int INITIAL_COUNTDOWN = 5400;
    private int currentCountdown = INITIAL_COUNTDOWN;
    private static final int MIN_PLAYERS = 1; // Minimalna liczba graczy
    private static final int ACTIVE_HOUR_START = 1;
    private static final int ACTIVE_HOUR_END = 20;

    @Override
    public void run() {
        Calendar currentCalendar = Calendar.getInstance();
        int currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY);
        int progress = (int) (((double) (INITIAL_COUNTDOWN - currentCountdown) / INITIAL_COUNTDOWN) * 100);
        String progressString = progress + "%";

        if (currentHour < ACTIVE_HOUR_START || currentHour >= ACTIVE_HOUR_END) {
            ENDERMAN_BOSS_BAR.removeAll();
            ENDERMAN_BOSS_BAR_ACTIVE.removeAll();
            return;
        }

        int onlinePlayersCount = Bukkit.getOnlinePlayers().size();
        if (onlinePlayersCount < MIN_PLAYERS) {
            return;
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            ENDERMAN_BOSS_BAR_ACTIVE.addPlayer(player);
            ENDERMAN_BOSS_BAR.addPlayer(player);
        });

        if (currentCountdown > 0) {
            ENDERMAN_BOSS_BAR.setTitle(ChatUtil.translateHexColorCodes("&7Za &f" + DataUtil.convertSecondsToTime(currentCountdown) + " pojawi się boss MIREK na strefie CRYSTAL &8(&f" + progressString + "&8)"));
            ENDERMAN_BOSS_BAR.setProgress((double) currentCountdown / INITIAL_COUNTDOWN);
            currentCountdown--;
        } else {
            ENDERMAN_BOSS_BAR_ACTIVE.setTitle(ChatUtil.translateHexColorCodes("&aAktualnie trwa boss MIREK ZNAJDZIESZ GO NA STREFIE CRYSTAL"));
            ENDERMAN_BOSS_BAR_ACTIVE.setProgress(1.0);
            Bukkit.getScheduler().runTask(Main.getPlugin(), this::spawnCustomEnderman);
            ENDERMAN_BOSS_BAR.setProgress(1.0);
            ENDERMAN_BOSS_BAR.removeAll();
            currentCountdown = INITIAL_COUNTDOWN;
        }
    }

    private Enderman spawnCustomEnderman() {
        World world = Bukkit.getWorlds().get(0); // Assuming you want to spawn it in the first world
        Location spawnLocation = new Location(world, 102.597, 24, 363.307); // Set your desired coordinates

        Enderman enderman = (Enderman) world.spawnEntity(spawnLocation, EntityType.ENDERMAN);
        enderman.setCustomName("MIREK");
        enderman.setGlowing(true);
        enderman.setCustomNameVisible(true);
        enderman.setMaxHealth(1000);

        enderman.setAI(false); // Enable AI
        enderman.setSilent(false);
        enderman.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));

        return enderman;
    }
}
