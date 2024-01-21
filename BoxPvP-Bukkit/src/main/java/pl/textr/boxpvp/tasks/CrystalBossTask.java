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
    private static final BossBar ENDERMAN_BOSS_BAR_ACTIVE = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&#02fb24A&#07fb28K&#0cfb2bT&#11fb2fU&#16fb33A&#1bfb36L&#20fb3aN&#25fb3eI&#2afb41E &#2ffb45N&#33fc49A &#38fc4cS&#3dfc50E&#42fc54R&#47fc57W&#4cfc5bE&#51fc5fR&#56fc62Z&#5bfc66E &#60fc6aT&#65fc6eR&#6afc71W&#6ffc75A &#74fc79E&#79fc7cV&#7efc80E&#83fc84N&#88fc87T &#8cfd8bB&#91fd8fO&#96fd92S&#9bfd96S &#a0fd9aM&#a5fd9dI&#aafda1R&#affda5E&#b4fda8K&#b9fdac!"), BarColor.GREEN, BarStyle.SEGMENTED_20, BarFlag.values());
    private static final BossBar ENDERMAN_BOSS_BAR = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&7Za &f1h 30sek pojawi się boss MIREK na strefie PvP"), BarColor.PURPLE, BarStyle.SEGMENTED_10, BarFlag.values());

    private static final int INITIAL_COUNTDOWN = 2700;
    private int currentCountdown = INITIAL_COUNTDOWN;
    private static final int MIN_PLAYERS = 1; // Minimalna liczba graczy
    private static final int ACTIVE_HOUR_START = 15;
    private static final int ACTIVE_HOUR_END = 19;

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
            ENDERMAN_BOSS_BAR.setTitle(ChatUtil.translateHexColorCodes("&#fb0cf7Z&#fb0cf7a &f" + DataUtil.convertSecondsToTime(currentCountdown) + "&7 &#fb0cf7P&#f80df7o&#f50df7j&#f20ef8a&#ef0ff8w&#ec10f8i &#e910f8s&#e611f8i&#e312f8e &#e013f9b&#dd13f9o&#da14f9s&#d715f9s &#d415f9M&#d116faI&#ce17faR&#cb18faE&#c818faK &#c519fan&#c21afaa &#bf1bfbs&#bc1bfbt&#b91cfbr&#b61dfbe&#b31dfbf&#b01efci&#ad1ffce &#aa20fcc&#a720fcr&#a421fcy&#a122fcs&#9e23fdt&#9b23fda&#9824fdl &8(&f" + progressString + "&8)"));
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
