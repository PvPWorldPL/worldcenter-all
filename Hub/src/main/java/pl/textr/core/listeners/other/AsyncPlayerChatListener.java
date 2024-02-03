package pl.textr.core.listeners.other;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import api.packet.ChatPacket;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import pl.textr.core.LobbyPlugin;
import pl.textr.core.utils.other.ChatUtil;

public class AsyncPlayerChatListener implements Listener {

    LuckPerms api = LuckPermsProvider.get();

    @EventHandler(priority = EventPriority.LOW)
    public void onChat(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();

        net.luckperms.api.model.user.User lpuser = api.getUserManager().getUser(p.getUniqueId());
        Collection<Group> inheritedGroups = lpuser.getInheritedGroups(lpuser.getQueryOptions());
        final String prefix = lpuser.getCachedData().getMetaData().getPrefix();

        if (ChatUtil.isBlocked(e.getMessage()) && (!p.hasPermission("core.cmd.helper"))) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNiektorych slow nie wolno uzywac na naszym serwerze");
            e.setCancelled(true);
            return;
        }
        if (!p.hasPermission("core.premium") && LobbyPlugin.getPlugin().getConfiguration().VipChatEnable()) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Chat jest dostepny tylko dla rangi &6premium");
            e.setCancelled(true);
            return;
        }
        if (!p.hasPermission("core.cmd.helper") && !LobbyPlugin.getPlugin().getConfiguration().ChatEnable() && !LobbyPlugin.getPlugin().getConfiguration().VipChatEnable()) {
            ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Chat jest aktualnie wylaczony!");
            e.setCancelled(true);
            return;
        }
        String globalFormat = ChatUtil.fixColor(LobbyPlugin.getPlugin().getConfiguration().ChatFormatGlobal());
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("helper"))) {
        globalFormat = ChatUtil.fixColor(LobbyPlugin.getPlugin().getConfiguration().ChatFormatHelper());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("player"))) {
        globalFormat = ChatUtil.fixColor(LobbyPlugin.getPlugin().getConfiguration().ChatFormatPlayer());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("tHELPER"))) {
        globalFormat = ChatUtil.fixColor(LobbyPlugin.getPlugin().getConfiguration().ChatFormatTHelper());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("moderator"))) {
        globalFormat = ChatUtil.fixColor(LobbyPlugin.getPlugin().getConfiguration().ChatFormatModerator());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("admin"))) {
        globalFormat = ChatUtil.fixColor(LobbyPlugin.getPlugin().getConfiguration().ChatFormatAdmin());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("wlasciciel"))) {
        globalFormat = ChatUtil.fixColor(LobbyPlugin.getPlugin().getConfiguration().ChatFormatwlasciciel());
        }
        if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("headadmin"))) {
        globalFormat = ChatUtil.fixColor(LobbyPlugin.getPlugin().getConfiguration().ChatFormatHeadAdmin());

        }
        globalFormat = globalFormat.replace("{PREFIX}", prefix);
        globalFormat = globalFormat.replace("{PLAYER}",  p.getName());
        globalFormat = globalFormat.replace("{MESSAGE}", e.getMessage());
        e.setCancelled(true);
        if (!e.getMessage().startsWith("!"))   {
        ChatPacket chatpacket;
        chatpacket = new ChatPacket(ChatUtil.translateHexColorCodes(globalFormat));
        LobbyPlugin.getPlugin().getRedisService().publishAsync("chatlobby", chatpacket);
        }
    }


}
