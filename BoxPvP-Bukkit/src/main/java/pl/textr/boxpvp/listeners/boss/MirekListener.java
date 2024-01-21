package pl.textr.boxpvp.listeners.boss;

import api.managers.ItemsManager;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class MirekListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.ENDERMAN) {
            Zombie Zombie = (Zombie) event.getEntity();
            double health = Zombie.getHealth() - event.getDamage();
            String healthMessage = String.format("HP: %.2f", health);
            Zombie.setCustomName(healthMessage);
            Zombie.setCustomNameVisible(true);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntityType() == EntityType.ENDERMAN) {
            Zombie Zombie = (Zombie) event.getEntity();
            if (Zombie.hasMetadata("CustomZombie")) {
                dropItemOnEndermanKill(Zombie, event.getEntity().getKiller());
            }
        }
    }

    private void dropItemOnEndermanKill(Zombie enderman, Player killer) {
        List<MetadataValue> metadata = enderman.getMetadata("CustomZombie");
        for (MetadataValue value : metadata) {
            if (value.value() != null && value.value().equals(killer.getUniqueId().toString())) {
                killer.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(1)));
                break;
            }
        }
    }
}