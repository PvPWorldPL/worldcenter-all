package pl.textr.boxpvp.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.Clans;
import api.managers.ClanManager;
import api.menu.ColorsTagMenu;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "kolorowytag", description = "Komenda do zmiany kolorowego tagu", usage = "/kolorowytag", permission = "core.cmd.user")
public class KolorowyTagCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player player, final String[] args) {
        final Player p = player;
        
        final Clans g = ClanManager.getGuild(p);

        if (g == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie posiadasz klanu!");
        }
        if (!g.isOwner(p)) {
            return ChatUtil.sendMessage(p, "&8&l[&C&l!&8&l] &cNie jestes zalozycielem klanu!");
        }
        
        ColorsTagMenu.show(player);
        return true;
    }
}
