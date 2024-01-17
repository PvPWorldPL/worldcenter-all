package pl.textr.boxpvp.listeners.inventory;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import api.menu.UstawieniaMenu;
import pl.textr.boxpvp.utils.ChatUtil;

public class UstawieniaListener implements Listener {

    
@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
public void onClickUstawienia(final InventoryClickEvent e) {
    final Player p = (Player) e.getWhoClicked();
    final UserProfile u = UserAccountManager.getUser(p);
    if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.translateHexColorCodes("&#2cfe4e&lU&#43f64b&lS&#5aef49&lT&#72e746&lA&#89df43&lW&#a0d841&lI&#b7d03e&lE&#cfc83b&lN&#e6c139&lI&#fdb936&lA"))) {
        e.setCancelled(true);

        int slot = e.getSlot();
        if (slot == 24) {
            if (!u.isPrzerabianieKasy()) {
                u.setPrzerabianieKasy(true);
                u.save();
                p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f); 

                UstawieniaMenu.show(p);
            } else {
                u.setPrzerabianieKasy(false);
                u.save();
                p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);

                UstawieniaMenu.show(p);
                return;
           }     
        }
        if (slot == 40) {
            if (!u.isPrzerabianieMonet()) {
                u.setPrzerabianieMonet(true);
                u.save();
                p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);

                UstawieniaMenu.show(p);
            } else {
                u.setPrzerabianieMonet(false);
                u.save();
                p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);

                UstawieniaMenu.show(p);
                return;
            }
        }
        if (slot == 20) {
            if (!u.isPrzerabianieBlokow()) {
                u.setPrzerabianieBlokow(true);
                u.save();
                p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);

                UstawieniaMenu.show(p);
            } else {
                u.setPrzerabianieBlokow(false);
                u.save();
                p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);

                UstawieniaMenu.show(p);
            }
        }
    }
    
    
}
}