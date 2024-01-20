package pl.textr.connect.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import pl.textr.connect.AuthPlugin;
import pl.textr.connect.utils.ChatUtil;

import org.apache.commons.lang3.StringUtils;

public class SlotCommand extends Command
{
    public SlotCommand() {
        super("gslot");
    }
    
    public void execute(final CommandSender sender, final String[] args) {
    	 
    	 if (sender instanceof ProxiedPlayer && ! AuthPlugin.getPlugin().getConfiguration().adminsnicks().contains(sender.getName())) {
             ChatUtil.sendMessage(sender, "&cNie masz dostepu do tej komendy!");
             return;
         }

        if (args.length < 1) {
            ChatUtil.sendMessage(sender,  AuthPlugin.getPlugin().getConfiguration().message_usage.replace("{USAGE}", "/gslot <liczba>"));           
            return;
        }
        if (!StringUtils.isNumeric(args[0])) {
            ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cPodana wartosc nie jest liczba!");
            return;
        }
        final int slot = AuthPlugin.getPlugin().getConfiguration().slot = Integer.parseInt(args[0]);
        ChatUtil.sendMessage(sender, "&7Ustawiles sloty na &c" + slot);
        AuthPlugin.getPlugin().getConfiguration().save();
    }
}
