package api.data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import pl.textr.boxpvp.Main;

	public class UserProfile {
        private String name;
        private int money;
	    private BigDecimal balance;
	    private boolean teczowy;
	    private long lastChat;
	    private int points;
	    private int kills;
	    private int deaths;
	    private String lastKill;
	    private long lastKillTime;
	    private boolean PrzerabianieKasy;
	    private boolean PrzerabianieMonet;
	    private boolean PrzerabianieBlokow;
	    private boolean god;
	    private int perkZycia;	    
	    private int perkSzybkosci;    
	    private int perkSily;	    
	    private int perkWampiryzmu;    
	    private int perkSzybkosciAtaku;

        private int perkDropu;
        private boolean vanish;

    public UserProfile(final Player p) {
        this.name = p.getName();
        this.money = 0;
        this.balance = BigDecimal.valueOf(0.0);
        this.teczowy = false;
        this.points = 1000;
        this.kills = 0;
        this.deaths = 0;
        this.lastKill = "-";
        this.lastKillTime = 0L;
        this.lastChat = 0L;
        this.god = false;
        this.PrzerabianieKasy = false;
        this.PrzerabianieMonet = false;
        this.PrzerabianieBlokow = false;
        this.perkZycia = 0;
        this.perkSzybkosci = 0;
        this.perkSily = 0;
        this.perkWampiryzmu = 0;
        this.perkSzybkosciAtaku = 0;
        this.perkDropu = 0;
        this.vanish = false;
    }


    public UserProfile(final ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.money = rs.getInt("money");
        this.balance = rs.getBigDecimal("balance");
        this.teczowy = (rs.getInt("teczowy") == 1);
        this.points = 1000;
        this.kills = 0;
        this.deaths = 0;
        this.lastKill = rs.getString("lastKill");
        this.lastKillTime = rs.getLong("lastKillTime");
        this.lastChat = 0L;
        this.god = (rs.getInt("god") == 1);
        this.PrzerabianieKasy = (rs.getInt("PrzerabianieKasy") == 1);
        this.PrzerabianieMonet = (rs.getInt("PrzerabianieMonet") == 1);
        this.PrzerabianieBlokow = (rs.getInt("PrzerabianieBlokow") == 1);
        this.perkZycia = rs.getInt("perkZycia");
        this.perkSzybkosci = rs.getInt("perkSzybkosci");
        this.perkSily = rs.getInt("perkSily");
        this.perkWampiryzmu = rs.getInt("perkWampiryzmu");
        this.perkSzybkosciAtaku = rs.getInt("perkSzybkosciAtaku");
        this.perkSzybkosciAtaku = rs.getInt("perkDropu");
        this.vanish = (rs.getInt("vanish") == 1);
    }

    public void insert() {
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users (name, money, balance, teczowy, points, kills, deaths, lastKill, lastKillTime,  god, PrzerabianieKasy, PrzerabianieMonet, PrzerabianieBlokow,  perkZycia, perkSzybkosci, perkSily, perkWampiryzmu, perkSzybkosciAtaku, perkDropu, vanish) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)")) {
            statement.setString(1, this.getName());
            statement.setInt(2, this.getMoney());
            statement.setBigDecimal(3, this.getBalance());
            statement.setInt(4, this.isTeczowy() ? 1 : 0);
            statement.setInt(5, this.getPoints());
            statement.setInt(6, this.getKills());
            statement.setInt(7, this.getDeaths());
            statement.setString(8, this.getLastKill());
            statement.setLong(9, this.getLastKillTime());
            statement.setInt(10, this.isGod() ? 1 : 0);
            statement.setInt(11, this.isPrzerabianieKasy() ? 1 : 0);
            statement.setInt(12, this.isPrzerabianieMonet() ? 1 : 0);
            statement.setInt(13, this.isPrzerabianieBlokow() ? 1 : 0);
            statement.setInt(14, this.getPerkZycia());
            statement.setInt(15, this.getPerkSzybkosci());
            statement.setInt(16, this.getPerkSily());
            statement.setInt(17, this.getPerkWampiryzmu());
            statement.setInt(18, this.getPerkSzybkosciAtaku());
            statement.setInt(19, this.getPerkDropu());
            statement.setInt(20, this.isVanish() ? 1 : 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void setPerkZycia(Integer index) {
        this.perkZycia = index;
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkZycia = ? WHERE name = ?")) {           
            statement.setInt(1, this.getPerkZycia());
            statement.setString(2, getName());            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
    
    public void setPerkSzybkosci(Integer index) {
        this.perkSzybkosci = index;
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkSzybkosci = ? WHERE name = ?")) {           
            statement.setInt(1, this.getPerkSzybkosci());
            statement.setString(2, getName());         
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void setPerkSily(Integer index) {
        this.perkSily = index;
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkSily = ? WHERE name = ?")) {           
            statement.setInt(1, this.getPerkSily());
            statement.setString(2, getName());         
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void setPerkSzybkosciAtaku(Integer index) {
        this.perkSzybkosciAtaku = index;
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkSzybkosciAtaku = ? WHERE name = ?")) {           
            statement.setInt(1, this.getPerkSzybkosciAtaku());
            statement.setString(2, getName());         
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void setPerkDropu(Integer index) {
            this.perkDropu = index;
            try (Connection connection = Main.getPlugin().getHikari().getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkDropu = ? WHERE name = ?")) {
                statement.setInt(1, this.getPerkDropu());
                statement.setString(2, getName());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
    public void setPerkWampiryzmu(Integer index) {
        this.perkWampiryzmu = index;
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkWampiryzmu = ? WHERE name = ?")) {           
            statement.setInt(1, this.getPerkWampiryzmu());
            statement.setString(2, getName());         
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void setBalance(final BigDecimal index) {
   	 this.balance = index;
       try (Connection connection = Main.getPlugin().getHikari().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET balance=? WHERE name=?")) {
           statement.setBigDecimal(1, this.getBalance());
           statement.setString(2, this.getName());
           statement.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
   
   
   public void setMoney(int index) {
       this.money = index;
       try (Connection connection = Main.getPlugin().getHikari().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET money = ? WHERE name = ?")) {
           statement.setDouble(1, this.getMoney());
           statement.setString(2, this.getName());
           statement.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

   
   
   

  
    public void save() {
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET "
                     + "points = ?, "
                     + "kills = ?, "
                     + "money = ?, "
                     + "balance = ?, "                    
                     + "deaths = ?, "
                     + "lastKill = ?, "
                     + "lastKillTime = ?, "
                     + "god = ?, "
                     + "PrzerabianieKasy = ?, "
                     + "PrzerabianieMonet = ?, "
                     + "PrzerabianieBlokow = ?, "
                     + "PerkZycia = ?, "  
                     + "PerkSzybkosci = ?, "  
                     + "PerkSily = ?, "  
                     + "PerkWampiryzmu = ?, "  
                     + "PerkSzybkosciAtaku = ?, "
                     + "PerkDropu = ?, "
                     + "teczowy = ?, "
                     + "vanish = ? "
                     + "WHERE name = ?")) {
            statement.setInt(1, this.getPoints());
            statement.setInt(2, this.getKills());
            statement.setInt(3, this.getMoney());
            statement.setBigDecimal(4, this.getBalance());          
            statement.setInt(5, this.getDeaths());
            statement.setString(6, this.getLastKill());
            statement.setLong(7, this.getLastKillTime());
            statement.setInt(8, this.isGod() ? 1 : 0);
            statement.setInt(9, this.isPrzerabianieKasy() ? 1 : 0);
            statement.setInt(10, this.isPrzerabianieMonet() ? 1 : 0); 
            statement.setInt(11, this.isPrzerabianieBlokow() ? 1 : 0);  
            statement.setInt(12, this.getPerkZycia());
            statement.setInt(13, this.getPerkSzybkosci());
            statement.setInt(14, this.getPerkSily());
            statement.setInt(15, this.getPerkWampiryzmu());
            statement.setInt(16, this.getPerkSzybkosciAtaku());
            statement.setInt(17, this.getPerkDropu());
            statement.setInt(18, this.isTeczowy() ? 1 : 0);
            statement.setInt(19, this.isVanish() ? 1 : 0);
            statement.setString(20, this.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    
    
    
    public void addperkZycia() {
    	   this.perkZycia++;
    	     try (Connection connection = Main.getPlugin().getHikari().getConnection();
    	             PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkZycia = ? WHERE name = ?")) {           
    	            statement.setInt(1, this.getPerkZycia());
    	            statement.setString(2, getName());            
    	            statement.executeUpdate();
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }
    
    

    
    public void addperkSzybkosci() {
 	   this.perkSzybkosci++;
 	     try (Connection connection = Main.getPlugin().getHikari().getConnection();
 	             PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkSzybkosci = ? WHERE name = ?")) {           
 	            statement.setInt(1, this.getPerkSzybkosci());
 	            statement.setString(2, getName());            
 	            statement.executeUpdate();
 	        } catch (SQLException e) {
 	            e.printStackTrace();
 	        }
 	    }
    
    
    
    
    
    public void addperkWampiryzmu() {
        this.perkWampiryzmu++;
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkWampiryzmu = ? WHERE name = ?")) {
            statement.setInt(1, this.getPerkWampiryzmu());
            statement.setString(2, getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    public void addperkSily() {
        this.perkSily++;
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkSily = ? WHERE name = ?")) {
            statement.setInt(1, this.getPerkSily());
            statement.setString(2, getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void addperkSzybkosciAtaku() {
        this.perkSzybkosciAtaku++;
        try (Connection connection = Main.getPlugin().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkSzybkosciAtaku = ? WHERE name = ?")) {
            statement.setInt(1, this.getPerkSzybkosciAtaku());
            statement.setString(2, getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        public void addperkPerkDropu() {
            this.perkDropu++;
            try (Connection connection = Main.getPlugin().getHikari().getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE users SET perkDropu = ? WHERE name = ?")) {
                statement.setInt(1, this.getPerkDropu());
                statement.setString(2, getName());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    public boolean hasEnough(final BigDecimal amount) throws ArithmeticException {
        return amount.compareTo(this.balance) <= 0;
    }
    
    public void addBalance(final double amount) {
        this.setBalance(this.balance.add(BigDecimal.valueOf(amount)));
    }
    public void addMoney(final int amount) {
        this.money += amount;
    }
    
    public void removeBalance(final double amount) {
        this.setBalance(this.balance.subtract(BigDecimal.valueOf(amount)));
    }
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public int getMoney() {
        return this.money;
    }
    
   
    public int getPerkZycia() {
        return this.perkZycia;
      }
    
    public int getPerkSzybkosci() {
        return this.perkSzybkosci;
      }
    
    
    public int getPerkSily() {
        return this.perkSily;
    }
    
    public int getPerkWampiryzmu() {
        return this.perkWampiryzmu;
      }
    
    public int getPerkSzybkosciAtaku() {
        return this.perkSzybkosciAtaku;
      }

    public int getPerkDropu() {
        return this.perkDropu;
        }
    
    public String getLastKill() {
        return this.lastKill;
    }
    
    public void setLastKill(final String lastKill) {
        this.lastKill = lastKill;
    }
    
    public long getLastKillTime() {
        return this.lastKillTime;
    }
    
    public void setLastKillTime(final long lastKillTime) {
        this.lastKillTime = lastKillTime;
    }
  
    public int getPoints() {
        return this.points;
    }
    
    public void setPoints(final int points) {
        this.points = points;
    }
    
    public void addPoints(final int index) {
        this.points += index;
    }
    
    public void removePoints(final int index) {
        this.points -= index;
    }

        public int getKills() {
            return this.kills;
        }
    
    public void setKills(final int kills) {
        this.kills = kills;
    }
    
    public void addKills(final int index) {
        this.kills += index;
  
    }
    
    public void removeKills(final int index) {
        this.kills -= index;
    }
    
    public int getDeaths() {
        return this.deaths;
    }
    
    public void setDeaths(final int deaths) {
        this.deaths = deaths;
    }

    public void addDeaths(final int index) {
        this.deaths += index;
    }
     
    public void removeDeaths(final int index) {
        this.deaths -= index;
    }
       
    public boolean isPrzerabianieKasy() {
        return this.PrzerabianieKasy;
    }

    public void setPrzerabianieKasy(final boolean PrzerabianieKasy) {
        this.PrzerabianieKasy = PrzerabianieKasy;
    }
    
    
    public boolean isPrzerabianieMonet() {
        return this.PrzerabianieMonet;
    }

    public void setPrzerabianieMonet(final boolean PrzerabianieMonet) {
        this.PrzerabianieMonet = PrzerabianieMonet;
    }
    
    
    public boolean isPrzerabianieBlokow() {
        return this.PrzerabianieBlokow;
    }

    public void setPrzerabianieBlokow(final boolean PrzerabianieBlokow) {
        this.PrzerabianieBlokow = PrzerabianieBlokow;
    }

    public boolean isVanish() {
        return this.vanish;
    }

    public void setVanish(final boolean vanish) {
        this.vanish = vanish;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.getName());
    }

    public boolean isOnline() {
        return this.getPlayer() != null;
    }

    public boolean isChat() {
        return System.currentTimeMillis() > this.lastChat;
    }

    public void setLastChat(final long lastChat) {
        this.lastChat = lastChat;
    }

    public long getLastChat() {
        return this.lastChat;
    }

    public boolean isGod() {
        return this.god;
    }

    public void setGod(final boolean god) {
        this.god = god;
    }

    public boolean isTeczowy() {
        return this.teczowy;
    }

    public void setTeczowy(final boolean teczowy) {
        this.teczowy = teczowy;
    }
    
    public OfflinePlayer getOfflinePlayer() {
        if (this.getName() == null || this.getName().isEmpty()) {
            return null;
        }
        return Bukkit.getPlayerExact(this.getName());
    }

	}