	package api.redis.cache.broadcast;

	import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import api.redis.PacketListener;
import api.redis.packet.broadcast.BroadcastMeteorRandomPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.RandomUtil;


public class BroadcastMeteorRandomPacketCache implements PacketListener<BroadcastMeteorRandomPacket> {


		public void handle(BroadcastMeteorRandomPacket packet) {
		    Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
		        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		        RegionManager regionManager = container.get(BukkitAdapter.adapt(Bukkit.getWorld("pvp"))); 
		        ProtectedRegion region = regionManager.getRegion("pvp");  

		        if (region != null) {
		            // Ogranicz losowanie współrzędnych do obszaru regionu
		            BlockVector3 minPoint = region.getMinimumPoint();
		            BlockVector3 maxPoint = region.getMaximumPoint();

		            int x = RandomUtil.getRandInt(minPoint.getBlockX(), maxPoint.getBlockX());
		            int z = RandomUtil.getRandInt(minPoint.getBlockZ(), maxPoint.getBlockZ());
		            int y = RandomUtil.getRandInt(minPoint.getBlockY(), maxPoint.getBlockY());

		            Location loc = new Location(Bukkit.getWorld("pvp"), x, y, z);
		            Block b = loc.getBlock();
		            loc.setY(loc.getY() + 3.0D);
		            b.setType(Material.DRAGON_EGG);

		            b.getWorld().playSound(b.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
		            b.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, b.getLocation().add(0.5, 0.5, 0.5), 1);
		         
		            Bukkit.broadcastMessage(ChatUtil.fixColor(""));
		            Bukkit.broadcastMessage(ChatUtil.fixColor(""));
		            Bukkit.broadcastMessage(ChatUtil.fixColor("&x&0&0&0&0&0&0✖ &x&f&f&0&f&0&f&lᴡ&x&f&f&1&0&0&f&lʟ&x&f&f&1&2&1&0&lᴀ&x&f&f&1&3&1&0&ls&x&f&f&1&4&1&1&lɴ&x&f&f&1&6&1&1&lɪ&x&f&f&1&7&1&2&lᴇ &x&f&f&1&8&1&2&ls&x&f&f&1&a&1&3&lᴘ&x&f&f&1&b&1&3&lᴀ&x&f&f&1&c&1&4&lᴅ&x&f&f&1&d&1&4&lʟ &x&f&f&1&f&1&5&lᴍ&x&f&f&2&0&1&5&lᴇ&x&f&f&2&1&1&6&lᴛ&x&f&f&2&3&1&6&lᴇ&x&f&f&2&4&1&7&lᴏ&x&f&f&2&5&1&7&lʀ&x&f&f&2&7&1&7&lʏ&x&f&f&2&8&1&8&lᴛ &x&f&f&2&9&1&8&lɴ&x&f&f&2&b&1&9&lᴀ &x&f&f&2&c&1&9&ls&x&f&f&2&d&1&a&lᴛ&x&f&f&2&f&1&a&lʀ&x&f&f&3&0&1&b&lᴇ&x&f&f&3&1&1&b&lғ&x&f&f&3&2&1&c&lɪ&x&f&f&3&4&1&c&lᴇ &x&f&f&3&5&1&d&lᴘ&x&f&f&3&6&1&d&lᴠ&x&f&f&3&8&1&e&lᴘ&x&f&f&3&9&1&e&l!"));
		            Bukkit.broadcastMessage(ChatUtil.fixColor(""));
		            Bukkit.broadcastMessage(ChatUtil.fixColor("&8 ● &7ᴡsᴘᴏ́ᴌʀᴢᴇᴅɴᴇ x: &c" + b.getLocation().getBlockX()));
		            Bukkit.broadcastMessage(ChatUtil.fixColor("&8 ● &7ᴡsᴘᴏ́ᴌʀᴢᴇᴅɴᴇ ᴢ: &c" + b.getLocation().getBlockZ()));
					Bukkit.broadcastMessage(ChatUtil.fixColor("&8 ● &7ᴡsᴘᴏ́ᴌʀᴢᴇᴅɴᴇ ʏ: &c" + b.getLocation().getBlockY()));

					Bukkit.broadcastMessage(ChatUtil.fixColor(""));
		            Bukkit.broadcastMessage(ChatUtil.fixColor(""));
		        } else {
		          if (Main.getPlugin().getConfiguration().debug) {
		            Bukkit.getLogger().warning("Region o nazwie [pvp] nie istnieje.");
		        }
		        }
		    });
		}
	}
	

	
/*	public void handle(BroadcastMeteorRandomPacket packet) {
	    Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
	        int x = RandomUtil.getRandInt(-100, 100);
	        int z = RandomUtil.getRandInt(-100, 100);
	        int y = RandomUtil.getRandInt(-100, 100);

	        Location loc = new Location(Bukkit.getWorld("pvp"), x, y, z);
	        Block b = loc.getBlock();
	        loc.setY(loc.getY() + 3.0D);
	        b.setType(Material.DRAGON_EGG);

	        b.getWorld().playSound(b.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
	        b.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, b.getLocation().add(0.5, 0.5, 0.5), 1);
	     
	        Bukkit.broadcastMessage(ChatUtil.fixColor(""));
	        Bukkit.broadcastMessage(ChatUtil.fixColor(""));
	        Bukkit.broadcastMessage(ChatUtil.fixColor("&6&lWłasnie spadł meteor na strefie pvp!"));
	        Bukkit.broadcastMessage(ChatUtil.fixColor(""));
	        Bukkit.broadcastMessage(ChatUtil.fixColor("&8- &7Współrzędne X: &f" + b.getLocation().getBlockX()));
	        Bukkit.broadcastMessage(ChatUtil.fixColor("&8- &7Współrzędne Z: &f" + b.getLocation().getBlockZ()));
	        Bukkit.broadcastMessage(ChatUtil.fixColor(""));
	        Bukkit.broadcastMessage(ChatUtil.fixColor(""));
	    });
	}
}

*/