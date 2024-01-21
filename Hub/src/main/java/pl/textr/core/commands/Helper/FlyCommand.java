package pl.textr.core.commands.Helper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.textr.core.commands.Api.PlayerCommand;
import pl.textr.core.utils.other.ChatUtil;

public class FlyCommand extends PlayerCommand
{
    public FlyCommand() {
        super("fly", "zarzadzanie trybem latania graczy", "/fly [gracz]", "core.cmd.helper");
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            p.setAllowFlight(!p.getAllowFlight());
            return ChatUtil.sendMessage(p, "&7Fly zostal " + (p.getAllowFlight() ? "&awlaczony" : "&cwylaczony"));
        }
        if (!p.hasPermission("core.cmd.admin")) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie masz dostepu!");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage(p, "&8[&c&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
        }
        o.setAllowFlight(!o.getAllowFlight());
        ChatUtil.sendMessage(o, "&7Fly zostal " + (o.getAllowFlight() ? "&awlaczony" : "&cwylaczony") + " &7przez &f" + p.getName());
        return ChatUtil.sendMessage(p, "&7Fly zostal " + (o.getAllowFlight() ? "&awlaczony" : "&cwylaczony") + " &7dla &f" + o.getName());
    }
}
