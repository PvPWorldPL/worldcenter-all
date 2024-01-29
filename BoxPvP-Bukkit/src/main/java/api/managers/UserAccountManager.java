package api.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import api.data.UserProfile;
import api.rankings.UserBalanceManager;
import api.rankings.UserDeathManager;
import api.rankings.UserKillManager;
import api.rankings.UserRankingManager;
import pl.textr.boxpvp.Main;

public class UserAccountManager {

	// Mapa przechowująca użytkowników indeksowanych po nazwie
	public static final ConcurrentHashMap<String, UserProfile> users = new ConcurrentHashMap<>();

	// Lista przechowująca zalogowanych użytkowników

	public static ConcurrentHashMap<String, UserProfile> getUsers() {
		return UserAccountManager.users;
	}

	public static UserProfile getUser(final String name) {
		for (final UserProfile u : UserAccountManager.users.values()) {
			if (u.getName().equalsIgnoreCase(name)) {
				return u;
			}
		}
		return null;
	}

	public static UserProfile getUser(Player p) {
		if (users.containsKey(p.getName())) return users.get(p.getName());
		else return createUser(p);
	}

	public static UserProfile createUser(Player p) {
		UserProfile user = new UserProfile(p);
		users.put(p.getName(), user);
		return user;
	}


	// Pobieranie informacji o użytkowniku z bazy danych
	public static void downloadPlayerInfo(final Player p) {
		final UserProfile u = UserAccountManager.getUser(p);
		if (Main.getPlugin().getConfiguration().debug) {
			Bukkit.getLogger().info("ladowanie danych gracza " + p.getName());
		}
		Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				try (Connection connection = Main.getPlugin().getHikari().getConnection();
					 PreparedStatement select = connection.prepareStatement("SELECT money,  balance, teczowy,  points, kills, deaths, god, PrzerabianieKasy, PrzerabianieMonet, PrzerabianieBlokow, perkZycia, perkSzybkosci, perkSily, perkWampiryzmu, perkSzybkosciAtaku, perkDropu, vanish  FROM users WHERE name = ?")) {
					select.setString(1, p.getName());
					ResultSet rs = select.executeQuery();
					if (rs.next()) {
						if (Main.getPlugin().getConfiguration().debug) {
							Bukkit.getLogger().info("Znaleziono dane gracza.");
						}
						u.setMoney(rs.getInt("money"));

						u.setBalance(rs.getBigDecimal("balance"));
						u.setTeczowy(rs.getBoolean("teczowy"));
						u.setPoints(rs.getInt("points"));
						u.setKills(rs.getInt("kills"));
						u.setDeaths(rs.getInt("deaths"));
						u.setGod(rs.getBoolean("god"));
						u.setPrzerabianieKasy(rs.getBoolean("PrzerabianieKasy"));
						u.setPrzerabianieMonet(rs.getBoolean("PrzerabianieMonet"));
						u.setPrzerabianieBlokow(rs.getBoolean("PrzerabianieBlokow"));
						u.setPerkZycia(rs.getInt("perkZycia"));
						u.setPerkSzybkosci(rs.getInt("perkSzybkosci"));
						u.setPerkSily(rs.getInt("perkSily"));
						u.setPerkWampiryzmu(rs.getInt("perkWampiryzmu"));
						u.setPerkSzybkosciAtaku(rs.getInt("perkSzybkosciAtaku"));
						u.setPerkDropu(rs.getInt("perkDropu"));
						u.setVanish(rs.getBoolean("vanish"));

						rs.close();
					} else {
						if (Main.getPlugin().getConfiguration().debug) {
							Bukkit.getLogger().info("Utworzono " + p.getName());
						}
						u.insert();
						UserRankingManager.addRanking(u);
						UserKillManager.addKill(u);
						UserDeathManager.addDeath(u);
						UserBalanceManager.addBalance(u);

						p.getEnderChest().clear();
						p.getInventory().clear();

						Bukkit.getServer().getScheduler().runTask(Main.getPlugin(), () -> {
							Location location = Main.getPlugin().getConfiguration().getSpawnLocation();
							p.teleport(location);
						});
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Pobieranie informacji o użytkowniku z bazy danych
	public static void downloadPlayerInfo(final String p) {
		final Long startTime = System.currentTimeMillis();
		final UserProfile u = UserAccountManager.getUser(p);
		if (Main.getPlugin().getConfiguration().debug) {
			Bukkit.getLogger().info("ladowanie danych gracza " + p);
		}
		try (Connection connection = Main.getPlugin().getHikari().getConnection();
			 PreparedStatement select = connection.prepareStatement("SELECT money,  balance, teczowy, points, kills, deaths, god, PrzerabianieKasy, PrzerabianieMonet, PrzerabianieBlokow, perkZycia, perkSzybkosci, perkSily, perkWampiryzmu, perkSzybkosciAtaku, perkDropu, vanish FROM users WHERE name = ?")) {
			select.setString(1, p);
			ResultSet rs = select.executeQuery();
			if (rs.next()) {
				if (Main.getPlugin().getConfiguration().debug) {
					Bukkit.getLogger().info("Znaleziono dane gracza.");
				}
				u.setMoney(rs.getInt("money"));

				u.setBalance(rs.getBigDecimal("balance"));
				u.setTeczowy(rs.getBoolean("teczowy"));
				u.setPoints(rs.getInt("points"));
				u.setKills(rs.getInt("kills"));
				u.setDeaths(rs.getInt("deaths"));
				u.setGod(rs.getBoolean("god"));
				u.setPrzerabianieKasy(rs.getBoolean("PrzerabianieKasy"));
				u.setPrzerabianieMonet(rs.getBoolean("PrzerabianieMonet"));
				u.setPrzerabianieBlokow(rs.getBoolean("PrzerabianieBlokow"));
				u.setPerkZycia(rs.getInt("perkZycia"));
				u.setPerkSzybkosci(rs.getInt("perkSzybkosci"));
				u.setPerkSily(rs.getInt("perkSily"));
				u.setPerkWampiryzmu(rs.getInt("perkWampiryzmu"));
				u.setPerkSzybkosciAtaku(rs.getInt("perkSzybkosciAtaku"));
				u.setPerkDropu(rs.getInt("perkDropu"));
				u.setVanish(rs.getBoolean("vanish"));
				rs.close();
			}

			if (Main.getPlugin().getConfiguration().debug) {
				final Long startTime2 = System.currentTimeMillis() - startTime;
				Bukkit.getLogger().info("Done! " + startTime2 + "ms");
				Bukkit.getLogger().info("  ");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Wczytywanie użytkowników z bazy danych
	public static void loadUsers() {
		try (Connection connection = Main.getPlugin().getHikari().getConnection();
			 PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
			 ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				final UserProfile u = new UserProfile(rs);
				UserAccountManager.users.put(u.getName(), u);
				UserRankingManager.addRanking(u);
				UserKillManager.addKill(u);
				UserDeathManager.addDeath(u);
				UserBalanceManager.addBalance(u);


				UserRankingManager.sortUserRankings();
				UserKillManager.sortUserKills();
				UserDeathManager.sortUserDeaths();
				UserBalanceManager.sortUserBalance();
				UserRankingManager.sortGuildRankings();
			}

			Bukkit.getLogger().info("Loaded " + UserAccountManager.users.size() + " players");
		} catch (SQLException e) {
			Bukkit.getLogger().warning("Can not load players Error " + e.getMessage());
			e.printStackTrace();
		}
	}
}
