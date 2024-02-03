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
        Bukkit.getLogger().info("Debug chuj");

        String npcName = npc.getName();

        if (npcName.contains("boxpvp")) {
            Bukkit.getLogger().info("Debug 1");
            ConnectUtil.connect(player, "boxpvp-1");
        } else if (npcName.contains("skypvp")) {
            Bukkit.getLogger().info("Debug 2");
            ConnectUtil.connect(player, "skypvp-1");
        } else if (npcName.contains("lifesteal")) {
            Bukkit.getLogger().info("Debug 3");
            ConnectUtil.connect(player, "lifesteal-1");
        } else if (npcName.contains("kitpvp")) {
            Bukkit.getLogger().info("Debug 4");
            ConnectUtil.connect(player, "kitpvp-1");
        } else if (npcName.contains("zombiemod")) {
            Bukkit.getLogger().info("Debug 5");
            ConnectUtil.connect(player, "zombiemod-1");
        }
    }
}
