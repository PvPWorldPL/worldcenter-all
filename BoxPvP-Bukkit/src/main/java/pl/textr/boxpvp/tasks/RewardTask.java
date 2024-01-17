package pl.textr.boxpvp.tasks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import api.managers.ItemsManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.DataUtil;

public class RewardTask extends BukkitRunnable {
    private final Player player; 
    private int countdown;
    private final int initialCountdown;
    private int countdownOdlamek;
    private final int initialCountdownOdlamek;
    private final BossBar basicRewardBossBar;
    private final BossBar odlamekRewardBossBar;    
    private final BossBar DisableRewardBossBar;
    static Map<Player, RewardTask> activeTasks = new HashMap<>();

   
    public RewardTask(int initialCountdown, int initialCountdownOdlamek, Player player) {
        this.player = player;
        this.countdown = initialCountdown;
        this.initialCountdown = initialCountdown;        
        this.countdownOdlamek = initialCountdownOdlamek;
        this.initialCountdownOdlamek = initialCountdownOdlamek;
        this.basicRewardBossBar = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&aPodstawową nagrodę otrzymasz za &2") + DataUtil.convertSecondsToTime(countdown) + " &7(&2{progress}%&7)", BarColor.GREEN, BarStyle.SOLID);        
        this.odlamekRewardBossBar = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&#01ffff&lK&#03f6fc&la&#05edf9&lm&#07e4f6&li&#09dbf3&le&#0ad2f0&ln &#0cc9ed&lf&#0ec0ea&li&#10b7e7&ll&#12ade3&lo&#14a4e0&lz&#169bdd&lo&#1892da&lf&#1989d7&li&#1b80d4&lc&#1d77d1&lz&#1f6ece&ln&#2165cb&ly &7otrzymasz za &f") + DataUtil.convertSecondsToTime(countdownOdlamek) + " &7szansa na trafienie (&f2%&7)", BarColor.BLUE, BarStyle.SEGMENTED_20);        
        this.DisableRewardBossBar = Bukkit.createBossBar(ChatUtil.translateHexColorCodes("&#fb1204&lNa tym kanale &f&lStrefa AFK &#fb1204&lnie działa! Użyj komendy &f&l/ch &#fb1204&laby przejść na inny kanał!"), BarColor.RED, BarStyle.SOLID);        
        
    }

    public static void startTask(int initialCountdown, int initialCountdownOdlamek, Player player) {
        cancelTask(player);
        if (!activeTasks.containsKey(player)) {
            RewardTask rewardTask = new RewardTask(initialCountdown, initialCountdownOdlamek, player);
            rewardTask.runTaskTimerAsynchronously(Main.getPlugin(), 0, 20);
            activeTasks.put(player, rewardTask);
        }
    }

    public static void cancelTask(Player player) {    	
        RewardTask rewardTask = activeTasks.remove(player);
        if (rewardTask != null) {
            rewardTask.cancel();           
            if (rewardTask.basicRewardBossBar.isVisible()) {
                rewardTask.basicRewardBossBar.setVisible(false);
                rewardTask.basicRewardBossBar.removePlayer(player);
            }        
            if (rewardTask.odlamekRewardBossBar.isVisible()) {
                rewardTask.odlamekRewardBossBar.setVisible(false);
                rewardTask.odlamekRewardBossBar.removePlayer(player);
            }        
            if (rewardTask.DisableRewardBossBar.isVisible()) {
                rewardTask.DisableRewardBossBar.setVisible(false);
                rewardTask.DisableRewardBossBar.removePlayer(player);
            }
        }
    }   
    
    @Override
    public void run() {
    	int progress = (int) (((double) (initialCountdown - countdown) / initialCountdown) * 100);
        String progressString = progress + "%";
        if (countdown <= 0) {
            giveMoneyToPlayer(player);
            countdown = initialCountdown;     
            basicRewardBossBar.setTitle(ChatUtil.translateHexColorCodes("&aPodstawową nagrodę otrzymasz za &2" + DataUtil.convertSecondsToTime(countdown) + " &7(&2" + progressString + "&7)"));
            basicRewardBossBar.setProgress((double) countdown / initialCountdown); 
            return;
        }
        if (countdownOdlamek <= 0) {
            giveMoneyToOdlamek(player);
            countdownOdlamek = initialCountdownOdlamek;
       	    odlamekRewardBossBar.setTitle(ChatUtil.translateHexColorCodes("&#01ffff&lK&#03f6fc&la&#05edf9&lm&#07e4f6&li&#09dbf3&le&#0ad2f0&ln &#0cc9ed&lf&#0ec0ea&li&#10b7e7&ll&#12ade3&lo&#14a4e0&lz&#169bdd&lo&#1892da&lf&#1989d7&li&#1b80d4&lc&#1d77d1&lz&#1f6ece&ln&#2165cb&ly &7otrzymasz za &f" + DataUtil.convertSecondsToTime(countdownOdlamek) + " &7szansa na trafienie (&f2%&7)"));
            return;
        }
        if (!Main.getPlugin().getConfiguration().afk()) {         
        	if (!DisableRewardBossBar.getPlayers().contains(player)) {
            DisableRewardBossBar.addPlayer(player);
            }
            return;
        }
        basicRewardBossBar.setTitle(ChatUtil.translateHexColorCodes("&aPodstawową nagrodę otrzymasz za &2" + DataUtil.convertSecondsToTime(countdown) + " &7(&2" + progressString + "&7)"));
        basicRewardBossBar.setProgress((double) countdown / initialCountdown);    
        odlamekRewardBossBar.setTitle(ChatUtil.translateHexColorCodes("&#01ffff&lK&#03f6fc&la&#05edf9&lm&#07e4f6&li&#09dbf3&le&#0ad2f0&ln &#0cc9ed&lf&#0ec0ea&li&#10b7e7&ll&#12ade3&lo&#14a4e0&lz&#169bdd&lo&#1892da&lf&#1989d7&li&#1b80d4&lc&#1d77d1&lz&#1f6ece&ln&#2165cb&ly &7otrzymasz za &f" + DataUtil.convertSecondsToTime(countdownOdlamek) + " &7szansa na trafienie (&f2%&7)"));
        odlamekRewardBossBar.setProgress((double) countdownOdlamek / initialCountdownOdlamek);
        
        if (!basicRewardBossBar.getPlayers().contains(player)) {
        basicRewardBossBar.addPlayer(player);
        }    
        if (!odlamekRewardBossBar.getPlayers().contains(player)) {
        odlamekRewardBossBar.addPlayer(player);
        }
        countdown--;
        countdownOdlamek--;        
    }

    private void giveMoneyToOdlamek(Player player) {
        double dropChance = 0.02; // 1%
        if (Math.random() < dropChance) {
            player.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(1)));
        } else {
            ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&c Nie wylosowałeś żadnego przedmiotu"));
        }
    }



    private void giveMoneyToPlayer(Player player) {
	  player.getInventory().addItem(new ItemStack(ItemsManager.getMoneta1(16)));
	  if (player.getInventory().contains(ItemsManager.getMoneta1(64))) {
    	  ItemsManager.change3(player, ItemsManager.getMoneta1(64), ItemsManager.getMoneta2(1));
      }
      if (player.getInventory().contains(ItemsManager.getMoneta2(64))) {
    	  ItemsManager.change3(player, ItemsManager.getMoneta2(64), ItemsManager.getMoneta3(1));
      }
      if (player.getInventory().contains(ItemsManager.getMoneta3(64))) {
    	  ItemsManager.change3(player, ItemsManager.getMoneta3(64), ItemsManager.getMoneta4(1));
      }
      if (player.getInventory().contains(ItemsManager.getMoneta4(64))) {
    	  ItemsManager.change3(player, ItemsManager.getMoneta4(64), ItemsManager.getMoneta5(1));
      }
      if (player.getInventory().contains(ItemsManager.getMoneta5(64))) {
    	  ItemsManager.change3(player, ItemsManager.getMoneta5(64), ItemsManager.getMoneta6(1));
        }
      if (player.getInventory().contains(ItemsManager.getMoneta6(64))) {
    	  ItemsManager.change3(player, ItemsManager.getMoneta6(64), ItemsManager.getMoneta7(1));
        }
      if (player.getInventory().contains(ItemsManager.getMoneta7(64))) {
    	  ItemsManager.change3(player, ItemsManager.getMoneta7(64), ItemsManager.getMoneta8(1));
        }
	
}
}