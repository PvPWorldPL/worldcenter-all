package pl.textr.boxpvp.commands.Helper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "teleport", description = "Teleport do graczy lub koordynaty", usage = "/teleport [kto] <do kogo> lub [kto] <x> <y> <z>", permission = "core.cmd.helper", aliases = { "tp" })
public class TeleportCommand extends PlayerCommandExecutor {
    
    
  
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        switch (args.length) {
            case 0: {
                return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));               
            }         
            case 1: {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
                }

                p.teleport(o.getLocation());

                return ChatUtil.sendMessage(p, "&7Zostałeś przeteleportowany do gracza &f" + o.getName());
            }

            
            case 2: {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
                
                }
                final Player o2 = Bukkit.getPlayer(args[1]);
                if (o2 == null) {
                    return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
                }
                o.teleport(o2.getLocation());
                return ChatUtil.sendMessage(p, "&7Przeteleportowales gracza &f" + o.getName() + " &7do gracza &f" + o2.getName());

            }
            case 3: {
                final double x = Double.parseDouble(args[0]);
                final double y = Double.parseDouble(args[1]);
                final double z = Double.parseDouble(args[2]);
                if (Double.isNaN(x) && Double.isNaN(y) && Double.isNaN(z)) {
                    return ChatUtil.sendMessage(p, "&8[&c&l!&8] &7Koordynaty musza byc liczbami!");
                }
                p.teleport(new Location(p.getWorld(), x, y, z));
                return ChatUtil.sendMessage(p, "&7Zostales przeteleportowany na kordy &7X: &f" + x + " &7Y: &f" + y + " &7Z: &f" + z);
            }
            case 4: {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
                }
                final double x2 = Double.parseDouble(args[1]);
                final double y2 = Double.parseDouble(args[2]);
                final double z2 = Double.parseDouble(args[3]);
                if (Double.isNaN(x2) && Double.isNaN(y2) && Double.isNaN(z2)) {
                    return ChatUtil.sendMessage(p, "&8[&c&l!&8] &7Koordynaty musza byc liczbami!");
                }
                o.teleport(new Location(o.getWorld(), x2, y2, z2));
                ChatUtil.sendMessage(o, "&7Zostales przeteleportowany na kordy &7X: &f" + x2 + " &7Y: &f" + y2 + " &7Z: &f" + z2 + " &7przez &f" + p.getName());
                return ChatUtil.sendMessage(p, "&7Przeteleportowales gracza &f" + o.getName() + " &7na kordy &7X: &f" + x2 + " &7Y: &c" + y2 + " &7Z: &f" + z2);
            }
            default: {
                return false;
            }
        }
    }
}
