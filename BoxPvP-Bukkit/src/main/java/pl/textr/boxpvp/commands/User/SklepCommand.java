package pl.textr.boxpvp.commands.User;


import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.menu.ShopMenu;


@CommandInfo(name = "sklep", description = "Otwórz sklep", usage = "/sklep", permission = "core.cmd.user", aliases = {"shop"})
public class SklepCommand extends PlayerCommandExecutor {




    @Override
    public boolean onCommand(final Player p, final String[] args) {
        ShopMenu.menu(p);
        return true;
    }
}
