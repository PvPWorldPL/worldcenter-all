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

        if (npc.getName().contains("boxpvp")) {

            player.sendMessage("xdd");
        }
    }
}