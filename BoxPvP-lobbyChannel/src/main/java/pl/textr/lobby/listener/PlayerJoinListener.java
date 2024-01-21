package pl.textr.lobby.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import pl.textr.lobby.Lobby;
import pl.textr.lobby.misc.ItemBuilder;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

       // final ItemBuilder item = new ItemBuilder(Material.getMaterial(Lobby.getInstance().cfg.getString("hotbarItem.material")), 1, (short)Lobby.getInstance().cfg.getInt("hotbarItem.subid")).setDisplayName(ChatColor.translateAlternateColorCodes('&', Lobby.getInstance().cfg.getString("hotbarItem.displayname"))).setLore(Lobby.getInstance().cfg.getStringList("hotbarItem.lore"));
      //  p.getInventory().setItem(Lobby.getInstance().cfg.getInt("hotbarItem.slot"), (ItemStack)item);
        if (Lobby.getInstance().currentServer == null)
            Lobby.getInstance().getServer(p);    
        
    }

}
