package pl.textr.boxpvp.tasks;

import org.bukkit.Bukkit;
import api.redis.BroadcastType;
import api.redis.packet.player.ServerRefreshTopsRanking;
import pl.textr.boxpvp.Main;

public class TabCategoryTask implements Runnable {
	private boolean canceled = false;

	@Override
	public void run() {
		if (canceled) {
			return;
		}

		ServerRefreshTopsRanking serverRefreshTopsRanking = new ServerRefreshTopsRanking(BroadcastType.TAB_UPDATE);
		Main.getPlugin().getRedisService().publishAsync("ServerRefreshTops", serverRefreshTopsRanking);
	}

	public void cancel() {
		if (Main.getPlugin().getConfiguration().debug) {
			Bukkit.getLogger().info("Task został zatrzymany");
		}
		canceled = true;
	}

	public void resetTask() {
		if (Main.getPlugin().getConfiguration().debug) {
			Bukkit.getLogger().info("Task został zrestartowany");
		}
		canceled = false;
	}
}
