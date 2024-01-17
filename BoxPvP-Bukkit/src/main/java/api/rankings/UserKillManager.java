package api.rankings;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;

import api.data.UserProfile;

public class UserKillManager
{
	private static List<UserProfile> kills = new LinkedList<>();

 
    
    public static List<UserProfile> getKills() {
        return UserKillManager.kills;
    }
    
  
    
    public static void addKill(final UserProfile kill) {
        UserKillManager.kills.add(kill);
        sortUserKills();
    }
    
  
    
    public static void removeKill(final UserProfile kill) {
        UserKillManager.kills.remove(kill);
        sortUserKills();
    }
    
   
    public static void sortUserKills() {
        UserKillManager.kills.sort((g0, g1) -> {
            final Integer p0 = g0.getKills();
            final Integer p2 = g1.getKills();

            return p2.compareTo(p0); // Zamiana miejscami p0 i p2
        });
    }

    public static int getPlaceUser(final String playerName) {
        for (int num = 0; num < UserKillManager.kills.size(); ++num) {
            if (UserKillManager.kills.get(num).equals(playerName)) {
                return num + 1;
            }
        }
        return 0;
    }
    
    public static int getPlaceUser(final UserProfile user) {
        for (int num = 0; num < UserKillManager.kills.size(); ++num) {
            if (UserKillManager.kills.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
    
}
