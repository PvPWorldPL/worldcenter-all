package pl.textr.boxpvp.listeners.boss;

import api.managers.ItemsManager;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
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
            Enderman enderman = (Enderman) event.getEntity();
            double health = enderman.getHealth() - event.getDamage();
            String healthMessage = String.format("HP: %.2f", health);
            enderman.setCustomName(healthMessage);
            enderman.setCustomNameVisible(true);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntityType() == EntityType.ENDERMAN) {
            Enderman enderman = (Enderman) event.getEntity();
            if (enderman.hasMetadata("CustomEnderman")) {
                dropItemOnEndermanKill(enderman, event.getEntity().getKiller());
            }
        }
    }

    private void dropItemOnEndermanKill(Enderman enderman, Player killer) {
        List<MetadataValue> metadata = enderman.getMetadata("CustomEnderman");
        for (MetadataValue value : metadata) {
            if (value.value() != null && value.value().equals(killer.getUniqueId().toString())) {
                killer.getInventory().addItem(new ItemStack(ItemsManager.getodlamek(1)));
                break;
            }
        }
    }
}