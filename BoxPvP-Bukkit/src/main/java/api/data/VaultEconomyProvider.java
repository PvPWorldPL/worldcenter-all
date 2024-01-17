package api.data;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.bukkit.OfflinePlayer;

import api.managers.UserAccountManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;


public final class VaultEconomyProvider
  implements Economy
{
  public boolean isEnabled() {
    return false;
  }

  
  public String getName() {
    return null;
  }

  
  public boolean hasBankSupport() {
    return false;
  }

  
  public int fractionalDigits() {
    return -1;
  }

  
  public String format(double v) {
    return (new BigDecimal(v)).setScale(2, RoundingMode.CEILING).toString();
  }

  
  public String currencyNamePlural() {
    return currencyNameSingular();
  }

  
  public String currencyNameSingular() {
    return "$";
  }

  
  public boolean hasAccount(String s) {
    return false;
  }

  
  public boolean hasAccount(OfflinePlayer offlinePlayer) {
    return false;
  }

  
  public boolean hasAccount(String s, String s1) {
    return false;
  }

  
  public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
    return false;
  }


  
  public double getBalance(String s) {
	  UserProfile toolsPlayer = UserAccountManager.getUser(s);
    return toolsPlayer.getBalance().doubleValue();
  }


  
  public double getBalance(OfflinePlayer offlinePlayer) {
	  UserProfile toolsPlayer = UserAccountManager.getUser(offlinePlayer.getName());
    return toolsPlayer.getBalance().doubleValue();
  }


  
  public double getBalance(String s, String s1) {
    return getBalance(s);
  }

  
  public double getBalance(OfflinePlayer offlinePlayer, String s) {
    return getBalance(offlinePlayer);
  }


  
  public boolean has(String s, double v) {
	  UserProfile toolsPlayer = UserAccountManager.getUser(s);
    return toolsPlayer.hasEnough(BigDecimal.valueOf(v));
  }



  
  public boolean has(OfflinePlayer offlinePlayer, double v) {
	  UserProfile toolsPlayer = UserAccountManager.getUser(offlinePlayer.getName());
    return toolsPlayer.hasEnough(BigDecimal.valueOf(v));
  }


  
  public boolean has(String s, String s1, double v) {
    return has(s, v);
  }

  
  public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
    return has(offlinePlayer, v);
  }


  
  public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
    if (offlinePlayer == null) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "OfflinePlayer cannot be null!");
    }
    if (amount < 0.0D) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Nie mozna wyplacic ujemnej kwoty.");
    }
    
    UserProfile toolsPlayer = UserAccountManager.getUser(offlinePlayer.getName());
    if (toolsPlayer == null) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Ten gracz nie istnieje w bazie danych!");
    }
    
    toolsPlayer.removeBalance(amount);
    
    return new EconomyResponse(amount, toolsPlayer.getBalance().doubleValue(), EconomyResponse.ResponseType.SUCCESS, null);
  }

  
  public EconomyResponse withdrawPlayer(String playerName, double amount) {
    if (playerName == null) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Nazwa gracza nie moze byc nullem");
    }
    if (amount < 0.0D) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Nie mozna wyplacic ujemnej kwoty");
    }
    
    UserProfile toolsPlayer = UserAccountManager.getUser(playerName);
    if (toolsPlayer == null) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Ten gracz nie istnieje w bazie danych!");
    }
    
    BigDecimal result = toolsPlayer.getBalance().subtract(BigDecimal.valueOf(amount), MathContext.DECIMAL128);
    toolsPlayer.setBalance(result);
    
    return new EconomyResponse(amount, toolsPlayer.getBalance().doubleValue(), EconomyResponse.ResponseType.SUCCESS, null);
  }

  
  public EconomyResponse withdrawPlayer(String s, String s1, double v) {
    return withdrawPlayer(s, v);
  }

  
  public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
    return withdrawPlayer(offlinePlayer, v);
  }

  
  public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
    if (offlinePlayer == null) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "OfflinePlayer cannot be null.");
    }
    if (amount < 0.0D) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Nie mozna wyplacic ujemnej kwoty");
    }
    
    UserProfile toolsPlayer = UserAccountManager.getUser(offlinePlayer.getName());
    if (toolsPlayer == null) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Ten gracz nie istnieje w bazie danych!");
    }
    
    toolsPlayer.addBalance(amount);
    
    return new EconomyResponse(amount, toolsPlayer.getBalance().doubleValue(), EconomyResponse.ResponseType.SUCCESS, null);
  }

  
  public EconomyResponse depositPlayer(String playerName, double amount) {
    if (playerName == null) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Nazwa gracza nie mobynullem.");
    }
    if (amount < 0.0D) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Nie mozna wyplacic ujemnej kwoty");
    }
    
    UserProfile toolsPlayer = UserAccountManager.getUser(playerName);
    if (toolsPlayer == null) {
      return new EconomyResponse(0.0D, 0.0D, EconomyResponse.ResponseType.FAILURE, "Ten gracz nie istnieje w bazie danych!");
    }
    
    toolsPlayer.addBalance(amount);
    
    return new EconomyResponse(amount, toolsPlayer.getBalance().doubleValue(), EconomyResponse.ResponseType.SUCCESS, null);
  }

  
  public EconomyResponse depositPlayer(String s, String s1, double v) {
    return depositPlayer(s, v);
  }

  
  public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
    return depositPlayer(offlinePlayer, v);
  }

  
  public EconomyResponse createBank(String s, String s1) {
    return null;
  }

  
  public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
    return null;
  }

  
  public EconomyResponse deleteBank(String s) {
    return null;
  }

  
  public EconomyResponse bankBalance(String s) {
    return null;
  }

  
  public EconomyResponse bankHas(String s, double v) {
    return null;
  }

  
  public EconomyResponse bankWithdraw(String s, double v) {
    return null;
  }

  
  public EconomyResponse bankDeposit(String s, double v) {
    return null;
  }

  
  public EconomyResponse isBankOwner(String s, String s1) {
    return null;
  }

  
  public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
    return null;
  }

  
  public EconomyResponse isBankMember(String s, String s1) {
    return null;
  }

  
  public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
    return null;
  }

  
  public List<String> getBanks() {
    return null;
  }

  
  public boolean createPlayerAccount(String s) {
    return false;
  }

  
  public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
    return false;
  }

  
  public boolean createPlayerAccount(String s, String s1) {
    return false;
  }

  
  public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
    return false;
  }
}
