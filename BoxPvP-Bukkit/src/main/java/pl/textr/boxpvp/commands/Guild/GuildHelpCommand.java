package pl.textr.boxpvp.commands.Guild;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "klan", description = "Info o klanach", usage = "/klan", permission = "core.cmd.user")
public class GuildHelpCommand extends PlayerCommandExecutor {
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            ChatUtil.sendMessage(p, "&7&m----------------------------------------------");
            ChatUtil.sendMessage(p, " &7/zaloz <tag> <nazwa> &8- &cZakladanie klanu");
            ChatUtil.sendMessage(p, " &7/zapros <gracz> &8- &cZapraszanie gracza do klanu");
            ChatUtil.sendMessage(p, " &7/usun &8- &cUsuwanie gildii");
            ChatUtil.sendMessage(p, " &7/kinfo [tag] &8- &cPokazuje informacje o klanu");
            ChatUtil.sendMessage(p, " &7/dolacz <tag> &8- &cDolaczanie do gildii");
            ChatUtil.sendMessage(p, " &7/wyrzuc <gracz> &8- &cWyrzucanie gracza z klanu");
            ChatUtil.sendMessage(p, " &7/opusc &8- &cOpuszczanie gildii");
            ChatUtil.sendMessage(p, " &7/lider <nick> &8- &cZmienianie zalozyciela klanu");

            return ChatUtil.sendMessage(p, "&7&m----------------------------------------------");
        }
        return false;
    }
}
