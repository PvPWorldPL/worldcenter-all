package pl.textr.boxpvp.commands.HeadAdmin;
import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import api.redis.BroadcastType;
import api.redis.packet.player.ServerRefreshTopsRanking;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.tasks.TopPlayersNpc;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "updatetab", description = "Aktualizuje tab", usage = "/updatetab <refresh|sync>", permission = "core.cmd.dev")
public class UpdateTabCommand extends BaseCommand {

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }

        final String option = args[0];

        if ("refresh".equals(option)) {
            ServerRefreshTopsRanking ServerRefreshTopsRanking;
            ServerRefreshTopsRanking = new ServerRefreshTopsRanking(BroadcastType.TAB_UPDATE);
            Main.getPlugin().getRedisService().publishAsync("ServerRefreshTops", ServerRefreshTopsRanking);

        } else if ("sync".equals(option)) {
        	
            ServerRefreshTopsRanking ServerRefreshTopsRanking;
            ServerRefreshTopsRanking = new ServerRefreshTopsRanking(BroadcastType.TAB_SYNC);
            Main.getPlugin().getRedisService().publishAsync("ServerRefreshTops", ServerRefreshTopsRanking);
      
            
        } else {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        return true;
    }
}


