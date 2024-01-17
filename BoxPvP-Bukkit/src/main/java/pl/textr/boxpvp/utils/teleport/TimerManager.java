package pl.textr.boxpvp.utils.teleport;

import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

import org.bukkit.scheduler.BukkitTask;

public class TimerManager
{
    public static HashMap<String, ScheduledFuture<?>> executors;
    public static HashMap<String, BukkitTask> bukkitTasks;

    static {
        TimerManager.executors = new HashMap<>();
        TimerManager.bukkitTasks = new HashMap<>();
  
    }
    
    public static void stopAll() {
        for (final ScheduledFuture<?> executors : TimerManager.executors.values()) {
        	executors.cancel(true);
        }
        for (final BukkitTask bukkitTasks : TimerManager.bukkitTasks.values()) {
        	bukkitTasks.cancel();
        }
    }
}
