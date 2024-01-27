package pl.textr.boxpvp.commands.Guild;

import api.regions.WorldGuardRegionHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.Clans;
import api.data.UserProfile;
import api.managers.ClanManager;
import api.managers.UserAccountManager;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "zapros", description = "Zapraszanie gracza do gildii", usage = "/zapros <gracz>", permission = "core.cmd.user")
public class InviteCommandGuilds extends PlayerCommandExecutor {
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Clans g = ClanManager.getGuild(p);

        if (g == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie posiadasz klanu!");
        }
        if (!g.isOwner(p) && !g.isOwner(p.getName())) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie jestes liderem klanu!");
        }
        if (g.getMembers().size() >= 30) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cTwoj klan posiada juz maksymalna ilosc czlonkow! &8(&f30 osob&8)");
        }
        if (args.length != 1) {
            return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        final UserProfile o = UserAccountManager.getUser(args[0]);
        if (o == null) {
     
        	return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");     
        	}
        if (o.getPlayer() == null) {
        return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");  
        }
        
        if (p.getName().equalsIgnoreCase(o.getName())) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie możesz zaprosić samego siebie!");
        }



        final Clans go = ClanManager.getGuild(o.getPlayer());
        if (go != null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz posiada juz klan!");
        }
        if (g.getInvites().contains(o.getPlayer())) {
            g.getInvites().remove(o.getPlayer());
            ChatUtil.sendMessage(o.getPlayer(), "&8[&C&l!&8] &7Zaproszenie do klanu &r" + g.getTag() + " &7zostalo cofniete przez &c" + p.getName() + "&c!");
               
        return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Cofnales zaproszenie do klanu dla gracza &c" + o.getName() + "&c!");
        }
      Player player = Bukkit.getPlayer(o.getName());  
      g.getInvites().add(player);   
       ChatUtil.sendMessage(o.getPlayer(), "&7Zostales zaproszony do klanu: &r" + g.getTag() + "\n &7Aby zaakceptowac wpisz: &r/dolacz " + g.getTag());

        return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Wyslales zaproszenie do klanu dla gracza &c" + o.getName() + "&7!");
        
    }
}
