package pl.textr.core.commands.Moderator;

import org.bukkit.command.CommandSender;

import api.packet.ChatClearPacket;
import api.packet.ChatDisablePacket;
import api.packet.ChatEnablePacket;
import api.packet.ChatVipDisablePacket;
import api.packet.ChatVipEnablePacket;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.commands.Api.Command;
import pl.textr.core.utils.other.ChatUtil;

public class ChatCommand extends Command {
    private String player;

    public ChatCommand() {
        super("chat", "zarzadzanie chatem", "/chat <cc|on|off|<vipon>|<vipoff>|", "core.cmd.helper", "czat");
    }

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
        }

        if (args[0].equalsIgnoreCase("cc")) {
            ChatClearPacket chatClearPacket = new ChatClearPacket("&7Chat zostal &awyczyszczony");
            LobbyPlugin.getPlugin().getRedisService().publishAsync("clearchat", chatClearPacket);
            return true;
        }

        if (args[0].equalsIgnoreCase("on")) {
            if (LobbyPlugin.getPlugin().getConfiguration().ChatEnable) {
                return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Chat jest juz wlaczony!");
            }
            ChatEnablePacket chatEnablePacket = new ChatEnablePacket("&7Chat zostal &awlaczony");
            LobbyPlugin.getPlugin().getRedisService().publishAsync("Chaton", chatEnablePacket);
            return true;
        }

        if (args[0].equalsIgnoreCase("off")) {
            if (!LobbyPlugin.getPlugin().getConfiguration().ChatEnable) {
                return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Chat jest wylaczony!");
            }
            ChatDisablePacket chatDisablePacket = new ChatDisablePacket("&7Chat zostal &cwylaczony");
            LobbyPlugin.getPlugin().getRedisService().publishAsync("Chatoff", chatDisablePacket);
            return true;
        }

        if (args[0].equalsIgnoreCase("vipon")) {
            ChatVipEnablePacket chatVipEnablePacket = new ChatVipEnablePacket("&7Chat dla vipow zostal &awlaczony");
            LobbyPlugin.getPlugin().getRedisService().publishAsync("ChatonVip", chatVipEnablePacket);
            return true;
        }

        if (args[0].equalsIgnoreCase("vipoff")) {
            ChatVipDisablePacket chatVipDisablePacket = new ChatVipDisablePacket("&7Chat dla vipow zostal &cwylaczony");
            LobbyPlugin.getPlugin().getRedisService().publishAsync("ChatoffVip", chatVipDisablePacket);
            return true;
        }


        return false;
    }
}
