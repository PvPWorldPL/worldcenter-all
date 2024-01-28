package pl.textr.boxpvp.commands.HeadAdmin;

import api.redis.BroadcastType;
import api.redis.packet.broadcast.BroadcastItemShopPacket;
import api.redis.packet.server.ServerRefreshConfigurationPacket;
import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "core", description = "Informacje o Core", usage = "/core <reload>", permission = "core.cmd.dev")
public class ConfigCommand extends BaseCommand {

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        final String option = args[0];
        if ("reload".equals(option)) {
        	Main.getPlugin().getConfiguration().load();
            ServerRefreshConfigurationPacket ServerRefreshConfigurationPacket;
            ServerRefreshConfigurationPacket = new ServerRefreshConfigurationPacket();
            Main.getPlugin().getRedisService().publishAsync("ServerRefreshConfiguration", ServerRefreshConfigurationPacket);
            return ChatUtil.sendMessage(sender, "&aKonfiguracja zostala przeladowana!");

        }

        return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
    }
}