package pl.textr.boxpvp.commands.Admin;


import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import api.redis.BroadcastType;
import api.redis.packet.broadcast.BroadcastAchievementsPacket;
import api.redis.packet.broadcast.BroadcastCasePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "key", description = "Komenda do eventow", usage = "/key give <nick> <skrzynka> <ilosc> | all <skrzynka> <ilosc>", permission = "core.cmd.headadmin")
public class KeyGiveCommand extends BaseCommand {
    private List<String> dostepneSkrzynki = Arrays.asList("epicka", "rzadka", "zwykla", "czasu");



	@Override
	public boolean onExecute(final CommandSender sender, final String[] args) {
	    if (args.length < 1) {
	        return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
	    }

	    String subCommand = args[0];

	    if (subCommand.equalsIgnoreCase("give")) {
	        if (args.length < 4) {
	            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
	        }

	        String skrzynka = args[2];
	        String nick = args[1];
	        int ilosc = Integer.parseInt(args[3]);

	        if (!dostepneSkrzynki.contains(skrzynka)) {
	            return ChatUtil.sendMessage(sender, ChatUtil.fixColor("&8[&c&l!&8] &cNieprawidłowa skrzynka! Dostępne skrzynki: " + dostepneSkrzynki));
	        }

	        BroadcastCasePacket broadcastCasePacket = new BroadcastCasePacket(BroadcastType.CASE_PLAYER, nick, skrzynka, ilosc);
	        Main.getPlugin().getRedisService().publishAsync("BroadcastCase", broadcastCasePacket);
	       

	        return true;
	    } 
	    if (subCommand.equalsIgnoreCase("all")) {
	        if (args.length < 3) {
	            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
	        }

	        String skrzynka = args[1];
	        int ilosc = Integer.parseInt(args[2]);

	        if (!dostepneSkrzynki.contains(skrzynka)) {
	            return ChatUtil.sendMessage(sender, "&8[&c&l!&8] &cNieprawidłowa skrzynka! Dostępne skrzynki: " + dostepneSkrzynki);
	        }

	        BroadcastCasePacket broadcastCasePacket = new BroadcastCasePacket(BroadcastType.CASE_ALL, sender.getName(), skrzynka, ilosc);
	        Main.getPlugin().getRedisService().publishAsync("BroadcastCase", broadcastCasePacket);
	        return true;
	    }

	    return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
	}
}