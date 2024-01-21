package pl.textr.boxpvp.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import api.data.UserProfile;
import api.managers.UserAccountManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import pl.textr.boxpvp.utils.teleport.TeleportTimer;

public class ActionbarTask implements Runnable {

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UserProfile user = UserAccountManager.getUser(player);
            String message = "";

            if (user.isVanish()) {
                message += "&fJestes w trybie &aVanish";
            }

            if (TeleportTimer.teleporting.containsKey(player)) {
                if (!message.isEmpty()) {
                    message += " &8| ";
                }
                long teleportEndTime = TeleportTimer.teleporting.get(player);
                int teleportRemainingTime = (int) ((teleportEndTime - System.currentTimeMillis()) / 1000L);
                message += "&fTeleportacja &8(&a" + teleportRemainingTime + "sek.&8)";
            }

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7Wymiana monet: " + (user.isPrzerabianieMonet() ? "&a&l✔" : "&c&l✖") + "&7, &7Sprzedaż monet: " + (user.isPrzerabianieKasy() ? "&a&l✔" : "&c&l✖") + " &8(&f/ustawienia&8)")));

            if (!message.isEmpty()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
            }
        }
    }
}
