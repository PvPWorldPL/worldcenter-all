package pl.textr.boxpvp.tasks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.DataUtil;

public class CrystalBossTask extends BukkitRunnable {
    private final Player player;
    private int countdown;
    private final int initialCountdown;
    private final BossBar basicCrystalBossBar;
    static Map<Player, CrystalBossTask> activeTasks = new HashMap<>();

    public CrystalBossTask(int initialCountdown, Player player) {
        this.player = player;
        this.countdown = initialCountdown;
        this.initialCountdown = initialCountdown;
        this.basicCrystalBossBar = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&aWitaj w krainie crystal za &2") + DataUtil.convertSecondsToTime(countdown) + " &7zrespi sie mirek &7(&2{progress}%&7)", BarColor.GREEN, BarStyle.SOLID);
    }

    public static void startTask(int initialCountdown, Player player) {
        cancelTask(player);
        if (!activeTasks.containsKey(player)) {
            CrystalBossTask CrystalTask = new CrystalBossTask(initialCountdown, player);
            CrystalTask.runTaskTimerAsynchronously(Main.getPlugin(), 0, 20);
            activeTasks.put(player, CrystalTask);
        }
    }

    public static void cancelTask(Player player) {
        CrystalBossTask CrystalTask = activeTasks.remove(player);
        if (CrystalTask != null) {
            CrystalTask.cancel();
            if (CrystalTask.basicCrystalBossBar.isVisible()) {
                CrystalTask.basicCrystalBossBar.setVisible(false);
                CrystalTask.basicCrystalBossBar.removePlayer(player);
            }
        }
    }

    @Override
    public void run() {
        int progress = (int) (((double) (initialCountdown - countdown) / initialCountdown) * 100);
        String progressString = progress + "%";
        if (countdown <= 0) {
            Bukkit.getScheduler().runTask(Main.getPlugin(), () -> spawnCustomEnderman(player));
            countdown = initialCountdown;
            basicCrystalBossBar.setTitle(ChatUtil.translateHexColorCodes("&aWitaj w krainie crystal za &2" + DataUtil.convertSecondsToTime(countdown) + " &7zrespi sie mirek &7(&2" + progressString + "&7)"));
            basicCrystalBossBar.setProgress((double) countdown / initialCountdown);
            return;
        }
        basicCrystalBossBar.setTitle(ChatUtil.translateHexColorCodes("&aWitaj w krainie crystal za &2" + DataUtil.convertSecondsToTime(countdown) + " &7zrespi sie mirek &7(&2" + progressString + "&7)"));
        basicCrystalBossBar.setProgress((double) countdown / initialCountdown);

        if (!basicCrystalBossBar.getPlayers().contains(player)) {
            basicCrystalBossBar.addPlayer(player);
        }
        countdown--;
    }


    public static void spawnCustomEnderman(Player player) {
        World world = player.getWorld();
        Location spawnLocation = new Location(world, 102.597, 24, 363.307); // Set your desired coordinates

        Enderman enderman = (Enderman) world.spawnEntity(spawnLocation, EntityType.ENDERMAN);
        enderman.setCustomName("MIREK");
        enderman.setCustomNameVisible(true);
        enderman.setMaxHealth(1000);
        enderman.setHealth(1000);
        enderman.setAI(false);
        enderman.setSilent(true);
        enderman.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
        enderman.setMetadata("CustomEnderman", new FixedMetadataValue(Main.getPlugin(), null));
        enderman.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 10));
        enderman.setMetadata("CustomEnderman", new FixedMetadataValue(Main.getPlugin(), player.getUniqueId().toString()));
    }
}