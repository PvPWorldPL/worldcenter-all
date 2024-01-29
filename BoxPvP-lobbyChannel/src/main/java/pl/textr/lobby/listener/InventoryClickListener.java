package pl.textr.lobby.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import pl.textr.lobby.Lobby;
import pl.textr.lobby.ping.HexTransform;
import pl.textr.lobby.ping.ServerInfo;

import java.util.logging.Logger;

public class InventoryClickListener implements Listener
{
    public InventoryClickListener() {
        super();
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', Lobby.getInstance().cfg.getString("inventory.title")))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.getMaterial(Lobby.getInstance().cfg.getString("layouts.online.material"))) {
                for (ServerInfo servers : Lobby.getInstance().servers.values()) {
                    if (e.getSlot() == servers.getSlot()) {
                        if (servers.isOnline()) {
                            if (servers.getServerName().equalsIgnoreCase(Lobby.getInstance().currentServer)) {
                                p.closeInventory();
                                p.sendMessage(HexTransform.translateHexColorCodes("&cJestes juz polaczony z tym kanalem").replace("%server%", servers.getDisplayName()));
                                return;
                            } else {
                                p.closeInventory();
                                Lobby.getInstance().sendToServer(p, servers.getServerName());
                                p.sendMessage(HexTransform.translateHexColorCodes("&7Trwa laczenie do &f%server%").replace("%server%", servers.getDisplayName()));
                                return;
                            }
                        } else {
                            p.closeInventory();
                            p.sendMessage(HexTransform.translateHexColorCodes("&cKanal jest obecnie offline"));
                            return;
                        }
                    }
                }
            }
        }
    }
}