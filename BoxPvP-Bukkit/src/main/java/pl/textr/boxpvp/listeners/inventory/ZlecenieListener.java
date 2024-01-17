package pl.textr.boxpvp.listeners.inventory;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import pl.textr.boxpvp.commands.User.RepairCommand;
import pl.textr.boxpvp.utils.ChatUtil;


public class ZlecenieListener implements Listener {


    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onClickUstawienia(final InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        final UserProfile u = UserAccountManager.getUser(p);
        if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.translateHexColorCodes("&#2cfe4e&lZleceniodawca"))) {
            e.setCancelled(true);

        }
    }
}