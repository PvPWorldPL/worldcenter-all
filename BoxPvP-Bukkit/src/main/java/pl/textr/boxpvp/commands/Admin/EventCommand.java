package pl.textr.boxpvp.commands.Admin;


import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import api.redis.BroadcastType;
import api.redis.packet.broadcast.BroadcastRangiDropPacket;
import api.redis.packet.broadcast.BroadcastTurboDropPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.DataUtil;

@CommandInfo(name = "event", description = "Komenda do eventow", usage = "/event <rangi>|<drop> [czas]", permission = "core.cmd.headadmin")
public class  EventCommand extends BaseCommand {
    



    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        if (!args[0].equalsIgnoreCase("rangi") && !args[0].equalsIgnoreCase("drop")){
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        final long time = DataUtil.parseDateDiff(args[1], true);
        if (args[0].equalsIgnoreCase("rangi")) {
        	Main.getPlugin().getConfiguration().vouchery = time;
        	BroadcastRangiDropPacket BroadcastEventyPacket;
        	BroadcastEventyPacket = new BroadcastRangiDropPacket(BroadcastType.TURBOVOUCHERY_CHAT, time);
            Main.getPlugin().getRedisService().publishAsync("BroadcastRangiDrop", BroadcastEventyPacket);
          //  Main.getPlugin().getRedisService().publishAsync("BroadcastTurboRangi", BroadcastEventyPacket);
            
        	}
       if (args[0].equalsIgnoreCase("drop")) {
        	Main.getPlugin().getConfiguration().turbodrop = time;
        	BroadcastTurboDropPacket BroadcastEventyPacket;
        	BroadcastEventyPacket = new BroadcastTurboDropPacket(BroadcastType.TURBODROP_CHAT, time);
         //   Main.getPlugin().getRedisService().publishAsync("BroadcastTurboDrop", BroadcastEventyPacket);
            Main.getPlugin().getRedisService().publishAsync("BroadcastTurboDrop", BroadcastEventyPacket);
            
        	}
        return false;
    }


}