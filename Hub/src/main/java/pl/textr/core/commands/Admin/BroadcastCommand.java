package pl.textr.core.commands.Admin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import api.packet.BroadcastPacket;
import api.redis.BroadcastType;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.commands.Api.Command;
import pl.textr.core.utils.other.ChatUtil;

public class BroadcastCommand extends Command
{
    public BroadcastCommand() {
        super("broadcast", "ogloszenie do graczy", "/broadcast <title/sb>/actionbar/chat>", "core.cmd.moderator", "bc", "bcast");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
            
        }
        if (args[0].equalsIgnoreCase("chat")) {
            BroadcastPacket broadcastPacket;
            broadcastPacket = new BroadcastPacket(BroadcastType.CHAT, StringUtils.join(args, " ").replace("chat", ""));
            LobbyPlugin.getPlugin().getRedisService().publishAsync("broadcastlobby", broadcastPacket);           
    }
    if (args[0].equalsIgnoreCase("actionbar")) {
            BroadcastPacket broadcastPacket;
            broadcastPacket = new BroadcastPacket(BroadcastType.ACTIONBAR, StringUtils.join(args, " ").replace("actionbar", ""));
            LobbyPlugin.getPlugin().getRedisService().publishAsync("broadcastlobby", broadcastPacket);
    }
    if (args[0].equalsIgnoreCase("title")) {

            BroadcastPacket broadcastPacket;
            broadcastPacket = new BroadcastPacket(BroadcastType.TITLE, StringUtils.join(args, " ").replace("title", ""));
            LobbyPlugin.getPlugin().getRedisService().publishAsync("broadcastlobby", broadcastPacket);
    }
    if (args[0].equalsIgnoreCase("sb")) {
        BroadcastPacket broadcastPacket;
        broadcastPacket = new BroadcastPacket(BroadcastType.SUBTITLE, StringUtils.join(args, " ").replace("sb", ""));
        LobbyPlugin.getPlugin().getRedisService().publishAsync("broadcastlobby", broadcastPacket);
}
    return true;
}
}
