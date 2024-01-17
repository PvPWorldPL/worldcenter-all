package pl.textr.boxpvp.commands.Moderator;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "heal", description = "Uleczanie graczy", usage = "/heal [gracz]", permission = "core.cmd.moderator")
public class HealCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player p, final String[] args) {
        Player target = p;
        if (args.length == 1) {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
                return true;
            }
        }
        healPlayer(target);
        ChatUtil.sendMessage(target, "&7Zostales uleczony przez &f" + p.getName());
        if (target == p) {
            ChatUtil.sendMessage(p, "&aZostales uleczony!");
        } else {
            ChatUtil.sendMessage(p, "&7Uleczyles &f" + target.getName());
        }
        return true;
    }

    private void healPlayer(Player player) {
        player.setFireTicks(0);
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        player.setFoodLevel(20);
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
    }
}