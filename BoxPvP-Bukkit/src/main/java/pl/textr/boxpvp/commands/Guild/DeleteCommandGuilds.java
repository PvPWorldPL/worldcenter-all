package pl.textr.boxpvp.commands.Guild;

import api.regions.WorldGuardRegionHelper;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.Clans;
import api.managers.ClanManager;
import api.redis.packet.guild.UpdateGuildDeletePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "usun", description = "Usuwanie Klan", usage = "/usun", permission = "core.cmd.user")
public class DeleteCommandGuilds extends PlayerCommandExecutor {
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Clans g = ClanManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage(p, "&8&l[&C&l!&8&l] &cNie posiadasz Klanu!");
        }
        if (!g.isOwner(p)) {
            return ChatUtil.sendMessage(p, "&8&l[&C&l!&8&l] &cNie jestes zalozycielem klanu!");
        }

        if (WorldGuardRegionHelper.isPlayerInAnyRegion(p.getUniqueId(), "crystal")) {
            return ChatUtil.sendMessage(p, "&8&l[&C&l!&8&l] &cNie możesz usunąć klanu będąc w obszarze crystal!");
        }

        UpdateGuildDeletePacket UpdateGuildDeletePacket;
        UpdateGuildDeletePacket = new UpdateGuildDeletePacket(g.getTag(), p.getName());
        Main.getPlugin().getRedisService().publishAsync("UpdateGuildDelete", UpdateGuildDeletePacket);        
       return true;
    }
}
