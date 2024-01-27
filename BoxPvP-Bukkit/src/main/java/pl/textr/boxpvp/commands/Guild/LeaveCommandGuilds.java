package pl.textr.boxpvp.commands.Guild;

import api.regions.WorldGuardRegionHelper;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.Clans;
import api.managers.ClanManager;
import api.redis.packet.guild.UpdateGuildRemovePlayerPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "opusc", description = "Opuszczanie gildii", usage = "/opusc", permission = "core.cmd.user")
public class LeaveCommandGuilds extends PlayerCommandExecutor {
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Clans g = ClanManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie posiadasz klanu!");
        }
        if (g.isOwner(p)) {
            return ChatUtil.sendMessage(p, "&cTy jestes zalozycielem klanu nie mozesz go opuscic!");
        }

        if (WorldGuardRegionHelper.isPlayerInAnyRegion(p.getUniqueId(), "crystal")) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie możesz opuscic klanu będąc w obszarze crystal!");
        }


      //  g.removeMember(p.getName());
        UpdateGuildRemovePlayerPacket UpdateGuildRemovePlayerPacket;
        UpdateGuildRemovePlayerPacket = new UpdateGuildRemovePlayerPacket(String.valueOf(g.getTag()), p.getName());
        Main.getPlugin().getRedisService().publishAsync("UpdateGuildRemovePlayer", UpdateGuildRemovePlayerPacket);        
        return true;
    }
}
