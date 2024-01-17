package api.managers;


import org.bukkit.entity.Player;

public class CooldownManager {
    private static final int COOLDOWN_SECONDS = 30;

        private static final long COOLDOWN_TIME = COOLDOWN_SECONDS * 1000;
        private static final java.util.Map<String, Long> cooldowns = new java.util.HashMap<>();

        public static boolean canUse(Player player) {
            return !cooldowns.containsKey(player.getName()) || (System.currentTimeMillis() - cooldowns.get(player.getName())) > COOLDOWN_TIME;
        }

        public static void setCooldown(Player player) {
            cooldowns.put(player.getName(), System.currentTimeMillis());
        }

        public static long getRemainingCooldown(Player player) {
            if (cooldowns.containsKey(player.getName())) {
                long cooldownTime = cooldowns.get(player.getName());
                long elapsedTime = System.currentTimeMillis() - cooldownTime;
                return Math.max(0, (COOLDOWN_TIME - elapsedTime) / 1000);
            }
            return 0;
        }
    }

