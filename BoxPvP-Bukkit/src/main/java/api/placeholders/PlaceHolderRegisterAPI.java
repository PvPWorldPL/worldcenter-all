package api.placeholders;
import org.bukkit.entity.Player;

import api.data.Clans;
import api.data.UserProfile;
import api.managers.ClanManager;
import api.managers.UserAccountManager;
import api.rankings.UserRankingManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.md_5.bungee.api.ChatColor;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.DataUtil;

public class PlaceHolderRegisterAPI extends PlaceholderExpansion {

	 

	  
	
	// Ta metoda jest wywoływana, gdy PlaceholderAPI potrzebuje zaktualizować wartość placeholdera
	@Override
	public String onPlaceholderRequest(Player player, String identifier) {
		
	    Clans g = ClanManager.getGuild(player);
	    
	    // Pobieranie informacji o obciążeniu CPU
	    if (identifier.equalsIgnoreCase("cpu")) {
	        double load = PlayerTabInfoUtil.getLoad();
	        ChatColor color = PlayerTabInfoUtil.getColorByLoad(load);
	        return color + String.format("%.2f", load) + "&7%";
	    }
	    
	    // Sprawdzanie, czy kolorowy nick jest włączony czy wyłączony
	    if (identifier.equalsIgnoreCase("kolorowynick")) {
	        final UserProfile u = UserAccountManager.getUser(player);
	        return (u.isTeczowy() ? "&awlaczony" : "&cwylaczony");
	    }
	    
	    // Pobieranie pozycji gracza w rankingu
	    if (identifier.equalsIgnoreCase("position")) {
	        final UserProfile u = UserAccountManager.getUser(player);
	        return (u == null) ? "" : ("" + UserRankingManager.getPlaceUser(u.getName()));
	    }	
	    // Pobieranie pozycji gracza w rankingu
	    if (identifier.equalsIgnoreCase("totalclans")) {
	    	final int total = ClanManager.getGuilds().size();
	        return Integer.toString(total);
	    }	
	    
	    if (identifier.equalsIgnoreCase("totaluser")) {
	        final int total = UserAccountManager.getUsers().size();
	        return Integer.toString(total);
	    }	
	    
		 // Zmienne player		
    
	    if (identifier.equalsIgnoreCase("points")) {
	        // Pobieranie punktów użytkownika
	        UserProfile u = UserAccountManager.getUser(player);
	        int points = u.getPoints();
	        return String.valueOf(points);
	    }
	    if (identifier.equalsIgnoreCase("balance")) {
	        // Pobieranie salda użytkownika
	        final UserProfile user = UserAccountManager.getUser(player);
	        return ChatUtil.formatAmount(user.getBalance());
	    }
	    if (identifier.equalsIgnoreCase("money")) {
	        // Pobieranie ilości pieniędzy użytkownika
	        final UserProfile user = UserAccountManager.getUser(player);
	        return String.valueOf(user.getMoney());
	    }
	    if (identifier.equalsIgnoreCase("deaths")) {
	        // Pobieranie ilości śmierci użytkownika
	        UserProfile u = UserAccountManager.getUser(player);
	        int deaths = u.getDeaths();
	        return String.valueOf(deaths);
	    }  
	    if (identifier.equalsIgnoreCase("kills")) {
	        // Pobieranie ilości zabójstw użytkownika
	        UserProfile u = UserAccountManager.getUser(player);
	        int kills = u.getKills();
	        return String.valueOf(kills);
	    }
	    
	    //klan
	    
	    if (identifier.equalsIgnoreCase("clansposition")) {
	
	        return (g == null) ? "&cBrak klanu" : ("#" + UserRankingManager.getPlaceGuild(g));
	    }
	    
	    if (identifier.equalsIgnoreCase("clanspoints")) {
	        // Pobieranie punktów gildii
	        int gpoints = (g != null) ? g.getPoints() : 0;
	        return (gpoints > 0) ? "&e* &7Punkty: &e" + gpoints : "";
	    }
	    if (identifier.equalsIgnoreCase("clanspointstab")) {
	        // Pobieranie punktów gildii   
	        return (g == null) ? "&cBrak klanu" : ("" + g.getPoints());
	    }
	    if (identifier.equalsIgnoreCase("clansmembers")) {
	        // Pobieranie informacji o ilości członków gildii online i ogółem
	        return (g == null) ? "&cBrak klanu" : ("" + g.getOnlineMembers().size() + "&7/&a" + g.getMembers().size());
	    }
	    if (identifier.equalsIgnoreCase("clansonline")) {
	        // Pobieranie informacji o ilości członków gildii online i ogółem
	        return (g == null) ? "&cBrak klanu" : ("" + g.getOnlineMembers().size());
	    }
	    if (identifier.equalsIgnoreCase("clansowner")) {
	        // Pobieranie właściciela gildii
	        return (g == null) ? "&cBrak klanu" : ("" + g.getOwner());
	    }
	    if (identifier.equalsIgnoreCase("clanscreate")) {
	        // Pobieranie właściciela gildii
	        return (g == null) ? "&cBrak klanu" : ("" + DataUtil.getDate(g.getCreateTime()));
	    }
	    //tekst kategori
	    if (identifier.equalsIgnoreCase("changetextcategory")) {
            return PlayerTabInfoUtil.getCategoryText();
        }
	    //tagi
	    if (identifier.equalsIgnoreCase("nametag")) {
	        // Pobieranie sformatowanego tagu gildii
	        return (g != null) ? PlayerTabInfoUtil.getFormattedTag(g) : "";
	    }
	    if (identifier.equalsIgnoreCase("tag")) {
	        // Pobieranie sformatowanego tagu gildii
	        return (g != null) ? PlayerTabInfoUtil.getFormattedTag(g) : "&cBrak klanu";
	    }
	    //topki
	    if (identifier.startsWith("top_")) {
	        // Pobieranie punktów gracza z rankingu
	        String positionStr = identifier.replace("top_", "");
	        int position = Integer.parseInt(positionStr);
	        

	        String topPointsInfo = PlayerTabInfoUtil.getTopPoints(player, position);

	
	        return topPointsInfo;
	        
	    }
	
	    if (identifier.startsWith("clantop_")) {
	        // Pobieranie punktów gildii z rankingu
	        String positionStr = identifier.replace("clantop_", "");
	        int position = Integer.parseInt(positionStr);
	        return PlayerTabInfoUtil.getTopsClanPoints(player, position);
	    }
	
	    return null;
	}

	  

    
	    
    public boolean persist() {
        return true;
      }
      
      public boolean canRegister() {
        return true;
      }
      

    @Override
    public String getIdentifier() {
        return "BoxPvP";
    }


    @Override
    public String getAuthor() {
        return Main.getPlugin().getDescription().getAuthors().toString();
    }


    @Override
    public String getVersion() {
        return Main.getPlugin().getDescription().getVersion();
    }
}
