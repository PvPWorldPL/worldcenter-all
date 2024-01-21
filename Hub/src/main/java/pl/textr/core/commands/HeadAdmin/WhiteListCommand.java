package pl.textr.core.commands.HeadAdmin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import pl.textr.core.LobbyPlugin;
import pl.textr.core.commands.Api.Command;
import pl.textr.core.utils.other.ChatUtil;



public class WhiteListCommand extends Command
{
    public WhiteListCommand() {
        super("whitelist", "whitelist serwera", "/whitelist <on|off|add|remove|list> [gracz]", "core.cmd.headadmin", "wl");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
        }
                
        if (args[0].equalsIgnoreCase("on")) {
           if (LobbyPlugin.getPlugin().getConfiguration().enablewhitelist()) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cWhitelist jest juz on!");
           }
           LobbyPlugin.getPlugin().getConfiguration().enablewhitelist = true;
           LobbyPlugin.getPlugin().getConfiguration().save();
            return ChatUtil.sendMessage(sender, "&8» &aWhitelist zostala wlaczona!");
        } 
        
        if (args[0].equalsIgnoreCase("off")) {
            if (!LobbyPlugin.getPlugin().getConfiguration().enablewhitelist()) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cWhitelist jest off!");
            }
            LobbyPlugin.getPlugin().getConfiguration().enablewhitelist = false;
            LobbyPlugin.getPlugin().getConfiguration().save();
            return ChatUtil.sendMessage(sender, "&8» &cWhitelist zostala wylaczona!");
        }
        
        if (args[0].equalsIgnoreCase("add")) {
            final String nick = args[1];
            if (LobbyPlugin.getPlugin().getConfiguration().whitelistnicks().contains(nick)) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &c" + nick + " &7jest juz na whitelist!");
            }
            LobbyPlugin.getPlugin().getConfiguration().whitelistnicks().add(nick);
            LobbyPlugin.getPlugin().getConfiguration().save();
            return ChatUtil.sendMessage(sender, "&8» &7Gracz &c" + nick + " &7zostal dodany do whitelist!");
            }
       if (args[0].equalsIgnoreCase("remove")) {
           final String nick = args[1];
           if (!LobbyPlugin.getPlugin().getConfiguration().whitelistnicks().contains(nick)) {
           return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7" + nick + " nie jest na whitelist!");
            }
           LobbyPlugin.getPlugin().getConfiguration().whitelistnicks().remove(nick);
           LobbyPlugin.getPlugin().getConfiguration().save();
           return ChatUtil.sendMessage(sender, "&8» &7Gracz &c" + nick + " &7zostal usuniety z whitelist!");
            }
       if (args[0].equalsIgnoreCase("reason")) {
          final String reason = StringUtils.join(args, " ", 1, args.length);
          LobbyPlugin.getPlugin().getConfiguration().whitelistreason = ChatUtil.fixColor(reason);
          LobbyPlugin.getPlugin().getConfiguration().save();
          return ChatUtil.sendMessage(sender, "&8» &7Ustawiles powod whitelist na: &c" + reason);
    }
      if (args[0].equalsIgnoreCase("list")) {
          return ChatUtil.sendMessage(sender, "&8» &7Lista graczy na whitelist: &c" + StringUtils.join(LobbyPlugin.getPlugin().getConfiguration().whitelistnicks(), "&c, &7"));
            }
        
        
		return true;
    }
}
        
    

