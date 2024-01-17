package api.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.textr.boxpvp.Main;

public class Clans
{
    private String tag;
    private  String name;
    private Set<String> members = new HashSet<>();
    private Set<Player> invites = new HashSet<>();
    private String owner;
    private boolean pvp;
    private long createTime;
    private String ColorTag;
    private int points;
    
    public Clans(final String tag, final String name, final Player owner) {
    	this.members = new HashSet<>();
    	this.invites = new HashSet<>();
        this.tag = tag;
        this.name = name;
        this.owner = owner.getName();   
        this.createTime = System.currentTimeMillis();
        this.ColorTag = "WHITE";
        this.pvp = false;
        this.addMember(owner.getName());
        this.points = 1000;
        this.insert();
    }
    
    public Clans(ResultSet rs) throws SQLException {
    	this.members = new HashSet<>();
    	this.invites = new HashSet<>();
        this.tag = rs.getString("tag");
        this.name = rs.getString("name");
        this.owner = rs.getString("owner");
        this.createTime = rs.getLong("createtime");
        this.ColorTag = rs.getString("colortag");
        this.pvp = (rs.getInt("pvp") == 1);
        this.points = rs.getInt("points");
        this.members();
    }
    
    

    
    public String getTag() {
        return this.tag;
    }
    
    
    public void setTag(final String tag) {
        this.tag = tag;
    }
    
    public String getName() {
        return this.name;
    }
     
    public long getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(final long createTime) {
        this.createTime = createTime;
    }  
    
    public boolean isMember(final String name) {
        return this.getMembers().contains(name);
    }
    
    public boolean isMember(final Player p) {
        return this.getMembers().contains(p.getName());
    }
    
    public boolean isOwner(final Player p) {
        return this.getOwner().equalsIgnoreCase(p.getName());
    }
    
    public boolean isOwner(final String p) {
        return this.getOwner().equalsIgnoreCase(p);
    }
  
    public void addPoints(final int index) {
        this.setPoints(this.getPoints() + index);
    }
    
    public void removePoints(final int index) {
        this.setPoints(this.getPoints() - index);
    }
    
    
    public Set<String> getMembers() {
        return this.members;
    }
    
    public Set<Player> getInvites() {
        return this.invites;
    }
    
    public String getOwner() {
        return this.owner;
    }
    
    public boolean isPvp() {
        return this.pvp;
    }
  
    public int getPoints() {
        return this.points;
    }
    
    public void setName(final String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.getName());
    }
 
    public String isColorTag() {
        return this.ColorTag;
    }

    
    
    
    
    
    
    public void members() {
        try (Connection connection = Main.getPlugin().getHikari().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT name FROM members WHERE tag = ?")) {
                statement.setString(1, this.tag);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        this.members.add(resultSet.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Obsługa błędów, można ją ulepszyć
        }
    }

    
    public void setColorTag(String colorTag) {
    	 this.ColorTag = colorTag;
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE clans SET colortag = ? WHERE tag = ?")) {
            statement.setString(1, this.ColorTag);
            statement.setString(2, this.getTag());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addMember(Player p) {
        this.members.add(p.getName());
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO members (name,tag) VALUES(?, ?)")) {
            statement.setString(1, p.getName());
            statement.setString(2, this.tag);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMember(String nick2) {
        this.members.add(nick2);
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO members (name,tag) VALUES(?, ?)")) {
            statement.setString(1, nick2);
            statement.setString(2, this.tag);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeMember(Player p) {
        this.members.remove(p.getName());
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM members WHERE name = ? AND tag = ?")) {
            statement.setString(1, p.getName());
            statement.setString(2, this.tag);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeMember(String p) {
        this.members.remove(p);
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM members WHERE name = ? AND tag = ?")) {
            statement.setString(1, p);
            statement.setString(2, this.tag);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setOwner(String owner) {
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE clans SET owner = ? WHERE tag = ?")) {
            statement.setString(1, owner);
            statement.setString(2, this.tag);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }       
  
    public void setPvp(boolean pvp) {
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE clans SET pvp = ? WHERE tag = ?")) {
            statement.setInt(1, pvp ? 1 : 0);
            statement.setString(2, this.tag);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void insert() {
    	 try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO clans (tag, name, owner, pvp, createtime, colortag, points) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, this.getTag());
            statement.setString(2, this.getName());
            statement.setString(3, this.getOwner());
            statement.setInt(4, this.isPvp() ? 1 : 0);
            statement.setLong(5, this.getCreateTime());
            statement.setString(6, this.isColorTag());
            statement.setInt(7, this.getPoints());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    
    public void setPoints(final int points) {
    	 this.points = points;
        String query = "UPDATE clans SET points = ? WHERE tag = ?";
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, this.getPoints());
            statement.setString(2, this.getTag());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
          
        }
    }

	
	    public Set<Player> getOnlineMembers() {
	        final Set<Player> online = new HashSet<>();
	        for (final Player p : Bukkit.getOnlinePlayers()) {
	            if (this.members.contains(p.getName())) {
	                online.add(p);
	            }
	        }
	        return online;
	    }

}
