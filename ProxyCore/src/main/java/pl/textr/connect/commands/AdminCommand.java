package pl.textr.connect.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.apache.commons.lang3.StringUtils;
import pl.textr.connect.AuthPlugin;
import pl.textr.connect.utils.ChatUtil;

import java.util.HashSet;
import java.util.Set;

public class AdminCommand extends Command implements TabExecutor
{
    public AdminCommand() {
        super("gadmin");
    }
    
    public void execute(final CommandSender sender, final String[] args) {
       
    	  if (sender instanceof ProxiedPlayer && ! AuthPlugin.getPlugin().getConfiguration().adminsnicks().contains(sender.getName())) {
              ChatUtil.sendMessage(sender, "&cNie masz dostepu do tej komendy!");
              return;
          }
          if (args.length < 1) {
              ChatUtil.sendMessage(sender,  AuthPlugin.getPlugin().getConfiguration().message_usage.replace("{USAGE}", "/gadmin <add>|<remove>|<list>"));
            return;
          }
                  
          if (args[0].equalsIgnoreCase("add")) {
              final String nick = args[1];
              if (AuthPlugin.getPlugin().getConfiguration().adminsnicks().contains(nick)) {
               ChatUtil.sendMessage(sender, "&8[&C&l!&8] &c" + nick + " &7jest juz na liscie Administratrow!");
               return;
              }
              AuthPlugin.getPlugin().getConfiguration().adminsnicks().add(nick);
              AuthPlugin.getPlugin().getConfiguration().save();
              ChatUtil.sendMessage(sender, "&8» &7Gracz &c" + nick + " &7zostal dodany do listy Administratorow!");
              return;
              }
         if (args[0].equalsIgnoreCase("remove")) {
             final String nick = args[1];
             if (!AuthPlugin.getPlugin().getConfiguration().adminsnicks().contains(nick)) {
              ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7" + nick + " nie jest juz Administratorem!");
              }
             AuthPlugin.getPlugin().getConfiguration().adminsnicks().remove(nick);
             AuthPlugin.getPlugin().getConfiguration().save();
            ChatUtil.sendMessage(sender, "&8» &7Gracz &c" + nick + " &7zostal usuniety z listy Administratorow!");
            return;  
         }
        if (args[0].equalsIgnoreCase("list")) {
            ChatUtil.sendMessage(sender, "&8» &7Lista Administratorow: &c" + StringUtils.join(AuthPlugin.getPlugin().getConfiguration().adminsnicks(), "&c, &7"));
            return;   
        }
    }
    
    public Iterable<String> onTabComplete(final CommandSender sender, final String[] args) {
        if (args.length > 3) {
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
