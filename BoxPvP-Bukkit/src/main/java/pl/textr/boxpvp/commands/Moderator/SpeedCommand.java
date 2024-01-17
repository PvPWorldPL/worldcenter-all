package pl.textr.boxpvp.commands.Moderator;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "speed", description = "Ustaw prędkość gracza", usage = "/speed <1-10>", permission = "core.cmd.helper")
public class SpeedCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
            ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
            return true;
        }
        final float speed = Float.parseFloat(args[0]);
        if (speed < 1.0f || speed > 20.0f) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Predkosc speed musi wynosic &r1&7-&r20");
            return true;
        }
        final float finalSpeed = speed / 20.0f;
        p.setFlySpeed(finalSpeed);
        ChatUtil.sendMessage(p, "&7Ustawiles predkosc latania na &f" + speed);
        return true;
    }
}
