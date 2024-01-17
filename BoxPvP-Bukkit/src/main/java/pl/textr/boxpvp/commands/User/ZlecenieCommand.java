package pl.textr.boxpvp.commands.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.managers.KillContractManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "zlecenie", description = "Udziel zlecenia innemu graczowi", usage = "/zlecenie [nick]", permission = "core.cmd.user")
public class ZlecenieCommand extends PlayerCommandExecutor {


    @Override
    public boolean onCommand(final Player player, final String[] args) {
        if (args.length != 1) {
            return ChatUtil.sendMessage(player, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }

        Player cel = Bukkit.getPlayer(args[0]);

        if (cel == null) {
            return ChatUtil.sendMessage(player, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
        }

        if (player.getUniqueId().equals(cel.getUniqueId())) {
            return ChatUtil.sendMessage(player, "&cNie możesz zlecić zabójstwa sobie!");
        }

        KillContractManager contractManager = Main.getPlugin().getKillContractManager();
        contractManager.addContract(player.getUniqueId(), cel.getUniqueId()); // Dodaj zlecenie zabójstwa
        player.sendMessage(ChatUtil.translateHexColorCodes("&aPomyślnie zleciłeś zabójstwo graczowi " + cel.getName()));
        cel.sendMessage(ChatUtil.translateHexColorCodes("&7Gracz &f" + player.getName() + " &7zlecił ci zabójstwo  &7Jeśli je wykonasz w ciągu 10 min to otrzymasz nagrodę!"));

        return true;
    }
}
