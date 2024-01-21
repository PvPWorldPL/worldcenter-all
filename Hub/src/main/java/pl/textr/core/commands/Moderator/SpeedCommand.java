package pl.textr.core.commands.Moderator;

import org.bukkit.entity.Player;

import pl.textr.core.commands.Api.PlayerCommand;
import pl.textr.core.utils.other.ChatUtil;

public class SpeedCommand extends PlayerCommand {
    public SpeedCommand() {
        super("speed", "Uleczanie graczy", "/speed 1-10", "core.cmd.helper");
    }

    @Override
    public boolean onCommand(final Player p, final String[] args) {

        final float speed = Float.parseFloat(args[0]);
        if (speed > 10.0f) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Predkosc speed musi wynosic 1-10");
        }
        if (speed < 1.0f) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Predkosc speed musi wynosic 1-10");
        }
        final float finalSpeed = speed / 10.0f;
        p.setFlySpeed(finalSpeed);
        return ChatUtil.sendMessage(p, "&7Ustawiles predkosc latania na &f" + speed);

    }
}
