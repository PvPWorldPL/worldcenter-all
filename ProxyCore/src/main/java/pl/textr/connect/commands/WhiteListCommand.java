package pl.textr.connect.commands;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import pl.textr.connect.AuthPlugin;
import pl.textr.connect.utils.ChatUtil;

public class WhiteListCommand extends Command implements TabExecutor
{
    public WhiteListCommand() {
        super("gwl");
    }
    
    public void execute(final CommandSender sender, final String[] args) {
       
    	  if (sender instanceof ProxiedPlayer && ! AuthPlugin.getPlugin().getConfiguration().adminsnicks().contains(sender.getName())) {
              ChatUtil.sendMessage(sender, "&cNie masz dostepu do tej komendy!");
              return;
          }
          if (args.length < 1) {
              ChatUtil.sendMessage(sender,  AuthPlugin.getPlugin().getConfiguration().message_usage.replace("{USAGE}", "/gwl <on>|<off>|<add>|<remove>|<list>|<reason>"));
                 return;
          }
                  
          if (args[0].equalsIgnoreCase("on")) {
             if (AuthPlugin.getPlugin().getConfiguration().enablewhitelist()) {
              ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cWhitelist jest juz on!");
              return;
             }
             AuthPlugin.getPlugin().getConfiguration().enablewhitelist = true;
             AuthPlugin.getPlugin().getConfiguration().save();
             ChatUtil.sendMessage(sender, "&8» &aWhitelist zostala wlaczona!");
             return;
             
          } 
          
          if (args[0].equalsIgnoreCase("off")) {
              if (!AuthPlugin.getPlugin().getConfiguration().enablewhitelist()) {
              ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cWhitelist jest off!");
              return;
              }
              AuthPlugin.getPlugin().getConfiguration().enablewhitelist = false;
              AuthPlugin.getPlugin().getConfiguration().save();
               ChatUtil.sendMessage(sender, "&8» &cWhitelist zostala wylaczona!");
               return;
          }
          
          if (args[0].equalsIgnoreCase("add")) {
              final String nick = args[1];
              if (AuthPlugin.getPlugin().getConfiguration().whitelistnicks().contains(nick)) {
               ChatUtil.sendMessage(sender, "&8[&C&l!&8] &c" + nick + " &7jest juz na whitelist!");
               return;
              }
              AuthPlugin.getPlugin().getConfiguration().whitelistnicks().add(nick);
              AuthPlugin.getPlugin().getConfiguration().save();
              ChatUtil.sendMessage(sender, "&8» &7Gracz &c" + nick + " &7zostal dodany do whitelist!");
              return;
              }
         if (args[0].equalsIgnoreCase("remove")) {
             final String nick = args[1];
             if (!AuthPlugin.getPlugin().getConfiguration().whitelistnicks().contains(nick)) {
              ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7" + nick + " nie jest na whitelist!");
              }
             AuthPlugin.getPlugin().getConfiguration().whitelistnicks().remove(nick);
             AuthPlugin.getPlugin().getConfiguration().save();
            ChatUtil.sendMessage(sender, "&8» &7Gracz &c" + nick + " &7zostal usuniety z whitelist!");
            return;  
         }
         
         if (args[0].equalsIgnoreCase("reason")) {
            final String reason = StringUtils.join(args, " ", 1, args.length);
            AuthPlugin.getPlugin().getConfiguration().whitelistreason = ChatUtil.fixColor(reason);
            AuthPlugin.getPlugin().getConfiguration().save();
             ChatUtil.sendMessage(sender, "&8» &7Ustawiles powod whitelist na: &c" + reason);
             return;
         }
        if (args[0].equalsIgnoreCase("list")) {
            ChatUtil.sendMessage(sender, "&8» &7Lista graczy na whitelist: &c" + StringUtils.join(AuthPlugin.getPlugin().getConfiguration().whitelistnicks(), "&c, &7"));
            return;   
        }
    }

          
        
      
  
    
    public Iterable<String> onTabComplete(final CommandSender sender, final String[] args) {
        if (args.length <= 3) {
        }
        final Set<String> matches = new HashSet<String>();
        if (args.length == 2) {
            final String search = args[1].toLowerCase();
            for (final ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                if (player.getName().toLowerCase().startsWith(search)) {
                    matches.add(player.getName());
                }
            }
        }
        return matches;
    }
}
