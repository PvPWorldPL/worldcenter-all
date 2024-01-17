package pl.textr.boxpvp.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import api.managers.CooldownManager;

import java.util.Optional;

public class SniezkaListener  implements Listener {

    private static final String SNOWBALL_NAME = ChatColor.RED + "Magiczna Śnieżka";

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                player.getInventory().getItemInMainHand().getType().toString().contains("SNOWBALL") &&
                player.getInventory().getItemInMainHand().hasItemMeta() &&
                player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(SNOWBALL_NAME)) {
            event.setCancelled(true);
            Optional.of(player)
                    .filter(CooldownManager::canUse)
                    .ifPresent(p -> {
                        Snowball snowball = player.launchProjectile(Snowball.class);
                        snowball.setCustomName(SNOWBALL_NAME);
                        snowball.setShooter(player);
                     CooldownManager.setCooldownTime(30);

                    });
        } else {
            player.sendMessage(ChatColor.RED + "Musisz poczekać " + CooldownManager.getRemainingCooldown(player) + " sekund przed ponownym użyciem.");
        }
    }

//
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Optional.of(event)
                .filter(e -> e.getEntity() instanceof Snowball)
                .map(e -> (Snowball) e.getEntity())
                .filter(snowball -> snowball.getCustomName() != null && snowball.getCustomName().equals(SNOWBALL_NAME))
                .filter(snowball -> event.getHitEntity() instanceof Player)
                .ifPresent(snowball -> {
                    Player thrower = (Player) snowball.getShooter();
                    Player hitPlayer = (Player) event.getHitEntity();
                    // Zamień miejscami graczy
                    teleportPlayers(thrower, hitPlayer);
                    snowball.remove();
                });
    }


    private void teleportPlayers(Player player1, Player player2) {

        double x1 = player1.getLocation().getX();
        double y1 = player1.getLocation().getY();
        double z1 = player1.getLocation().getZ();

        double x2 = player2.getLocation().getX();
        double y2 = player2.getLocation().getY();
        double z2 = player2.getLocation().getZ();

        player1.teleport(player2.getLocation());
        player2.teleport(player1.getLocation());


        player1.getLocation().setX(x2);
        player1.getLocation().setY(y2);
        player1.getLocation().setZ(z2);

        player2.getLocation().setX(x1);
        player2.getLocation().setY(y1);
        player2.getLocation().setZ(z1);
    }

}
