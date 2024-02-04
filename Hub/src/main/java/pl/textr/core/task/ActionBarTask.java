package pl.textr.core.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.utils.other.ChatUtil;
import pl.textr.core.utils.other.DataUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ActionBarTask implements Runnable {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            long currentTime = System.currentTimeMillis();

            try {
                Date timerDate = DATE_FORMAT.parse(LobbyPlugin.getPlugin().getConfiguration().odliczanieactionbar);
                long timer = timerDate.getTime();
                long timeRemaining = Math.max(timer - currentTime, 0); // Ensure non-negative time

                String formattedTime = formatTime(timeRemaining);
                String message = "";

                if (!LobbyPlugin.getPlugin().getConfiguration().ActionBarMessage.isEmpty()) {
                    message += ChatUtil.translateHexColorCodes(LobbyPlugin.getPlugin().getConfiguration().ActionBarMessage).replace("{DATA}", formattedTime);
                }
                player.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            } catch (ParseException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }

    private String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        return String.format("%02d:%02d:%02d", hours % 24, minutes % 60, seconds % 60);
    }
}