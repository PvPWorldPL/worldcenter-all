package pl.textr.boxpvp.commands.HeadAdmin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import api.redis.BroadcastType;
import api.redis.packet.broadcast.BroadcastItemShopPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "is", description = "Wiadomość o zakupie", usage = "/is <gracz> sponsor|vip|svip|zwyklyklucz|rzadkiklucz|epickiklucz ilość|unban|By", permission = "core.cmd.admin")
public class IsCommand extends BaseCommand {
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
            
        }
        final String name = args[0];
        final String s = args[1];
        switch (s) {
            case "vip": {
            	BroadcastItemShopPacket BroadcastItemShopPacket;
            	BroadcastItemShopPacket = new BroadcastItemShopPacket(BroadcastType.CHAT_ITEMSHOP_VIP, name,null);
                Main.getPlugin().getRedisService().publishAsync("broadcastItemShop", BroadcastItemShopPacket);          
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " group set vip");
                return true;
            }
            case "sponsor": {
            	BroadcastItemShopPacket BroadcastItemShopPacket;
            	BroadcastItemShopPacket = new BroadcastItemShopPacket(BroadcastType.CHAT_ITEMSHOP_SPONSOR, name, null);
                Main.getPlugin().getRedisService().publishAsync("broadcastItemShop", BroadcastItemShopPacket);               
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " group set sponsor");              
                return true;
            }
            case "svip": {
            	BroadcastItemShopPacket BroadcastItemShopPacket;
            	BroadcastItemShopPacket = new BroadcastItemShopPacket(BroadcastType.CHAT_ITEMSHOP_SVIP, name, null);
                Main.getPlugin().getRedisService().publishAsync("broadcastItemShop", BroadcastItemShopPacket);               
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " group set svip");              
                return true;
            }
            case "zwyklyklucz": {
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage("/is <nick> zwyklyklucz <ilosc>"));
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&cTo nie liczba!");
                }
                final int amount = Integer.parseInt(args[2]);        
                BroadcastItemShopPacket BroadcastItemShopPacket;
                BroadcastItemShopPacket = new BroadcastItemShopPacket(BroadcastType.CHAT_ITEMSHOP_ZWYKLYKLUCZ, name,amount);
                Main.getPlugin().getRedisService().publishAsync("broadcastItemShop", BroadcastItemShopPacket);                
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give physical zwykla " + amount + " " + name);
                return true;
            }
            case "rzadkiklucz": {
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage("/is <nick> rzadkiklucz <ilosc>"));
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&cTo nie liczba!");
                }
                final int amount = Integer.parseInt(args[2]);       
                BroadcastItemShopPacket BroadcastItemShopPacket;
                BroadcastItemShopPacket = new BroadcastItemShopPacket(BroadcastType.CHAT_ITEMSHOP_RZADKIKLUCZ, name,amount);
                Main.getPlugin().getRedisService().publishAsync("broadcastItemShop", BroadcastItemShopPacket);             
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give physical rzadka " + amount + " " + name);
                return true;
            }
            case "epickiklucz": {
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage("/is <nick> epickiklucz <ilosc>"));
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&cTo nie liczba!");
                }
                final int amount = Integer.parseInt(args[2]);
                BroadcastItemShopPacket BroadcastItemShopPacket;
                BroadcastItemShopPacket = new BroadcastItemShopPacket(BroadcastType.CHAT_ITEMSHOP_EPICKIKLUCZ, name,amount);
                Main.getPlugin().getRedisService().publishAsync("broadcastItemShop", BroadcastItemShopPacket);                           
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc give physical epicka " + amount + " " + name);
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

