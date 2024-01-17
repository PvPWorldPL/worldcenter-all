package pl.textr.boxpvp.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.managers.KillContractManager;
import api.menu.ZleceniodawcaMenu;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "zleceniodawca", description = "Otwórz menu zleceniodawcy", usage = "/zleceniodawca", permission = "core.cmd.user")
public class ZleceniodawcaCommand extends PlayerCommandExecutor {


    @Override
    public boolean onCommand(final Player player, final String[] args) {
        KillContractManager contractManager = Main.getPlugin().getKillContractManager();

        if (!contractManager.hasContracts(player.getUniqueId())) {
            player.sendMessage(ChatUtil.translateHexColorCodes("&cNie masz żadnych zleceń do wyświetlenia."));
            return true;
        }

        ZleceniodawcaMenu.openZleceniodawcaGUI(player);
        return true;
    }
}
