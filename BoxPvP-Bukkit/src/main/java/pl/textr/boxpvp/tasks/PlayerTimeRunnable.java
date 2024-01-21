package pl.textr.boxpvp.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.data.UserProfile;
import api.managers.UserAccountManager;

public class PlayerTimeRunnable implements Runnable {
	
	
	public void run() {
	    if (Bukkit.getOnlinePlayers().isEmpty()) {
	        return;
	    }

	    for (Player player : Bukkit.getOnlinePlayers()) {
	        UserProfile user = UserAccountManager.getUser(player);
	        user.addMoney(1);
	        user.save();
	    }
	}
}