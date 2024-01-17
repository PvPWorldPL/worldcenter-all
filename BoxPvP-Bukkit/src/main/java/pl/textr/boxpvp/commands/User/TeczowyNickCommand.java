package pl.textr.boxpvp.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.UserProfile;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "kolorowynick", description = "Zmień swój nick na tęczowy", usage = "/kolorowynick", permission = "core.cmd.user")
public class TeczowyNickCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player sender, final String[] args) {
    	 if (!sender.hasPermission("core.premium")) {
           	ChatUtil.sendMessage(sender, ChatUtil.translateHexColorCodes("&7Ta czynnosc jest dostepna tylko dla rang &6premium"));
           	return true;
         }
        final UserProfile u = UserAccountManager.getUser(sender.getName());
        u.setTeczowy(!u.isTeczowy());
        u.save();
        return ChatUtil.sendMessage(sender, "&7Twój kolorowy nick został " + (u.isTeczowy() ? "&awlaczony" : "&cwylaczony"));
    }
}