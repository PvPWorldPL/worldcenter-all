package pl.textr.connect.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import pl.textr.connect.AuthPlugin;
import pl.textr.connect.utils.ChatUtil;

public class ConfigCommand extends Command
{
    public ConfigCommand() {
        super("gconfig");
    }
    
    public void execute(final CommandSender sender, final String[] args) {
    	   
        if (sender instanceof ProxiedPlayer && ! AuthPlugin.getPlugin().getConfiguration().adminsnicks().contains(sender.getName())) {
            ChatUtil.sendMessage(sender, "&cNie masz dostepu do tej komendy!");
            return;
        }
        AuthPlugin.getPlugin().getConfiguration();
        ChatUtil.sendMessage(sender, "&cCfg zaladowany!");
    	AuthPlugin.getPlugin().getConfiguration().load();
    }
}
