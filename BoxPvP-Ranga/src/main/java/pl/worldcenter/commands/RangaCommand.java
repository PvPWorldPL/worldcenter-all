package pl.worldcenter.commands;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import org.bukkit.entity.Player;

import pl.worldcenter.util.ChatUtil;
import pl.worldcenter.commands.api.CommandInfo;
import pl.worldcenter.commands.api.PlayerCommandExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CommandInfo(name = "ranga", description = "Pokazuje posiadane rangi gracza", usage = "/ranga", permission = "core.cmd.user", aliases = {"rangi", "group"})
public class RangaCommand extends PlayerCommandExecutor {

    private static final Map<String, String> RANK_MAPPINGS = new HashMap<>();
    private static final Map<String, String> RANK_GLOBAL_MAPPINGS = new HashMap<>();

    private static final Map<String, String> SERVER_NAME = new HashMap<>();


    static {
        RANK_MAPPINGS.put("group.wlasciciel", "&4&lCEO ");
        RANK_MAPPINGS.put("group.vip", "&aVIP ");
        RANK_MAPPINGS.put("group.svip", "&6SVIP ");
        RANK_MAPPINGS.put("group.sponsor", "&9 SPONSOR ");
        RANK_MAPPINGS.put("group.yt", " ");
        RANK_MAPPINGS.put("group.player", " ");

        RANK_GLOBAL_MAPPINGS.put("wlasciciel", "&4&lCEO");
        RANK_GLOBAL_MAPPINGS.put("vip", "&aVIP");
        RANK_GLOBAL_MAPPINGS.put("svip", "&6SVIP");
        RANK_GLOBAL_MAPPINGS.put("sponsor", "&9SPONSOR");
        RANK_GLOBAL_MAPPINGS.put("yt", " ");
        RANK_GLOBAL_MAPPINGS.put("Player", "Brak");
        RANK_GLOBAL_MAPPINGS.put("null", "Brak");

        SERVER_NAME.put("boxpvp", "&8 &cboxpvp &8");
        SERVER_NAME.put("skypvp", "&8 &askypvp &8");
        SERVER_NAME.put("lifesteal", "&8 &6lifesteal &8");
    }

    @Override
    public boolean onCommand(final Player player, final String[] args) {
        sendRanksMessage(player);
        return true;
    }

    public static String getRanksOnServer(User user, String serverName) {
        Set<String> serverRanks = user.getNodes().stream()
                .filter(node -> node.getType() == NodeType.INHERITANCE)
                .filter(node -> node.getContexts().getAnyValue("server").map(serverName::equalsIgnoreCase).orElse(false))
                .map(node -> {
                    String rankName = RANK_MAPPINGS.getOrDefault(node.getKey(), "");
                    return SERVER_NAME.getOrDefault(serverName, "") + "&8»&8 " + rankName;
                })
                .collect(Collectors.toSet());
        return String.join(", ", serverRanks);
    }






    public static String getRanksOnServerkomenda(User user, String serverName) {
        Set<String> serverRanks = user.getNodes().stream()
                .filter(node -> node.getType() == NodeType.INHERITANCE)
                .filter(node -> node.getContexts().getAnyValue("server").map(serverName::equalsIgnoreCase).orElse(false))
                .map(node -> {
                    String rankName = RANK_MAPPINGS.getOrDefault(node.getKey(), "");
                    String expiryInfo = node.hasExpiry() && node.getExpiry() != null ?
                            ChatUtil.fixColor( "&fwygasa &8(&a " + (node.getExpiry()) + "&8)").replace("T", " ").replace("Z", " "):
                            ChatUtil.fixColor("&8(&fwygasa &cNIGDY&8)");
                    return rankName + expiryInfo;
                })
                .collect(Collectors.toSet());
        return String.join(", ", serverRanks);
    }

    private void sendRanksMessage(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        if (user != null) {
            String boxPvPRanks = getRanksOnServerkomenda(user, "boxpvp");
            String skyPvPRanks = getRanksOnServerkomenda(user, "skypvp");
            String lifeStealRanks = getRanksOnServerkomenda(user, "lifesteal");
            String globalRanks = luckPerms.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup();

            player.sendMessage(ChatUtil.fixColor("&7Rangi na trybach: "));
            if (boxPvPRanks.isEmpty() && skyPvPRanks.isEmpty() && lifeStealRanks.isEmpty() && globalRanks.isEmpty()) {
                player.sendMessage(ChatUtil.fixColor("&fBrak"));
            } else {
                if (!globalRanks.isEmpty()) {
                    player.sendMessage(ChatUtil.fixColor("&fRanga globalna&8:&c " + RANK_GLOBAL_MAPPINGS.getOrDefault(globalRanks, "Brak") + "&7"));
                }
                if (!boxPvPRanks.isEmpty()) {
                    player.sendMessage(ChatUtil.fixColor("&c&LBOXPVP&f Ranga" + boxPvPRanks + "&7"));
                }
                if (!skyPvPRanks.isEmpty()) {
                    player.sendMessage(ChatUtil.fixColor("&a&LSKYPVP&f Ranga" + skyPvPRanks + "&7"));
                }
                if (!lifeStealRanks.isEmpty()) {
                    player.sendMessage(ChatUtil.fixColor("&6&lLIFESTEAL&f Ranga" + lifeStealRanks + "&7"));
                }
            }
        }
    }
}
