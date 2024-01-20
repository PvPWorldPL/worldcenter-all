package api.rankings;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;

import api.data.UserProfile;

public class UserBalanceManager
{
	private static List<UserProfile> balance = new LinkedList<>();


    
 
    
    public static List<UserProfile> getBalance() {
        return UserBalanceManager.balance;
    }
    
  
    
    public static void addBalance(final UserProfile balance) {
    	UserBalanceManager.balance.add(balance);
    	sortUserBalance();
    }
    
 
    
    public static void removeBalance(final UserProfile balance) {
    	UserBalanceManager.balance.remove(balance);
    	sortUserBalance();
    }
    
 
    
    public static void sortUserBalance() {
        UserBalanceManager.balance.sort((g0, g1) -> {
            final BigDecimal p0 = g0.getBalance();
            final BigDecimal p2 = g1.getBalance();

            return p2.compareTo(p0); // Zamiana miejscami p0 i p2
        });
    }
 
    

    
    public static int getPlaceUserMoney(final UserProfile user) {
        for (int num = 0; num < UserBalanceManager.balance.size(); ++num) {
            if (UserBalanceManager.balance.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
}
