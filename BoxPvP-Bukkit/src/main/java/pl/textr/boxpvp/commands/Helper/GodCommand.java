package pl.textr.boxpvp.commands.Helper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.UserProfile;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "god", description = "Zarządzanie trybem boga graczy", usage = "/god [gracz]", permission = "core.cmd.helper")
public class GodCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            toggleGodMode(UserAccountManager.getUser(p.getName()), p);
        } else {
            final Player o = Bukkit.getPlayer(args[0]);
            if (o == null) {
                ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
               
                return true;
            }
            toggleGodMode(UserAccountManager.getUser(o), o);
            ChatUtil.sendMessage(o, "&7God zostal " + (UserAccountManager.getUser(o).isGod() ? "&awlaczony" : "&cwylaczony") + " &7przez &f" + p.getName());
            ChatUtil.sendMessage(p, "&7God zostal " + (UserAccountManager.getUser(o).isGod() ? "&awlaczony" : "&cwylaczony") + " &7dla &f" + o.getName());
        }
        return true;
    }

    private void toggleGodMode(UserProfile user, Player player) {
        if (user == null) {
            return;
        }
        user.setGod(!user.isGod());
        user.save();
        ChatUtil.sendMessage(player, "&7God zostal " + (user.isGod() ? "&awlaczony" : "&cwylaczony"));
    }
}
