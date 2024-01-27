package pl.textr.boxpvp.commands.Guild;

import api.regions.WorldGuardRegionHelper;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.Clans;
import api.managers.ClanManager;
import api.redis.packet.guild.UpdateGuildAddPlayerPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
@CommandInfo(name = "dolacz", description = "Dolaczanie do gildii", usage = "/dolacz <tag/nazwa>", permission = "core.cmd.user")
public class JoinCommandGuilds extends PlayerCommandExecutor {
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {

        final Clans clans = ClanManager.getGuild(p);
  
        if (clans != null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cPosiadasz juz klan!");
        }
        if (args.length != 1) {
            return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        final Clans g = ClanManager.getGuild(args[0]);
        if (g == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cKlan o takim tagu nie istnieje!");
        }
        if (g.getMembers().size() >= 30) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cTen klan  posiada juz maksymalna ilosc czlonkow! (30 osob)");
        }

        if (WorldGuardRegionHelper.isPlayerInAnyRegion(p.getUniqueId(), "crystal")) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie mozesz dolaczyc do klanu będac w obszarze crystal!");
        }
        if (!g.getInvites().contains(p)) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie posiadasz zaproszenia do klanu " + g.getTag() + "!");
        }
     
        g.getInvites().remove(p);
       // g.addMember(p.getName());
        UpdateGuildAddPlayerPacket UpdateGuildAddPlayerPacket;
        UpdateGuildAddPlayerPacket = new UpdateGuildAddPlayerPacket(String.valueOf(g.getTag()), p.getName());
        Main.getPlugin().getRedisService().publishAsync("UpdateGuildAddPlayer", UpdateGuildAddPlayerPacket);  
     
        return true;  		
  }
}

