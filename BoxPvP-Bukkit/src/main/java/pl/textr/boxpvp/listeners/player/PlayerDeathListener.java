package pl.textr.boxpvp.listeners.player;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import api.data.Clans;
import api.data.UserProfile;
import api.managers.ClanManager;
import api.managers.UserAccountManager;
import api.redis.packet.broadcast.BroadcastDeathMessagePacket;
import api.redis.packet.guild.UpdateGuildInformationPacket;
import api.redis.packet.player.UpdatePlayerPacket;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;


public class PlayerDeathListener implements Listener {

    static LuckPerms api = LuckPermsProvider.get();
	private static Location SPAWN_LOCATION;


    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(final PlayerDeathEvent event) {
        event.setDeathMessage(null);
        final Player p = event.getEntity();
        final UserProfile user = UserAccountManager.getUser(p);
        final Player k = p.getKiller();
    
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
            p.setExp(0);
            user.addDeaths(1);
            user.save();
            for (PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
            }
            UpdatePlayerPacket updateVictimPlayerPacket = new UpdatePlayerPacket(user.getName());
            Main.getPlugin().getRedisService().publishAsync("UpdatePlayer", updateVictimPlayerPacket);
            if (p.isDead()) {

                p.spigot().respawn();
                   if (SPAWN_LOCATION == null) {
                        SPAWN_LOCATION = Main.getPlugin().getConfiguration().getSpawnLocation();
                        }
                        p.teleport(SPAWN_LOCATION);
                p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                p.setFoodLevel(20);

            }
        }, 10L);



		 if (k != null) {
		    if (user == null || p.equals(k)) {
		    return;
		     }

		    final UserProfile kUser = UserAccountManager.getUser(k);
			final UserProfile victim = UserAccountManager.getUser(p);

			 int initialPoints = (int) (43.0 + (kUser.getPoints() - user.getPoints()) * -0.25);

                // Dodawanie punktów
			 int addedPoints = Math.min(Math.max(initialPoints, 20), 450);

               // Usuwanie punktów
			 int removedPoints = Math.min(Math.max(addedPoints / 2, 10), 250);


			 //add message kuser // voucher give random
		     ChatUtil.sendTitle(k.getPlayer(), "", ChatUtil.fixColor("&7zabiles &f" +  victim.getName() + "&r" + " &8[&c+" + addedPoints + "&8]"));          
		     voucher(k);

			 //add statictic victim
			 victim.setPoints(victim.getPoints() - removedPoints);
		     victim.save();
		     //update victom
		     UpdatePlayerPacket UpdateVictimPlayerPacket;
		     UpdateVictimPlayerPacket = new UpdatePlayerPacket(victim.getName());
             Main.getPlugin().getRedisService().publishAsync("UpdatePlayer", UpdateVictimPlayerPacket);       
            //kuser add statistic

			 if (Main.getPlugin().getKillContractManager().handleKill(kUser.getPlayer(), victim.getPlayer())) {
				 // Zlecenie zabójstwa obsłużone, nie dodajemy zabójstwa
			 } else {
				 // Gracz nie ma aktywnego zlecenia, dodajemy zabójstwo na konto zabójcy
				 UserProfile userKiller = UserAccountManager.getUser(kUser.getPlayer());
					 userKiller.addKills(1);
			 }
			 kUser.setLastKill(victim.getName());
			 kUser.setPoints(kUser.getPoints() + addedPoints);
			 kUser.save();
			 //update killer
		     UpdatePlayerPacket UpdateKillerPlayerPacket;  
		     UpdateKillerPlayerPacket = new UpdatePlayerPacket(kUser.getName());
             Main.getPlugin().getRedisService().publishAsync("UpdatePlayer", UpdateKillerPlayerPacket);            
             //deathmessage
             BroadcastDeathMessagePacket BroadcastDeathMessagePacket;
 		     BroadcastDeathMessagePacket = new BroadcastDeathMessagePacket(p.getName(), kUser.getName(), addedPoints, removedPoints);
 			 Main.getPlugin().getRedisService().publishAsync("BroadcastDeathMessage", BroadcastDeathMessagePacket);										
 	
 			//check g killer null add points
 			final Clans g = ClanManager.getGuild(k);			
			if (g != null) {
				 g.setPoints(g.getPoints() + addedPoints);
			//update guild information killer
			UpdateGuildInformationPacket UpdateGuildInformationPacket;
			UpdateGuildInformationPacket = new UpdateGuildInformationPacket(g.getTag());
			Main.getPlugin().getRedisService().publishAsync("UpdateGuildInformation", UpdateGuildInformationPacket);	
			}
			 //check o  vistim null add points
			final Clans o = ClanManager.getGuild(p);			

			if (o != null) {
				 o.setPoints(o.getPoints() - removedPoints);
			//update guild information victim
			UpdateGuildInformationPacket UpdateGuildInformationPacket;
			UpdateGuildInformationPacket = new UpdateGuildInformationPacket(o.getTag());
			Main.getPlugin().getRedisService().publishAsync("UpdateGuildInformation", UpdateGuildInformationPacket);										
				}
		 }
			  
	}


    public void voucher(Player player) {
        if (Main.getPlugin().getConfiguration().vouchery > System.currentTimeMillis()) {
            Random random = new Random();
            double[] chances = {0.01, 0.02, 0.03}; // Szanse dla voucherów
            List<String> titles = Arrays.asList("&a&lVoucher Sponsor", "&6&lVoucher SVIP", "&e&lVoucher VIP");
            IntStream.range(0, chances.length)
                    .filter(i -> random.nextDouble() < chances[i])
                    .findFirst()
                    .ifPresent(index -> {
                        ItemStack voucher = new ItemBuilder(Material.PAPER, 1)
                                .setTitle(ChatUtil.fixColor(titles.get(index)))
                                .ToItemStack();
                        player.getInventory().addItem(voucher);
                    });
        }
    }
}
	
	
