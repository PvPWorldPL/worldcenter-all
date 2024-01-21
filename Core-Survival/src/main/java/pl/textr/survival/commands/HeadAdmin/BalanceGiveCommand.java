package pl.textr.survival.commands.HeadAdmin;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.UserProfile;
import api.managers.UserAccountManager;
import pl.textr.survival.Main;
import pl.textr.survival.utils.ChatUtil;

@CommandInfo(name = "bmoney", description = "Zarządzanie balansem gracza", usage = "/bmoney <nick> <add|set|setmoney> <ilosc>", permission = "core.cmd.headadmin")
public class BalanceGiveCommand extends PlayerCommandExecutor {
    
    @Override
    public boolean onCommand(final Player player, final String[] args) {
        if (args.length < 3) {
          return ChatUtil.sendMessage(player, ChatUtil.fixColor(Main.getPlugin().getConfiguration().usage(this.getUsage())));

        }
        final Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            ChatUtil.sendMessage(player, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
            return true;
        }
        final UserProfile u = UserAccountManager.getUser(target);
        
        final String action = args[1].toLowerCase();
        if (!action.equals("add") && !action.equals("set") && !action.equals("setmoney")) {
            return ChatUtil.sendMessage(player, ChatUtil.fixColor(Main.getPlugin().getConfiguration().usage(this.getUsage())));

        }

        if (!ChatUtil.isInteger(args[2])) {
            return ChatUtil.sendMessage(player, "&8[&C&l!&8] &cNieprawidłowa wartość");
        }
        final double amout2 = new BigInteger(args[2]).doubleValue();
        final int amouttime = Integer.valueOf(args[2]);
        
        
        
        switch (action) {
            case "add": {
            	  u.addBalance(amout2);
            u.save();
             ChatUtil.sendMessage(player, ChatUtil.fixColor("&7Dodano &a$" + ChatUtil.formatAmount(amout2) + " &7do konta &a" + target.getName()));
                return true;
            }
            case "setmoney": {
          	  u.setMoney(amouttime);
              u.save();
              ChatUtil.sendMessage(player, ChatUtil.fixColor("&7Ustawiono stan monet czasu &a" + target.getName() + " &7na &a" + amouttime));
              return true;
          }
            case "set": {
            	  u.setBalance(BigDecimal.valueOf(amout2));
                u.save();
                ChatUtil.sendMessage(player, ChatUtil.fixColor("&7Ustawiono stan konta &a " + target.getName() + " &7na &a" + ChatUtil.formatAmount(amout2)));
                return true;
            }
            default:
                return false;
        }
    }
}