package pl.textr.connect.commands;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import pl.textr.connect.AuthPlugin;
import pl.textr.connect.utils.ChatUtil;
import pl.textr.connect.utils.MessageManager;



public class ReplyCommand extends Command implements TabExecutor {
	  private final MessageManager messageManager;

	    public ReplyCommand(MessageManager messageManager) {
	        super("r");
	        this.messageManager = messageManager;
	    }

	    @Override
	    public void execute(CommandSender sender, String[] args) {
	        if (!(sender instanceof ProxiedPlayer)) {
	            ChatUtil.sendMessage(sender, ChatUtil.translateHexColorCodes("&cTa komenda jest dostępna tylko dla graczy"));
	            return;
	        }

	        if (args.length < 1) {
	            ChatUtil.sendMessage(sender,  AuthPlugin.getPlugin().getConfiguration().message_usage.replace("{USAGE}", "/r [wiadomosc]"));           	            
	            return;
	        }
	        
	        ProxiedPlayer senderPlayer = (ProxiedPlayer) sender;
	       
	        UUID replyTarget = messageManager.getReplyTarget(senderPlayer.getUniqueId());
	        
	        if (replyTarget == null) {
	            ChatUtil.sendMessage(sender, ChatUtil.translateHexColorCodes("&cNie masz żadnych wiadomości do odpowiedzi"));
	            return;
	        }
	        ProxiedPlayer targetPlayer = AuthPlugin.getPlugin().getProxy().getPlayer(replyTarget);

	 	   
	
	    

	        String messageContent = String.join(" ", Arrays.copyOfRange(args, 0, args.length));
	        
	        String senderName = senderPlayer.getName();
	        String targetName = targetPlayer.getName();

	        // Formatowanie wiadomości
	        BaseComponent[] targetMessage = TextComponent.fromLegacyText(ChatUtil.fixColor("&8[&f"  + senderName + " &8-> &eja&8] &7" + messageContent)); 
	        BaseComponent[] senderMessage = TextComponent.fromLegacyText(ChatUtil.fixColor("&8[&eja &8-> &f" + targetName + "&8] &7" + messageContent));
	   
	        // Wysyłamy wiadomość
	        targetPlayer.sendMessage(targetMessage);
	        sender.sendMessage(senderMessage);
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