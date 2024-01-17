 package pl.textr.boxpvp.commands.Guild;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.Clans;
import api.data.UserProfile;
import api.managers.ClanManager;
import api.managers.UserAccountManager;
import api.redis.packet.guild.UpdateGuildKickPlayerPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "wyrzuc", description = "Wyrzucanie gracza z gildii", usage = "/wyrzuc <gracz>", permission = "core.cmd.user")
public class KickCommandGuilds extends PlayerCommandExecutor {
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Clans g = ClanManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie posiadasz klanu!");
        }
        if (!g.isOwner(p)) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie jestes liderem klanu!");
        }
        if (args.length != 1) {
            return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        final UserProfile o = UserAccountManager.getUser(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");       }
        if (!g.isMember(o.getName())) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz nie jest w twoim klanie!");
        }
        if (p.getName().equals(o.getName())) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie mozesz wyrzucic samego siebie!");
        }
   
      //  g.removeMember(o.getName());
        UpdateGuildKickPlayerPacket UpdateGuildKickPlayerPacket;
        UpdateGuildKickPlayerPacket = new UpdateGuildKickPlayerPacket(String.valueOf(g.getTag()), o.getName());
        Main.getPlugin().getRedisService().publishAsync("UpdateGuildKickPlayer", UpdateGuildKickPlayerPacket);        
        return true;
    }
}
