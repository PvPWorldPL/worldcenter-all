package pl.textr.boxpvp.commands.HeadAdmin;

import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
@CommandInfo(name = "enable", description = "Komenda do włączania/wyłączania funkcji", usage = "/enable <afk>|<create>|<break>|<true/false>", permission = "core.cmd.admin")
public class EnableCommand extends BaseCommand {
    
    @Override
    public boolean onExecute(final CommandSender p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        switch (((((args[0]))))) {
            case "create": {
                final boolean check = Main.getPlugin().getConfiguration().enablecreate = Boolean.parseBoolean(args[1]);
                Main.getPlugin().getConfiguration().save();
                return ChatUtil.sendMessage(p, "&7Zakladnie klanow zostalo " + (check ? "&awlaczone" : "&cwylaczone"));
            }
            case "break": {
                final boolean check = Main.getPlugin().getConfiguration().budowanie = Boolean.parseBoolean(args[1]);
                Main.getPlugin().getConfiguration().save();
                return ChatUtil.sendMessage(p, "&7Budowanie i nieszczenie na mapie zostalo " + (check ? "&awlaczone" : "&cwylaczone"));
            }
            case "afk": {
                final boolean check = Main.getPlugin().getConfiguration().afk = Boolean.parseBoolean(args[1]);
                Main.getPlugin().getConfiguration().save();
                return ChatUtil.sendMessage(p, "&7Strefa afk zostala " + (check ? "&awlaczona" : "&cwylaczona"));
            }
            case "discord": {
                final boolean check = Main.getPlugin().getConfiguration().RewardsDiscord = Boolean.parseBoolean(args[1]);
                Main.getPlugin().getConfiguration().save();
                return ChatUtil.sendMessage(p, "&7DiscordRewards zostal " + (check ? "&awlaczony" : "&cwylaczony"));
            }
            default:
                break;
        }
        return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
    }
}
