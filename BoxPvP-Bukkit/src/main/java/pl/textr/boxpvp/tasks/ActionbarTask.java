package pl.textr.boxpvp.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import api.data.UserProfile;
import api.managers.UserAccountManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.teleport.TeleportTimer;

public class ActionbarTask implements Runnable {

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UserProfile user = UserAccountManager.getUser(player);
            String message = "";

            if (user.isVanish()) {
                message += "&#1eff12j&#1cf111e&#1be210s&#19d40ft&#17c50ee&#16b70ds &#14a80cw &#129a0bt&#118b0ar&#0f7d09y&#118b0ab&#129a0bi&#14a80ce &#16b70dv&#17c50ea&#19d40fn&#1be210i&#1cf111s&#1eff12h";
            }

            if (TeleportTimer.teleporting.containsKey(player)) {
                if (!message.isEmpty()) {
                    message += " &8| ";
                }
                long teleportEndTime = TeleportTimer.teleporting.get(player);
                int teleportRemainingTime = (int) ((teleportEndTime - System.currentTimeMillis()) / 1000L);
                message += "&fTeleportacja &8(&a" + teleportRemainingTime + "sek.&8)";
            }

            if (!message.isEmpty()) {
                message += " &8| ";
            }
            message += "&7Przerabianie bloków: " + (user.isPrzerabianieBlokow() ? "&a&l✔" : "&c&l✖") + " &7Przerabianie monet: " + (user.isPrzerabianieMonet() ? "&a&l✔" : "&c&l✖") + " &7Przerabianie kasy: " + (user.isPrzerabianieKasy() ? "&a&l✔" : "&c&l✖") + " &8(&f/ustawienia&8)";

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.translateHexColorCodes(message)));
        }
    }
}
