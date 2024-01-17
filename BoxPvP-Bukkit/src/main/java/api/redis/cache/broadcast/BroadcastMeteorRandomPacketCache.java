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
		            Bukkit.broadcastMessage(ChatUtil.fixColor("&6&lWłaśnie spadł meteor na strefie PvP!"));
		            Bukkit.broadcastMessage(ChatUtil.fixColor(""));
		            Bukkit.broadcastMessage(ChatUtil.fixColor("&8- &7Współrzędne X: &f" + b.getLocation().getBlockX()));
		            Bukkit.broadcastMessage(ChatUtil.fixColor("&8- &7Współrzędne Z: &f" + b.getLocation().getBlockZ()));
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