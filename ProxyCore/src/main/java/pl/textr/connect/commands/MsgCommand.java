package pl.textr.connect.commands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

public class MsgCommand extends Command implements TabExecutor {

	private final MessageManager messageManager;

	public MsgCommand(MessageManager messageManager) {
		super("msg");
		this.messageManager = messageManager;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			ChatUtil.sendMessage(sender, ChatUtil.translateHexColorCodes("&cTa komenda jest dostępna tylko dla graczy"));
			return;
		}

		if (args.length < 2) {
			ChatUtil.sendMessage(sender, AuthPlugin.getPlugin().getConfiguration().message_usage.replace("{USAGE}", "/msg [nick] <wiadomosc>"));
			return;
		}

		ProxiedPlayer senderPlayer = (ProxiedPlayer) sender;
		ProxiedPlayer targetPlayer = AuthPlugin.getPlugin().getProxy().getPlayer(args[0]);

		if (targetPlayer == null) {
			ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
			return;
		}

		String messageContent = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

		String senderName = senderPlayer.getName();
		String targetName = targetPlayer.getName();

		// Ustaw odpowiedni UUID odbiorcy w mapie replyMap
		messageManager.setReplyTarget(senderPlayer.getUniqueId(), targetPlayer.getUniqueId());
		messageManager.setReplyTarget(targetPlayer.getUniqueId(), senderPlayer.getUniqueId());

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