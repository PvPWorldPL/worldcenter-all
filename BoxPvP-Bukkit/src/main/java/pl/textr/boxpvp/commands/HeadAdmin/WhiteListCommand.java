package pl.textr.boxpvp.commands.HeadAdmin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;


@CommandInfo(name = "whitelist", description = "Whitelist serwera", usage = "/whitelist <on|off|add|remove|list> [gracz]", permission = "core.cmd.headadmin")
public class WhiteListCommand extends BaseCommand {
	
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage("/wl <add|remove|list|reason|on|off>"));
        }
                
        if (args[0].equalsIgnoreCase("on")) {
           if (Main.getPlugin().getConfiguration().enablewhitelist()) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cWhitelist jest juz on!");
           }
           Main.getPlugin().getConfiguration().enablewhitelist = true;
           Main.getPlugin().getConfiguration().save();
            return ChatUtil.sendMessage(sender, "&8» &aWhitelist zostala wlaczona!");
        } 
        
        if (args[0].equalsIgnoreCase("off")) {
            if (!Main.getPlugin().getConfiguration().enablewhitelist()) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cWhitelist jest off!");
            }
            Main.getPlugin().getConfiguration().enablewhitelist = false;
            Main.getPlugin().getConfiguration().save();
            return ChatUtil.sendMessage(sender, "&8» &cWhitelist zostala wylaczona!");
        }
        
        if (args[0].equalsIgnoreCase("add")) {
            final String nick = args[1];
            if (Main.getPlugin().getConfiguration().whitelistnicks().contains(nick)) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &c" + nick + " &7jest juz na whitelist!");
            }
            Main.getPlugin().getConfiguration().whitelistnicks().add(nick);
            Main.getPlugin().getConfiguration().save();
            return ChatUtil.sendMessage(sender, "&8» &7Gracz &c" + nick + " &7zostal dodany do whitelist!");
            }
       if (args[0].equalsIgnoreCase("remove")) {
           final String nick = args[1];
           if (!Main.getPlugin().getConfiguration().whitelistnicks().contains(nick)) {
           return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7" + nick + " nie jest na whitelist!");
            }
           Main.getPlugin().getConfiguration().whitelistnicks().remove(nick);
           Main.getPlugin().getConfiguration().save();
           return ChatUtil.sendMessage(sender, "&8» &7Gracz &c" + nick + " &7zostal usuniety z whitelist!");
            }
       if (args[0].equalsIgnoreCase("reason")) {
          final String reason = StringUtils.join(args, " ", 1, args.length);
          Main.getPlugin().getConfiguration().whitelistreason = ChatUtil.translateHexColorCodes(reason);
          Main.getPlugin().getConfiguration().save();
          return ChatUtil.sendMessage(sender, "&8» &7Ustawiles powod whitelist na: &c" + reason);
    }
      if (args[0].equalsIgnoreCase("list")) {
          return ChatUtil.sendMessage(sender, "&8» &7Lista graczy na whitelist: &c" + StringUtils.join(Main.getPlugin().getConfiguration().whitelistnicks(), "&c, &7"));
            }
        
        
		return true;
    }
}
        
    

