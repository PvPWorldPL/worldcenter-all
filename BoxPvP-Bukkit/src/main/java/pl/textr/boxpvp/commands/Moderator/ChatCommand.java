package pl.textr.boxpvp.commands.Moderator;

import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import api.redis.packet.chat.ChatClearPacket;
import api.redis.packet.chat.ChatDisablePacket;
import api.redis.packet.chat.ChatEnablePacket;
import api.redis.packet.chat.ChatRankingPacket;
import api.redis.packet.chat.ChatSlowPacket;
import api.redis.packet.chat.ChatVipDisablePacket;
import api.redis.packet.chat.ChatVipEnablePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "chat", description = "Zarządzanie chatem", usage = "/chat <cc|on|off|<vipon>|<vipoff>|<slow>| <ranking>", permission = "core.cmd.helper", aliases = {"czat"})
public class ChatCommand extends BaseCommand {

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }

        if (args[0].equalsIgnoreCase("cc")) {
            ChatClearPacket chatClearPacket = new ChatClearPacket("&7Chat zostal &awyczyszczony");
            Main.getPlugin().getRedisService().publishAsync("ChatClear", chatClearPacket);
            return true;
        }

        if (args[0].equalsIgnoreCase("on")) {
            if (Main.getPlugin().getConfiguration().ChatEnable()) {
                return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Chat jest juz wlaczony!");
            }
            ChatEnablePacket chatEnablePacket = new ChatEnablePacket("&7Chat zostal &awlaczony");
            Main.getPlugin().getRedisService().publishAsync("ChatEnable", chatEnablePacket);
            return true;
        }

        if (args[0].equalsIgnoreCase("off")) {
            if (!Main.getPlugin().getConfiguration().ChatEnable()) {
                return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Chat jest wylaczony!");
            }
            ChatDisablePacket chatDisablePacket = new ChatDisablePacket("&7Chat zostal &cwylaczony");
            Main.getPlugin().getRedisService().publishAsync("ChatDisable", chatDisablePacket);
            return true;
        }

        if (args[0].equalsIgnoreCase("vipon")) {
            ChatVipEnablePacket chatVipEnablePacket = new ChatVipEnablePacket("&7Chat dla vipow zostal &awlaczony");
            Main.getPlugin().getRedisService().publishAsync("ChatVipEnable", chatVipEnablePacket);
            return true;
        }

        if (args[0].equalsIgnoreCase("vipoff")) {
            ChatVipDisablePacket chatVipDisablePacket = new ChatVipDisablePacket("&7Chat dla vipow zostal &cwylaczony");
            Main.getPlugin().getRedisService().publishAsync("ChatVipDisable", chatVipDisablePacket);
            return true;
        }

        if (args[0].equalsIgnoreCase("slow")) {
            if (args.length < 2) {
                return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage("/chat slow <czas w sekundach>"));
            }

            if (!ChatUtil.isInteger(args[1])) {
                return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7To nie jest liczba");
            }

            final int slow = Integer.parseInt(args[1]);
            ChatSlowPacket chatSlowPacket = new ChatSlowPacket(slow);
            Main.getPlugin().getRedisService().publishAsync("ChatSlow", chatSlowPacket);
            return ChatUtil.sendMessage(sender, "&7Ustawiles slow chatu na &r" + slow + " &7sek!");
        }

        if (args[0].equalsIgnoreCase("ranking")) {
            if (args.length < 2) {
                return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage("/chat ranking <ilosc>"));
            }

            if (!ChatUtil.isInteger(args[1])) {
                return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7To nie liczba!");
            }

            final int amount = Integer.parseInt(args[1]);
            ChatRankingPacket chatRankingPacket = new ChatRankingPacket(amount);
            Main.getPlugin().getRedisService().publishAsync("ChatRanking", chatRankingPacket); 
            return true;
        }

        return false;
    }
}
