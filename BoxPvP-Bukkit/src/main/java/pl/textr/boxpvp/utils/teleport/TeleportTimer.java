package pl.textr.boxpvp.utils.teleport;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.listeners.player.TeleportPlayerManagerListener;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.TimeUtil;


public class TeleportTimer
{
	public static HashMap<Player, Long> teleporting = new HashMap<>();
	private static Location SPAWN_LOCATION;

 
    
    public static void teleport(final Player p, final int delay) {
        final UserProfile u = UserAccountManager.getUser(p);
      
        if (p.hasPermission("core.cmd.helper")) {
            if (SPAWN_LOCATION == null) {
            SPAWN_LOCATION = Main.getPlugin().getConfiguration().getSpawnLocation();     
            }
            p.teleport(SPAWN_LOCATION);
            p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            p.setFoodLevel(20);
            ChatUtil.sendMessage(p, "&7Przeteleportowano na &fspawn");
            
        } else {
            TeleportTimer.teleporting.put(p, System.currentTimeMillis() + TimeUtil.SECOND.getTime(delay));
            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 1, true));
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1, true));
           
            TeleportPlayerManagerListener.addTask(p, u, new TeleportCallback<Player>() {
             
            	
            	@Override
                public void success(final Player player) {
                    if (SPAWN_LOCATION == null) {
                    SPAWN_LOCATION = Main.getPlugin().getConfiguration().getSpawnLocation();    
                    }
                    p.teleport(SPAWN_LOCATION);
                    for (int i = 0; i < 10; ++i) {
                        p.spawnParticle(Particle.END_ROD, p.getLocation().add(0.0, i, 0.0), 3, 0.0, 0.0, 0.0);
                    }
                    player.removePotionEffect(PotionEffectType.CONFUSION);
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                    ChatUtil.sendMessage(player, "&7Przeteleportowano na &fspawn");
                    TeleportTimer.teleporting.remove(p);
                }

                @Override
                public void error(final Player player) {
                    TeleportTimer.teleporting.remove(p);
                    player.removePotionEffect(PotionEffectType.CONFUSION);
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                    ChatUtil.sendMessage(p, "&8[&c&l!&8] &cTeleportacja przerwana!");
                }
            }, delay);
        }
    }
}