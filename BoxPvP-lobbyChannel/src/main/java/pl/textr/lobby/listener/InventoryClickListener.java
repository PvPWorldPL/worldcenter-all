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

public class InventoryClickListener implements Listener {
    public InventoryClickListener() {
        super();
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();

        // Sprawdzenie, czy kliknięty przedmiot istnieje
        if (e.getCurrentItem() == null) {
            return;
        }

        // Sprawdzenie, czy otwarty jest odpowiedni ekwipunek
        if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', Lobby.getInstance().cfg.getString("inventory.title")))) {
            e.setCancelled(true);

            // Sprawdzenie, czy kliknięty przedmiot to ten, który powinien reprezentować dostępność kanałów
            if (e.getCurrentItem().getType() == Material.getMaterial(Lobby.getInstance().cfg.getString("layouts.online.material"))) {
                for (ServerInfo server : Lobby.getInstance().servers.values()) {
                    // Sprawdzenie, czy kliknięty slot odpowiada dostępnemu serwerowi
                    if (e.getSlot() == server.getSlot()) {
                        // Sprawdzenie, czy serwer jest online
                        if (e.getCurrentItem().getType() == Material.getMaterial(Lobby.getInstance().cfg.getString("layouts.current.material"))) {
                            if (server.isOnline()) {

                                if (server.getServerName().equalsIgnoreCase(Lobby.getInstance().getCurrentServer())) {
                                    // Gracz jest już połączony z tym serwerem
                                    p.closeInventory();
                                    p.sendMessage(HexTransform.translateHexColorCodes("&cJesteś już połączony z tym kanałem").replace("%server%", server.getDisplayName()));
                                }
                                if (e.getCurrentItem().getType() == Material.getMaterial(Lobby.getInstance().cfg.getString("layouts.online.material"))) {
                                    p.closeInventory();
                                    Lobby.getInstance().sendToServer(p, server.getServerName());
                                    p.sendMessage(HexTransform.translateHexColorCodes("&7Trwa łączenie do &f%server%").replace("%server%", server.getDisplayName()));
                                }
                                if (e.getCurrentItem().getType() == Material.getMaterial(Lobby.getInstance().cfg.getString("layouts.offline.material"))) {
                                    p.closeInventory();
                                    p.sendMessage(HexTransform.translateHexColorCodes("&cKanał jest obecnie offline"));
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}