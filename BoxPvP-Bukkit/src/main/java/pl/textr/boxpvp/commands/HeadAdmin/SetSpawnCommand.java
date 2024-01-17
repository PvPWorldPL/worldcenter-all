package pl.textr.boxpvp.commands.HeadAdmin;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "setspawn", description = "Ustawia spawn mapy", usage = "/setspawn", permission = "core.cmd.headadmin")
public class SetSpawnCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player p, final String[] args) {
        Location playerLocation = p.getLocation();
        Main.getPlugin().getConfiguration().setSpawnLocation(playerLocation);
      	Main.getPlugin().getConfiguration().save();

        return ChatUtil.sendMessage(p, "&aPomyślnie ustawiono spawn");
    }
}