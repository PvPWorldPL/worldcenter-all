package pl.textr.boxpvp.listeners.chat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import api.data.Clans;
import api.data.UserProfile;
import api.managers.ClanManager;
import api.managers.UserAccountManager;
import api.redis.packet.chat.ChatPacket;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.md_5.bungee.api.ChatColor;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.DataUtil;
import pl.textr.boxpvp.utils.TimeUtil;

public class AsyncPlayerChatListener implements Listener {

    LuckPerms api = LuckPermsProvider.get();

    @EventHandler(priority = EventPriority.LOW)
    public void onChat(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
     
        net.luckperms.api.model.user.User lpuser = api.getUserManager().getUser(p.getUniqueId());
        Collection<Group> inheritedGroups = lpuser.getInheritedGroups(lpuser.getQueryOptions());
        final Clans g1 = ClanManager.getGuild(p);
        final UserProfile u = UserAccountManager.getUser(p);
        final String prefix = lpuser.getCachedData().getMetaData().getPrefix();

        if (ChatUtil.isBlocked(e.getMessage()) && (!p.hasPermission("core.cmd.helper"))) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNiektorych slow nie wolno uzywac na naszym serwerze");
            e.setCancelled(true);
            return;
        }
        if (!p.hasPermission("core.cmd.helper") &&  Main.getPlugin().getConfiguration().ranking() > u.getPoints()) {
            ChatUtil.sendMessage(p, "&8[&c!&8] &7Aktualnie korzystanie z chatu moga osoby powyzej &7" +  Main.getPlugin().getConfiguration().ranking() + " punktow rankingu");
            e.setCancelled(true);
            return;
        }
        if (!p.hasPermission("core.premium") && Main.getPlugin().getConfiguration().VipChatEnable()) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Chat jest dostepny tylko dla rangi &6premium");
            e.setCancelled(true);
            return;
        }
        if (!p.hasPermission("core.cmd.helper") && !Main.getPlugin().getConfiguration().ChatEnable() && !Main.getPlugin().getConfiguration().VipChatEnable()) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Chat jest aktualnie wylaczony!");
            e.setCancelled(true);
            return;
        }
        if (!p.hasPermission("core.cmd.helper") && !u.isChat()) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Na czacie bedziesz mogl pisac dopiero za &f" + DataUtil.secondsToString(u.getLastChat()) + "");
            e.setCancelled(true);
            return;
        }

        String globalFormat = ChatUtil.fixColor(Main.getPlugin().getConfiguration().ChatFormatGlobal());
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("helper"))) {
        globalFormat = ChatUtil.fixColor(Main.getPlugin().getConfiguration().ChatFormatHelper());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("player"))) {
        globalFormat = ChatUtil.fixColor(Main.getPlugin().getConfiguration().ChatFormatPlayer());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("tHELPER"))) {
        globalFormat = ChatUtil.fixColor(Main.getPlugin().getConfiguration().ChatFormatTHelper());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("moderator"))) {
        globalFormat = ChatUtil.fixColor(Main.getPlugin().getConfiguration().ChatFormatModerator());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("admin"))) {
        globalFormat = ChatUtil.fixColor(Main.getPlugin().getConfiguration().ChatFormatAdmin());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("wlasciciel"))) {
        globalFormat = ChatUtil.fixColor(Main.getPlugin().getConfiguration().ChatFormatwlasciciel());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("headadmin"))) {
        globalFormat = ChatUtil.fixColor(Main.getPlugin().getConfiguration().ChatFormatHeadAdmin());
        
        }
         u.setLastChat(System.currentTimeMillis() + TimeUtil.SECOND.getTime(Main.getPlugin().getConfiguration().chatslowmode()));
         globalFormat = globalFormat.replace("{PREFIX}", prefix);
         globalFormat = globalFormat.replace("{TAG}", (g1 != null) ? getFormattedTag(g1) : "");
        globalFormat = globalFormat.replace("{PLAYER}", UserAccountManager.getUser(p.getName()).isTeczowy() ? this.getRainbow(p) : p.getName());
        globalFormat = globalFormat.replace("{MESSAGE}", e.getMessage());
        e.setCancelled(true);   
        if (!e.getMessage().startsWith("!"))   {
        ChatPacket chatpacket;
        chatpacket = new ChatPacket(ChatUtil.translateHexColorCodes(globalFormat));
        Main.getPlugin().getRedisService().publishAsync("chatGlobal", chatpacket);
        return;
        }
    }

   
    public String getFormattedTag(final Clans g) {
        String formattedTag = g.getTag(); 
        if (g.isColorTag() != null) {
            String colorTag = g.isColorTag();
            try {
                ChatColor tagColor = ChatColor.of(colorTag);
                formattedTag = tagColor + "&l" + formattedTag;
            } catch (IllegalArgumentException e) {
               
            }
        }

        return formattedTag;
    }

    public String getRainbow(Player p) {
        String playernickname = p.getName();
        StringBuilder rainbow_nick = new StringBuilder(playernickname.length() * 10);
        Random r = new Random();

        for (char c : playernickname.toCharArray()) {
            int randomColorCode = r.nextInt(9) + 1;
            rainbow_nick.append("&").append(randomColorCode).append(c);
        }

        return rainbow_nick.toString();
    }



}