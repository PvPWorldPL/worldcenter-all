package pl.textr.core.listeners.other;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.textr.core.utils.other.ConnectUtil;

import java.util.logging.Logger;

public class NpcClickListener implements Listener {

    @EventHandler
    public void onRightClick(NPCRightClickEvent event) {
        NPC npc = event.getNPC();
        Player player = event.getClicker();
        Bukkit.getLogger().warning("Debug chuj");

        if (npc.getName().equals("boxpvp")) {
            Bukkit.getLogger().warning("Debug 1");
            ConnectUtil.connect(player, "boxpvp-1");
            return;
        }
        if (npc.getName().equals("skypvp")) {
            Bukkit.getLogger().warning("Debug 2");
            ConnectUtil.connect(player, "skypvp-1");
            return;
        }
        if (npc.getName().equals("lifesteal")) {
            Bukkit.getLogger().warning("Debug 3");
            ConnectUtil.connect(player, "lifesteal-1");
            return;
        }
        if (npc.getName().equals("kitpvp")) {
            Bukkit.getLogger().warning("Debug 4");
            ConnectUtil.connect(player, "kitpvp-1");
            return;
        }
        if (npc.getName().equals("zombiemod")) {
            Bukkit.getLogger().warning("Debug 5");
            ConnectUtil.connect(player, "zombiemod-1");
        }
    }
}
