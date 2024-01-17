package pl.textr.boxpvp.commands.Guild;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.Clans;
import api.managers.ClanManager;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "kinfo", description = "Pokazuje informacje o klanie", usage = "/kinfo <tag/nazwa>", permission = "core.cmd.user")
public class InfoCommandGuilds extends PlayerCommandExecutor {
    private static final Map<String, Long> cooldowns = new HashMap<>();

    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (hasCooldown(p)) {
            long secondsLeft = getCooldownSecondsLeft(p);
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cMożesz użyć tej komendy ponownie za " + secondsLeft + " sek");
        }

        Clans g = null;
        if (args.length == 0) {
            g = ClanManager.getGuild(p);
        } else {
            g = ClanManager.getGuild(args[0]);
        }

        if (g == null && args.length == 0) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie posiadasz klanu!");
        }
        if (g == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cKlan o takim tagu nie istnieje!");
        }

        ClanManager.guildInfo(p, g.getTag());
        applyCooldown(p, 30); // Ustawienie cooldownu na 30 sekund dla gracza

        return true;
    }

    private boolean hasCooldown(Player player) {
        return cooldowns.containsKey(player.getName());
    }

    private long getCooldownSecondsLeft(Player player) {
        long currentTime = System.currentTimeMillis();
        long cooldownTime = cooldowns.getOrDefault(player.getName(), 0L);
        long elapsedSeconds = (currentTime - cooldownTime) / 1000;
        long secondsLeft = 30 - elapsedSeconds;
        if (secondsLeft <= 0) {
            cooldowns.remove(player.getName()); // usuń wartość cooldownu z mapy
        }
        return Math.max(0, secondsLeft);
    }

    private void applyCooldown(Player player, int seconds) {
        long cooldownTime = System.currentTimeMillis() + (seconds * 1000);
        cooldowns.put(player.getName(), cooldownTime);
    }

    //metoda do resetowania cooldownów, np. przy restarcie serwera
    public static void resetCooldowns() {
        cooldowns.clear();
    }
}