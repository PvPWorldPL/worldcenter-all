package api.placeholders;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import org.bukkit.entity.Player;

import api.data.Clans;
import api.rankings.UserBalanceManager;
import api.rankings.UserDeathManager;
import api.rankings.UserKillManager;
import api.rankings.UserRankingManager;
import net.md_5.bungee.api.ChatColor;
import pl.textr.boxpvp.utils.ChatUtil;

	public class PlayerTabInfoUtil {
	
	
	    public static double getLoad() {
	        try {
	            OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
	            return os.getSystemLoadAverage();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return 0;
	    }
	
	    public static ChatColor getColorByLoad(double load) {
	        if (load < 25.0) {
	            return ChatColor.GREEN;
	        } else if (load < 50.0) {
	            return ChatColor.YELLOW;
	        } else if (load < 75.0) {
	            return ChatColor.GOLD;
	        } else {
	            return ChatColor.RED;
	        }
	    }
	    
	    
	    
	    
	    

	    
	     public static int category = 1; // 1 oznacza punkty, 2 oznacza zabójstwa
	  
	        public static String getTopPoints(final Player p, int i) {
	
	            if (UserRankingManager.getRankings().size() >= i) {
	                String s = "§7" + i + ". §c";
	
	                // Sprawdź, która kategoria jest aktualnie wybrana
	                if (category == 1) {
	                	//RankingManager.sortUserRankings();
	                    String playerName = UserRankingManager.getRankings().get(i - 1).getName();
	                    String onlineStatus = p != null && UserRankingManager.getRankings().get(i - 1).isOnline() ? "§a•" : "§c•";
	                    String points = "§f" + UserRankingManager.getRankings().get(i - 1).getPoints();
	                    return s + onlineStatus + " §7" + playerName + " " + points;
	                } else if (category == 2) {
	                	//KillManager.sortUserKills();
	                    String playerName = UserKillManager.getKills().get(i - 1).getName();
	                    String onlineStatus = p != null && UserKillManager.getKills().get(i - 1).isOnline() ? "§a•" : "§c•";
	                    String kills = "§f" + UserKillManager.getKills().get(i - 1).getKills();
	                    return s + onlineStatus + " §7" + playerName + " " + kills;
	                
	            } else if (category == 3) {
	            	//	DeathManager.sortUserDeaths();
	                String playerName = UserDeathManager.getDeaths().get(i - 1).getName();
	                String onlineStatus = p != null && UserDeathManager.getDeaths().get(i - 1).isOnline() ? "§a•" : "§c•";
	                String kills = "§f" + UserDeathManager.getDeaths().get(i - 1).getDeaths();
	                return s + onlineStatus + " §7" + playerName + " " + kills;
	            } else if (category == 4) {
	            	//	BalanceManager.sortUserBalance();
	                String playerName = UserBalanceManager.getBalance().get(i - 1).getName();
	                String onlineStatus = p != null && UserBalanceManager.getBalance().get(i - 1).isOnline() ? "§a•" : "§c•";
	                String formattedBalance = "§f" + ChatUtil.formatAmount(UserBalanceManager.getBalance().get(i - 1).getBalance());
	             
	                return s + onlineStatus + " §7" + playerName + " " + formattedBalance;
	            }
	            }
	
	            return "§7" + i + ".";
	        }
	
	        public static void toggleCategory() {
	            category = (category < 4) ? category + 1 : 1;

	        
	            UserRankingManager.sortUserRankings();
	          	 UserRankingManager.sortGuildRankings();
                UserKillManager.sortUserKills();
                UserDeathManager.sortUserDeaths();
                UserBalanceManager.sortUserBalance();
	        }
	        
			/*
			 * public static void toggleCategory() { UserRankingManager.sortUserRankings();
			 * UserRankingManager.sortGuildRankings(); UserKillManager.sortUserKills();
			 * UserDeathManager.sortUserDeaths(); UserBalanceManager.sortUserBalance();
			 * 
			 * category = (category < 4) ? category + 1 : 1; }
			 */

	        

	        public static void setCategory(int newCategory) {
	            if (newCategory >= 1 && newCategory <= 4) {
	                category = newCategory;
	                UserRankingManager.sortUserRankings();
	            	 UserRankingManager.sortGuildRankings();
	                UserKillManager.sortUserKills();
	                UserDeathManager.sortUserDeaths();
	                UserBalanceManager.sortUserBalance();
	            }
	        }


	        public static String getCategoryText() {
	            String[] categoryNames = {"PUNKTY", "ZABOJSTWA", "SMIERCI", "MONETY"};
	            int index = category - 1; // -1, ponieważ kategorie są od 1 do 4
	            return "&8[&r" + (index >= 0 && index < categoryNames.length ? categoryNames[index] : "PUNKTY") + "&8]";
	        }

	    
		
		
    public String getTopsPointsold(final Player p, int i ) {
        if (UserRankingManager.getRankings().size() >= i) {
            String s = "§7" + i + ". §c";
            if (i > 15) {
                s = "§7" + i + ". §c";
            }

            String playerName = UserRankingManager.getRankings().get(i - 1).getName();
            String onlineStatus = p != null && UserRankingManager.getRankings().get(i - 1).isOnline() ? "§a•" : "§c•";
            String points = "§f" + UserRankingManager.getRankings().get(i - 1).getPoints();

            return s + onlineStatus + " §7" + playerName + " " + points;
        }
        return "§7" + i + ".";
    }

    
    
    public static String getTopsClanPoints(final Player player,  int i ) {
		if (UserRankingManager.getGuildRankings().size() >= i) {
			String s = "&7" + i + ". &c";
			if (i > 15) {
				s = "&7" + i + ". &c";
			}
			return ChatUtil.fixColor(s
			+ "&c" + UserRankingManager.getGuildRankings().get(i - 1).getTag()
			+ " &f" + UserRankingManager.getGuildRankings().get(i - 1).getPoints() + "");
		}
		return "&7" + i + ".";
	}
    


    

    
    
    public static String getFormattedTag(final Clans g) {
        String formattedTag = g.getTag(); // Pobierz tag gildii
        if (g.isColorTag() != null) {
            String colorTag = g.isColorTag();
            try {
                ChatColor tagColor = ChatColor.of(colorTag);
                formattedTag = tagColor + "&l" + formattedTag;
            } catch (IllegalArgumentException e) {
                // Obsłuż błąd, jeśli przekazany kolor nie jest prawidłowy
            }
        }

        return formattedTag;
    }
}