package api.rankings;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;

import api.data.Clans;
import api.data.UserProfile;

public class UserRankingManager
{
	private static List<UserProfile> rankings = new LinkedList<>();
	private static List<Clans> guildRankings = new LinkedList<>();

    
    public static List<UserProfile> getRankings() {
        return UserRankingManager.rankings;
    }
    
    public static List<Clans> getGuildRankings() {
        return UserRankingManager.guildRankings;
    }
    
    
    
    
    public static void addRanking(final UserProfile ranking) {
        UserRankingManager.rankings.add(ranking);
        sortUserRankings();
    }
    

    
    public static void addRanking(final Clans ranking) {
        UserRankingManager.guildRankings.add(ranking);
        sortGuildRankings();
    }
    
    
 
    
    public static void removeRanking(final UserProfile ranking) {
        UserRankingManager.rankings.remove(ranking);
        sortUserRankings();
    }
    
    
    
    public static void removeRanking(final Clans ranking) {
        UserRankingManager.guildRankings.remove(ranking);
        sortGuildRankings();
    }
    


    
  
    
    public static void sortUserRankings() {
        UserRankingManager.rankings.sort((g0, g1) -> {
            final Integer p0 = g0.getPoints();
            final Integer p2 = g1.getPoints();

            return p2.compareTo(p0); // Zamiana miejscami p0 i p2
        });
    }
 
 
    
    
    public static void sortGuildRankings() {
        UserRankingManager.guildRankings.sort((g0, g1) -> {
            final Integer p0 = g0.getPoints();
            final Integer p2 = g1.getPoints();
  
            return p2.compareTo(p0); // Zamiana miejscami p0 i p2
        });
    }
 

    
    public static int getPlaceUser(final UserProfile user) {
        for (int num = 0; num < UserRankingManager.rankings.size(); ++num) {
            if (UserRankingManager.rankings.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }

    
    public static int getPlaceGuild(final Clans clans) {
        for (int num = 0; num < UserRankingManager.rankings.size(); ++num) {
            if (UserRankingManager.guildRankings.get(num).equals(clans)) {
                return  num + 1;
            }
        }
        return 0;
    }

    
}
