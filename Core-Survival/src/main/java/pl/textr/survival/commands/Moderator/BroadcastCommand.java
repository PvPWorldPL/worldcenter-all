package pl.textr.survival.commands.Moderator;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import api.redis.BroadcastType;
import api.redis.packet.broadcast.BroadcastPacket;
import pl.textr.survival.Main;
import pl.textr.survival.utils.ChatUtil;

@CommandInfo(name = "broadcast", description = "Ogłoszenie do graczy", usage = "/broadcast <title/sb>/actionbar/chat>", permission = "core.cmd.moderator", aliases = {"bc", "bcast"})
public class BroadcastCommand extends BaseCommand {

    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
            
        }
        if (args[0].equalsIgnoreCase("chat")) {
            BroadcastPacket broadcastPacket;
            broadcastPacket = new BroadcastPacket(BroadcastType.CHAT, StringUtils.join(args, " ").replace("chat", ""));
            Main.getPlugin().getRedisService().publishAsync("broadcast", broadcastPacket);           
    }
    if (args[0].equalsIgnoreCase("actionbar")) {
            BroadcastPacket broadcastPacket;
            broadcastPacket = new BroadcastPacket(BroadcastType.ACTIONBAR, StringUtils.join(args, " ").replace("actionbar", ""));
            Main.getPlugin().getRedisService().publishAsync("broadcast", broadcastPacket);
    }
    if (args[0].equalsIgnoreCase("title")) {

            BroadcastPacket broadcastPacket;
            broadcastPacket = new BroadcastPacket(BroadcastType.TITLE, StringUtils.join(args, " ").replace("title", ""));
            Main.getPlugin().getRedisService().publishAsync("broadcast", broadcastPacket);
    }
    if (args[0].equalsIgnoreCase("sb")) {
        BroadcastPacket broadcastPacket;
        broadcastPacket = new BroadcastPacket(BroadcastType.SUBTITLE, StringUtils.join(args, " ").replace("sb", ""));
        Main.getPlugin().getRedisService().publishAsync("broadcast", broadcastPacket);
}
    return true;
}
}
