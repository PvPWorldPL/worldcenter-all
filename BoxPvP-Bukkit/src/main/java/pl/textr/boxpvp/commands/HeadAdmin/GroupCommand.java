package pl.textr.boxpvp.commands.HeadAdmin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import api.data.UserProfile;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "group", description = "Ustawianie grupy dla gracza", usage = "/group <nick> <|Lider>|<By>|chatmod|<Debil>|<admin>|<mod>|<helper>|<thelper>|vip|<sponsor>|<yt>|<yt+>|<friend>", permission = "core.cmd.headadmin")
public class GroupCommand extends BaseCommand {

    @Override
    public boolean onExecute(final CommandSender p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
            
        }

        final UserProfile u = UserAccountManager.getUser(args[0]);
     
        if (u == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");     
        }
        
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + args[0] + " parent set " + args[1] + " server=boxpvp");

        ChatUtil.sendMessage(p, "&7Zmieniles range &f" + args[0] + " &7na &f" + args[1]);


        if (u.getPlayer() != null) {
            ChatUtil.sendMessage(u.getPlayer(), "&7Twoja ranga zostala zmieniona na &f" + args[1] + " &7przez &f" + p.getName());

        }
        return false;
    }
}
