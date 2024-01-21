package pl.textr.survival.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.survival.utils.ChatUtil;

@CommandInfo(name = "glow", description = "Komenda do nadawania efektu glow", usage = "/glow", permission = "core.cmd.user")
public class AdminGlowCommand extends PlayerCommandExecutor {
	
	
	
	
    @Override
    public boolean onCommand(final Player player, final String[] args) {

        if (!player.hasPermission("core.premium")) {
            ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&7Ta czynność jest dostępna tylko dla rangi &6premium"));
            return true;
        }

        if (player.isGlowing()) {
            player.setGlowing(false);
            ChatUtil.sendMessage(player, "&cPomyślnie usunięto efekt glow");
        } else {
            player.setGlowing(true);
            ChatUtil.sendMessage(player, "&aPomyślnie nadano efekt glow");
        }

        return true;
    }
}