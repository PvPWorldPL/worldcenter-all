package pl.textr.boxpvp.commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "fun", description = "na barana", usage = "fun <gracz>", permission = "core.cmd.admin")
public class FunCommand extends PlayerCommandExecutor {

  
  public boolean onCommand(Player p, String[] args) {
	    if (args.length < 1) {
	        ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
	        return true;
	    }

	    Player cel = Bukkit.getPlayer(args[0]);

	    if (cel == null) {
	        ChatUtil.sendMessage(p, "&8[&c&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
	        return true;
	    }
	    if (cel.equals(p)) {
	        ChatUtil.sendMessage(p, "&8[&c&l!&8]  &cNie możesz wskoczyć sobie samemu na plecy");
	        return true;
	    }

	    // Sprawdź nazwę świata, na którym znajduje się gracz, tylko jeśli gracz nie ma uprawnienia do ignorowania sprawdzania świata
	    if (p.hasPermission("core.cmd.helper") || !p.getWorld().getName().equalsIgnoreCase("pvp")) {
	        cel.setPassenger(p);
	        ChatUtil.sendTitle(p, "&7", "&7Wskoczyłeś na plecy gracza &f" + cel.getName(), 20, 60, 20);
	    } else {
	        ChatUtil.sendMessage(p, "&8[&c&l!&8] Nie możesz użyć tej komendy na tym świecie");
	    }

	    return true;
	}
}