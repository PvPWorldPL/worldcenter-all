package pl.textr.boxpvp.commands.Guild;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.Clans;
import api.data.UserProfile;
import api.managers.ClanManager;
import api.managers.UserAccountManager;
import api.redis.packet.guild.UpdateGuildOwnerPacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "lider", description = "Zmienianie lidera gildii", usage = "/lider <nick>", permission = "core.cmd.user")
public class OwnerCommandGuilds extends PlayerCommandExecutor {
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Clans g = ClanManager.getGuild(p);
        final UserProfile u1 = UserAccountManager.getUser(p);
        if (g == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie posiadasz klanu!");
        }
        if (!g.isOwner(p)) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie jestes zalozycielem klanu!");
        }
        if (args.length != 1) {
            return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        if (g.isOwner(u1.getName())) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie możesz przekazać roli zalozyciela samemu sobie!");
        }

 

        final UserProfile u2 = UserAccountManager.getUser(args[0]);
        if (u2 == null) {
        	   return ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes("&#f4493eG&#f4493er&#f4493ea&#f4493ec&#f4493ez &#f4493ej&#f4493ee&#f4493es&#f4493et &#f4493eo&#f4493ef&#f4493ef&#f4493el&#f4493ei&#f4493en&#f4493ee &#f4493el&#f4493eu&#f4493eb &#f4493en&#f4493ei&#f4493ee &#f4493ez&#f4493en&#f4493ea&#f4493el&#f4493ee&#f4493ez&#f4493ei&#f4493eo&#f4493en&#f4493eo &#f4493eg&#f4493eo &#f4493ew &#f4493eb&#f4493ea&#f4493ez&#f4493ei&#f4493ee &#f4493ed&#f4493ea&#f4493en&#f4493ey&#f4493ec&#f4493eh&#f4493e!"));
                 }
        if (!g.isMember(u2.getName())) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz nie jest w twoim klanie!");
        }
 
        if (g.isOwner(u2.getName())) {
            g.setOwner("null");
            return ChatUtil.sendMessage(p,  "&r" + u2.getName() + " &7nie jest juz liderem klanu &r" + g.getTag() + "");
        }
      
        UpdateGuildOwnerPacket UpdateGuildOwnerPacket;
        UpdateGuildOwnerPacket = new UpdateGuildOwnerPacket(g.getTag(), u2.getName());
        Main.getPlugin().getRedisService().publishAsync("UpdateGuildOwner", UpdateGuildOwnerPacket); 
       return true;
    }
}
