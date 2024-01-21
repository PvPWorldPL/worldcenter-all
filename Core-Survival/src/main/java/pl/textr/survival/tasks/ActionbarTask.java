package pl.textr.survival.tasks;

import org.bukkit.Bukkit;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;

import net.md_5.bungee.api.chat.TextComponent;
import pl.textr.survival.Main;
import pl.textr.survival.utils.DataUtil;
import pl.textr.survival.utils.teleport.TeleportTimer;

import org.bukkit.entity.Player;

public class ActionbarTask implements Runnable {

  public void run() {
      for (Player player : Bukkit.getOnlinePlayers()) {
          UserProfile user = UserAccountManager.getUser(player);

          String message = "";


          if (user.isVanish()) {
              message += "&fJestes w trybie &aVanish";
          }


          long turbodropTimer = Main.getPlugin().getConfiguration().turbodrop;
          if (turbodropTimer > System.currentTimeMillis()) {
              message += " &8| ";
              message += "&7Aktualnie trwa &C&lTURBO DROP &7pozostalo &8(&a" + DataUtil.secondsToString(turbodropTimer) + "&8)";
          }

          long voucheryTimer = Main.getPlugin().getConfiguration().vouchery;
          if (voucheryTimer > System.currentTimeMillis()) {
              message += " &8| ";
              message += "&7Aktualnie trwa &C&lTURBO RANG &7pozostalo &8(&a" + DataUtil.secondsToString(voucheryTimer) + "&8)";
          }

          if (TeleportTimer.teleporting.containsKey(player)) {
              if (!message.isEmpty()) {
                  message += " &8| ";
              }
              long teleportEndTime = TeleportTimer.teleporting.get(player);
              int teleportRemainingTime = (int) ((teleportEndTime - System.currentTimeMillis()) / 1000L);
              message += "&fTeleportacja &8(&a" + teleportRemainingTime + "sek.&8)";
          }

          if (!message.isEmpty())
              player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
      }
  }
}