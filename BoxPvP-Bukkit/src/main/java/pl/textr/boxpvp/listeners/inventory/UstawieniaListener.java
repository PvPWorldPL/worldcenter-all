package pl.textr.boxpvp.listeners.inventory;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import api.menu.UstawieniaMenu;
import org.bukkit.inventory.ItemStack;
import pl.textr.boxpvp.utils.ChatUtil;

public class UstawieniaListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onClickUstawienia(final InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        final UserProfile u = UserAccountManager.getUser(p);

        if (e.getView().getTitle().equals(ChatUtil.translateHexColorCodes("&#2cfe4e&lU&#43f64b&lS&#5aef49&lT&#72e746&lA&#89df43&lW&#a0d841&lI&#b7d03e&lE&#cfc83b&lN&#e6c139&lI&#fdb936&lA"))) {
            e.setCancelled(true);

            int slot = e.getSlot();
            ItemStack item = e.getCurrentItem();

            if (item != null) {
                switch (item.getType()) {
                    case SUNFLOWER:
                        u.setPrzerabianieKasy(!u.isPrzerabianieKasy());
                        u.save();
                        p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
                        UstawieniaMenu.show(p);
                        break;
                    case MAGMA_CREAM:
                        u.setPrzerabianieMonet(!u.isPrzerabianieMonet());
                        u.save();
                        p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
                        UstawieniaMenu.show(p);
                        break;
                    case DEEPSLATE_EMERALD_ORE:
                        u.setPrzerabianieBlokow(!u.isPrzerabianieBlokow());
                        u.save();
                        p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1.0f, 1.0f);
                        UstawieniaMenu.show(p);
                        break;
                }
            }
        }
    }
}
