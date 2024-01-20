	package api.managers;
	
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.concurrent.ConcurrentHashMap;
	
	import org.bukkit.Bukkit;
	import org.bukkit.entity.Player;

import api.data.Clans;
import api.rankings.UserRankingManager;
import pl.textr.boxpvp.Main;
	import pl.textr.boxpvp.utils.ChatUtil;
	import pl.textr.boxpvp.utils.DataUtil;
	
	public class ClanManager
	{
	
	    private static final ConcurrentHashMap<String, Clans> clans;
	    static {
	        clans = new ConcurrentHashMap<>();
	    }
	    
	    public static ConcurrentHashMap<String, Clans> getGuilds() {
	        return ClanManager.clans;
	    }
	    
		   public static Clans getGuild(final Player p) {
		        for (final Clans g : ClanManager.clans.values()) {
		            if (g.isMember(p)) {
		                return g;
		            }
		        }
		        return null;
		    }
		    
		    public static Clans getGuildByNamePlayer(final String name) {
		        for (final Clans g : ClanManager.clans.values()) {
		            if (g.isMember(name)) {
		                return g;
		            }
		        }
		        return null;
		    }

	    
	    public static Clans getGuild(final String name) {
	        for (final Clans g : ClanManager.clans.values()) {
	            if (g.getName().equalsIgnoreCase(name) || g.getTag().equalsIgnoreCase(name)) {
	                return g;
	            }
	        }
	        return null;
	    }
	
	
	    //pobiera gildie
	    public static void downloadGuild (final String tag) {
	    	
	    	try (Connection connection = Main.getPlugin().getHikari().getConnection();
	    	         PreparedStatement statement = connection.prepareStatement("SELECT tag, name, owner, createtime, colortag, pvp, points FROM clans WHERE tag = ?")) {
	    	    statement.setString(1, tag);
	    	    ResultSet rs = statement.executeQuery();
	    	    
	    	    if (rs.next()) {
	    	        final Clans g = new Clans(rs);
	    	        g.setTag(rs.getString("tag"));
	    	        g.setName(rs.getString("name"));
	    	        g.setOwner(rs.getString("owner"));
	                g.setCreateTime(rs.getLong("createtime"));
	                g.setColorTag(rs.getString("colortag"));
	    	        g.setPvp(rs.getInt("pvp") == 1);
	    	        g.setPoints(rs.getInt("points"));    
	    	           final Clans guild2 = ClanManager.getGuild(tag);
	                   if (Main.getPlugin().getConfiguration().debug) {
	                 	   Bukkit.getLogger().info("pomyslnie pobrano klan " + tag );
	                    }
	                  if (guild2 == null) { 
	                   	if (Main.getPlugin().getConfiguration().debug) {
	                   	 Bukkit.getLogger().info("pomyslnie utworzono klan " + tag );               	 
	                        }
	                   	   ClanManager.clans.put(g.getTag(), g);                
	                          UserRankingManager.addRanking(g);                  
	                 }                       
	               }
	               rs.close();         
	           }
	           catch (SQLException e) {
	               e.printStackTrace();
	           }
	       }
	       
	
	    
	  
	  
	    public static void guildInfo(final Player player, final String tag) {
	        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), new Runnable() {
	            @Override
	            public void run() {
	                try (Connection connection = Main.getPlugin().getHikari().getConnection();
	                     PreparedStatement select = connection.prepareStatement("SELECT tag, name, owner, createtime, colortag, pvp, points FROM clans WHERE tag = ?")) {
	                    select.setString(1, tag);
	                    ResultSet rs = select.executeQuery();
	                    if (rs.next()) {
	                        final Clans g = new Clans(rs);
	                        g.setTag(rs.getString("tag"));
	                        g.setName(rs.getString("name"));
	                        g.setOwner(rs.getString("owner"));
	                        g.setCreateTime(rs.getLong("createtime"));
	                        g.setColorTag(rs.getString("colortag"));
	                        g.setPvp(rs.getInt("pvp") == 1);
	                        g.setPoints(rs.getInt("points"));
	                        
	                        Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
	                            @Override
	                            public void run() {
	                                ChatUtil.sendMessage(player, "&8&m----------------------------------");
	                                ChatUtil.sendMessage(player, "&7» &7Klan: &8[&r" + g.getTag() + "&8] &r" + g.getName());
	                                ChatUtil.sendMessage(player, "&7» &7Lider: &r" + g.getOwner());
	                                ChatUtil.sendMessage(player, "&7» &7Zalozno: &r" + DataUtil.getDate(g.getCreateTime()));
	                                ChatUtil.sendMessage(player, "&7» &7Punkty: &r" + g.getPoints());
	                                ChatUtil.sendMessage(player, "&7» &7Czlonkow: &r" + g.getMembers().size() + "&7/&r30");
	                                ChatUtil.sendMessage(player, "&7» &7online:&r " + g.getOnlineMembers().size());
	                                ChatUtil.sendMessage(player, "&8&m----------------------------------");
	                            }
	                        });
	                    } else {
	                        if (Main.getPlugin().getConfiguration().debug) {                                     
	                        	  Bukkit.getLogger().warning("Nie odnaleziono gklanu " + tag);
	                        }
	                    }
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	
	
	
	       //aktualizuje informacje o gildii
	    public static void downloadGuildInformation(final String tag) {
	        final Clans g = ClanManager.getGuild(tag);
	        try (Connection connection = Main.getPlugin().getHikari().getConnection();
	                PreparedStatement select = connection.prepareStatement("SELECT tag, name, owner, createtime, colortag, pvp, points FROM clans WHERE tag = ?")) 
	        {	
	        	select.setString(1, tag);
	
	            ResultSet rs = select.executeQuery();
	            if (rs.next()) {
	                if (Main.getPlugin().getConfiguration().debug) {
	                	 Bukkit.getLogger().info("zaktualizowano pomyslnie wartosci klanu " + tag);      	
	                }
	                g.setTag(rs.getString("tag"));
	                g.setName(rs.getString("name"));
	                g.setOwner(rs.getString("owner"));
	                g.setCreateTime(rs.getLong("createtime"));
	                g.setColorTag(rs.getString("colortag"));
	                g.setPvp(rs.getInt("pvp") == 1);
	                g.setPoints(rs.getInt("points"));
	            } else {
	                if (Main.getPlugin().getConfiguration().debug) {
	                	  Bukkit.getLogger().warning("Nie odnaleziono Klanu " + tag);
	                }
	                rs.close();
	            }
	       
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }	            
	    }
	
	
	    
		public static Clans createGuild(final String tag, final String name, final Player owner) {
	        final Clans g = new Clans(tag, name, owner);
	        ClanManager.clans.put(tag, g);
	        UserRankingManager.addRanking(g);
	 
	   return g;
	    }
	
	    
		public static void deleteGuild(final Clans g) {
		    getGuilds().remove(g.getTag());
		    UserRankingManager.removeRanking(g);
		    
		    try (Connection connection = Main.getPlugin().getHikari().getConnection();
		         PreparedStatement deleteGuildStatement = connection.prepareStatement("DELETE FROM clans WHERE tag = ?");
		         PreparedStatement deleteMembersStatement = connection.prepareStatement("DELETE FROM members WHERE tag = ?")) {
		         deleteGuildStatement.setString(1, g.getTag());
		         deleteGuildStatement.executeUpdate();
		         deleteMembersStatement.setString(1, g.getTag());
		         deleteMembersStatement.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	
	    
	    public static void loadGuilds() {
	        try (Connection connection = Main.getPlugin().getHikari().getConnection();
	        		PreparedStatement statement = connection.prepareStatement("SELECT * FROM clans");
	             ResultSet rs = statement.executeQuery()) {
	
	            while (rs.next()) {
	                final Clans g = new Clans(rs);
	                ClanManager.clans.put(g.getTag(), g);
	                UserRankingManager.addRanking(g);
	            }
	            Bukkit.getLogger().info("Loaded " + ClanManager.clans.size() + " Klans");
	        } catch (SQLException e) {
	        	  Bukkit.getLogger().warning("Can not load Klan ERROR: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	
	
	 
		
	    
	}
