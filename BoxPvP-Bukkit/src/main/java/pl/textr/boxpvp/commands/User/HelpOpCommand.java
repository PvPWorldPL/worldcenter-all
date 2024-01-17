package pl.textr.boxpvp.commands.User;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.redis.packet.broadcast.BroadcastHelpopPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "helpop", description = "Wiadomość do administracji", usage = "/helpop <wiadomość>", permission = "core.cmd.user")
public class HelpOpCommand extends PlayerCommandExecutor {
    private static final HashMap<UUID, Long> times = new HashMap<>();
    

    public boolean onCommand(final Player player, final String[] args) {
        if (args.length == 0) {
            return ChatUtil.sendMessage(player, Main.getPlugin().getConfiguration().usage(this.getUsage()));
            
        }
        final Long t = HelpOpCommand.times.get(player.getUniqueId());
        if (t != null && System.currentTimeMillis() - t < 30000L) {
            return ChatUtil.sendMessage(player, "&8[&C&l!&8] &cZgloszenia mozna pisac co 30 sekund!");
        }
        final String msg = StringUtils.join(args, " ");
        HelpOpCommand.times.put(player.getUniqueId(), System.currentTimeMillis());
        player.sendMessage(ChatUtil.fixColor("&7Pomyslnie wyslano zgloszenie o tresci: &a" + msg));
        BroadcastHelpopPacket BroadcastHelpopPacket;
        BroadcastHelpopPacket = new BroadcastHelpopPacket(player.getName(), Main.getPlugin().getConfiguration().boxpvpName(), msg);
        Main.getPlugin().getRedisService().publishAsync("broadcastHelpop", BroadcastHelpopPacket);
        //DiscordWebhookUtil.SendWarningHelpop(message);
        ChatUtil.sendTitle(player, "", ChatUtil.fixColor("&aPomyslnie wyslano zgloszenie!"), 30, 50, 10);
        return true;
    }
}
