package pl.textr.boxpvp.commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.menu.SzamanMenu;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "resetperk", description = "Resetuje perki gracza", usage = "/resetperk <gracz>", permission = "core.cmd.admin")
public class ResetperkCommand extends PlayerCommandExecutor {
  
  public boolean onCommand(Player p, String[] args) {
	   if (args.length < 1) {
           return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
           
       }
	   
    Player cel = Bukkit.getPlayer(args[0]);
    
    if (cel == null) {
        return ChatUtil.sendMessage(p, "&8[&c&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
    }
 
    SzamanMenu.resetPerki(cel);

    ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&7Zresetowales perki &f" + cel.getName()));
    return false;
  }
}
