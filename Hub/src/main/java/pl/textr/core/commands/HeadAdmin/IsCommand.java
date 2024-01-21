package pl.textr.core.commands.HeadAdmin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import api.packet.BroadcastItemShopPacket;
import api.redis.BroadcastType;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.commands.Api.Command;
import pl.textr.core.utils.other.ChatUtil;

public class IsCommand extends Command {
    public IsCommand() {
        super("is", "Wiadomosc o zakupie", "/is <gracz> sponsor|vip|zwyklyklucz|rzadkiklucz|epickiklucz ilosc|unban|By|", "core.cmd.admin");
    }

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
            
        }
        final String name = args[0];
        final String s = args[1];
        switch (s) {
            case "vip": {
            	BroadcastItemShopPacket BroadcastItemShopPacket;
            	BroadcastItemShopPacket = new BroadcastItemShopPacket(BroadcastType.CHAT_ITEMSHOP_VIP, name,null);
            	LobbyPlugin.getPlugin().getRedisService().publishAsync("broadcastItemShop", BroadcastItemShopPacket);          
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " group set vip");
                return true;
            }
            case "sponsor": {
            	BroadcastItemShopPacket BroadcastItemShopPacket;
            	BroadcastItemShopPacket = new BroadcastItemShopPacket(BroadcastType.CHAT_ITEMSHOP_SPONSOR, name, null);
            	LobbyPlugin.getPlugin().getRedisService().publishAsync("broadcastItemShop", BroadcastItemShopPacket);               
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " group set sponsor");              
                return true;
            }
            case "svip": {
            	BroadcastItemShopPacket BroadcastItemShopPacket;
            	BroadcastItemShopPacket = new BroadcastItemShopPacket(BroadcastType.CHAT_ITEMSHOP_SVIP, name, null);
               LobbyPlugin.getPlugin().getRedisService().publishAsync("broadcastItemShop", BroadcastItemShopPacket);               
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " group set svip");              
                return true;
            }
                    
            default: {
                return false;
            }
        }
    }
}

