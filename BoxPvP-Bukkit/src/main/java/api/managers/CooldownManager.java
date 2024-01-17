package api.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    private static long DEFAULT_COOLDOWN_TIME = 30 * 1000;  // Domyślny czas cooldownu w milisekundach
    private static final Map<String, Long> cooldowns = new HashMap<>();

    public static void setCooldownTime(int seconds) {
        DEFAULT_COOLDOWN_TIME = seconds * 1000;
    }

    public static boolean canUse(Player player) {
        if (cooldowns.containsKey(player.getName())) {
            long cooldownTime = cooldowns.get(player.getName());
            long elapsedTime = System.currentTimeMillis() - cooldownTime;
            long remainingCooldown = Math.max(0, (DEFAULT_COOLDOWN_TIME - elapsedTime) / 1000);

            if (remainingCooldown > 0) {
                return false;
            }
        }

        cooldowns.put(player.getName(), System.currentTimeMillis());
        return true;
    }

    public static long getRemainingCooldown(Player player) {
        if (cooldowns.containsKey(player.getName())) {
            long cooldownTime = cooldowns.get(player.getName());
            long elapsedTime = System.currentTimeMillis() - cooldownTime;
            return Math.max(0, (elapsedTime) / 1000);  // Zwraca czas od ustawienia cooldownu do teraz
        }
        return 0;
    }

    public static boolean isCooldownActive(Player player) {
        return cooldowns.containsKey(player.getName());
    }
}
