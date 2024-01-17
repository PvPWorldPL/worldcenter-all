
package pl.textr.boxpvp.commands.User;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.UserProfile;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "przelej", description = "przelewanie monet graczom", usage = "/przelej <nick> <ilosc pieniedzy>", permission = "core.cmd.user", aliases = {"przelew"})
public class PayCommand extends PlayerCommandExecutor {
	
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        
        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
        }
        
        
        if (!ChatUtil.isAlphaNumeric(args[1])) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNieprawidłowa wartość");     }
        
        if (!ChatUtil.isInteger(args[1])) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNieprawidłowa wartość");
        }
        
        
        final double amount = new BigInteger(args[1]).doubleValue();
   
        final UserProfile odbierajacy = UserAccountManager.getUser(target);
        final UserProfile wysylajacy = UserAccountManager.getUser(p);
        
        if (target.getName().equalsIgnoreCase(p.getName())) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie możesz przelać pieniędzy sobie!");
        }
    
        if (wysylajacy.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie posiadasz wystarczającej liczby pieniędzy! &8(&f$" + ChatUtil.formatAmount(amount) + "&8)");
        }
        odbierajacy.addBalance(amount);
        wysylajacy.removeBalance(amount);
        odbierajacy.save();
        wysylajacy.save();
        
        ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Przelales dla gracza &f" + target.getName() + " &f$" + ChatUtil.formatAmount(amount));
        ChatUtil.sendMessage(target, "&8[&C&l!&8] &7Otrzymales od gracza &f" + p.getName() + " &f$" + ChatUtil.formatAmount(amount));
        
        return true;
    }
}
