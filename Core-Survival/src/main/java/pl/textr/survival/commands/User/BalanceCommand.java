
package pl.textr.survival.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.UserProfile;
import api.managers.UserAccountManager;
import pl.textr.survival.utils.ChatUtil;

@CommandInfo(name = "money", description = "Wyświetla ilość pieniędzy", usage = "/money", permission = "core.cmd.user", aliases = { "balance", "monety" })
public class BalanceCommand extends PlayerCommandExecutor {

    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
    	 final UserProfile u = UserAccountManager.getUser(p.getName());
      return ChatUtil.sendMessage(p, "&7Twoje pieniadze &a$" + ChatUtil.formatAmount(u.getBalance()));

    }
}
