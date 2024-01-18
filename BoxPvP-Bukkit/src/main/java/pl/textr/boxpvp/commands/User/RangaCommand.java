package pl.textr.boxpvp.commands.User;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.ChatUtil;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CommandInfo(name = "ranga", description = "Komenda do nadawania efektu glow", usage = "/glow", permission = "core.cmd.user", aliases = {"rangi", "group"})
public class RangaCommand extends PlayerCommandExecutor {

    private static final Map<String, String> RANK_MAPPINGS = new HashMap<>();

    static {
        // Dodaj mapowania rang: pełny identyfikator -> przyjazna nazwa
        RANK_MAPPINGS.put("group.wlasciciel", "&4&l CEO ");
        RANK_MAPPINGS.put("group.vip", "&a VIP ");
        RANK_MAPPINGS.put("group.svip", "&6 SVIP ");
        RANK_MAPPINGS.put("group.sponsor", "&9 SPONSOR ");
        RANK_MAPPINGS.put("group.yt", " ");
        RANK_MAPPINGS.put("group.player", " ");
        // Dodaj więcej mapowań według potrzeb
    }

    @Override
    public boolean onCommand(final Player player, final String[] args) {
        sendRanksMessage(player);
        return true;
    }

    private String getRanksOnServer(User user, String serverName) {
        Set<String> serverRanks = user.getNodes().stream()
                .filter(node -> node.getType() == NodeType.INHERITANCE)
                .filter(node -> node.getContexts().getAnyValue("server").map(serverName::equalsIgnoreCase).orElse(false))
                .map(node -> {
                    String rankName = RANK_MAPPINGS.get(node.getKey());
                    String expiryInfo = node.hasExpiry() && node.getExpiry() != null ? ChatUtil.fixColor("&fwygasa &8(&a " + (node.getExpiry()) + "&8)").replace("T", " ").replace("Z", " ") :
                            ChatUtil.fixColor("&8(&fwygasa &cNIGDY&8)");
                    return rankName + expiryInfo;
                })
                .collect(Collectors.toSet());

        if (!serverRanks.isEmpty()) {
            return String.join(", ", serverRanks);
        } else {
            return "";
        }
    }

    private void sendRanksMessage(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        if (user != null) {
            String boxPvPRanks = getRanksOnServer(user, "boxpvp");
            String SkyPvPRanks = getRanksOnServer(user, "skypvp");
            String LifeStealRanks = getRanksOnServer(user, "lifesteal");
            player.sendMessage(ChatUtil.fixColor("&7Rangi na trybach: "));
            if (boxPvPRanks.length() < 1 && SkyPvPRanks.length() < 1 && LifeStealRanks.length() < 1) {
                player.sendMessage(ChatUtil.fixColor("&fBrak"));
            } else {
                if (boxPvPRanks.length() >= 1) {
                    player.sendMessage(ChatUtil.fixColor("&c&LBOXPVP&f Ranga" + boxPvPRanks + "&7"));
                }
                if (SkyPvPRanks.length() >= 1) {
                    player.sendMessage(ChatUtil.fixColor("&a&LSKYPVP&f Ranga" + SkyPvPRanks + "&7"));
                }
                if (LifeStealRanks.length() >= 1) {
                    player.sendMessage(ChatUtil.fixColor("&6&lLIFESTEAL&f Ranga" + LifeStealRanks + "&7"));
                }
            }
        }
    }
}
