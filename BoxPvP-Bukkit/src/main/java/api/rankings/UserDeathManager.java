package api.rankings;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;

import api.data.UserProfile;

public class UserDeathManager
{
	private static List<UserProfile> deaths = new LinkedList<>();


    
 
    
    public static List<UserProfile> getDeaths() {
        return UserDeathManager.deaths;
    }
    
  
    
    public static void addDeath(final UserProfile death) {
        UserDeathManager.deaths.add(death);
        sortUserDeaths();
    }
    
 
    
    public static void removeDeath(final UserProfile death) {
        UserDeathManager.deaths.remove(death);
        sortUserDeaths();
    }
    

    
    public static void sortUserDeaths() {
    	UserDeathManager.deaths.sort((g0, g1) -> {
            final Integer p0 = g0.getDeaths();
            final Integer p2 = g1.getDeaths();

            return p2.compareTo(p0); // Zamiana miejscami p0 i p2
        });
    }
 
    


    
    public static int getPlaceUser(final UserProfile user) {
        for (int num = 0; num < UserDeathManager.deaths.size(); ++num) {
            if (UserDeathManager.deaths.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
}
