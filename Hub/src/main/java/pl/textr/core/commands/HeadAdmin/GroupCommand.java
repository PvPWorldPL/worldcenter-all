package pl.textr.core.commands.HeadAdmin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.textr.core.LobbyPlugin;
import pl.textr.core.commands.Api.Command;
import pl.textr.core.utils.other.ChatUtil;
public class GroupCommand extends Command
{
    public GroupCommand() {
        super("group", "Ustawianie grupy dla gracza", "/group <nick>  <|Lider>|<By>|chatmod|<Debil>|<admin>|<mod>|<helper>|<thelper>|vip|<sponsor>|<yt>|<yt+>|<friend>", "core.cmd.headadmin", "group");
    }
    
    @Override
    public boolean onExecute(final CommandSender p, final String[] args) {
    	if (args.length < 2) {
            return ChatUtil.sendMessage(p, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
             }
 
        final Player p1 = Bukkit.getPlayer(args[0]);
        if (p1 == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
        }
    
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + args[0] + " group set " + args[1]);
        
        ChatUtil.sendMessage(p, "&7Zmieniles range &f" + args[0] + " &7na &f" + args[1]);
       
     
        if (p1.getPlayer() != null) {
            ChatUtil.sendMessage(p1.getPlayer(), "&7Twoja ranga zostala zmieniona na &f" + args[1] + " &7przez &f" + p.getName());
         
        }
        return false;
    }
}
