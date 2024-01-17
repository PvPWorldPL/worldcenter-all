package pl.textr.boxpvp.commands.Helper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.UserProfile;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "vanish", description = "Zarządzaj trybem niewidzialności", usage = "/vanish [gracz]", permission = "core.cmd.helper", aliases = { "v" })
public class VanishCommand extends PlayerCommandExecutor {



    @Override
    public boolean onCommand(Player sender, String[] args) {
        if (args.length == 0) {
            // Toggle trybu niewidzialności gracza wykonującego komendę
            UserProfile user = UserAccountManager.getUser(sender);
            user.setVanish(!user.isVanish());
            setVanish(sender, user);
            user.save();

            String message = user.isVanish() ? "&cUkryłeś się." : "&aStałeś się widoczny.";
            broadcastVanishStatus(sender.getName(), user.isVanish());
            return ChatUtil.sendMessage(sender, message);
        } else {
            // Ustaw tryb niewidzialności dla innego gracza
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                return ChatUtil.sendMessage(sender, "&cGracz jest offline lub nie znaleziono go w bazie danych!");
            }

            UserProfile targetUser = UserAccountManager.getUser(target);
            targetUser.setVanish(!targetUser.isVanish());
            setVanish(target, targetUser);
            targetUser.save();

            broadcastVanishStatus(sender.getName(), targetUser.isVanish());
            ChatUtil.sendMessage(target, "&7Twój tryb vanish został ustawiony " + (targetUser.isVanish() ? "&aWłączony" : "&cWyłączony") + "&7 przez &f" + sender.getName());

            String message = "Vanish " + (targetUser.isVanish() ? "&aWłączony" : "&cWyłączony") + " dla " + target.getName();
            return ChatUtil.sendMessage(sender, message);
        }
    }

    private void broadcastVanishStatus(String playerName, boolean isVanish) {
        String message = isVanish ? "&7Administrator &c" + playerName + " jest teraz &cukryty." : "&7Administrator &a" + playerName + " stał się &awidoczny.";
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("core.cmd.helper") || player.isOp()) {
                ChatUtil.sendMessage(player, "&8[&C&l!&8] " + message);
            }
        }
    }

    public static void setVanish(Player player, UserProfile user) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (user.isVanish()) {
                if (onlinePlayer.hasPermission("core.cmd.helper")) {
                    onlinePlayer.showPlayer(Main.getPlugin(), player);
                } else {
                    onlinePlayer.hidePlayer(Main.getPlugin(), player);
                }
            } else {
                onlinePlayer.showPlayer(Main.getPlugin(), player);
            }
        }
    }
}
