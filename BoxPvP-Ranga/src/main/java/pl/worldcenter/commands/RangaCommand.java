package pl.worldcenter.commands;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.data.DataType;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import org.bukkit.entity.Player;

import pl.worldcenter.util.ChatUtil;
import pl.worldcenter.commands.api.CommandInfo;
import pl.worldcenter.commands.api.PlayerCommandExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@CommandInfo(name = "ranga", description = "Pokazuje posiadane rangi gracza", usage = "/ranga", permission = "core.cmd.user", aliases = {"rangi", "group"})
public class RangaCommand extends PlayerCommandExecutor {

    private static final Map<String, String> RANK_MAPPINGS = new HashMap<>();
    private static final Map<String, String> SERVER_NAME = new HashMap<>();


    static {
        RANK_MAPPINGS.put("group.root", ChatUtil.translateHexColorCodes("&#eb1212ʀ&#eb1212ᴏ&#eb1212ᴏ&#eb1212ᴛ&8&l"));
        RANK_MAPPINGS.put("group.developer", ChatUtil.translateHexColorCodes("&#d860ebᴅ&#d860ebᴇ&#d860ebᴠ&#d860ebᴇ&#d860ebʟ&#d860ebᴏ&#d860ebᴘ&#d860ebᴇ&#d860ebʀ&8&l"));
        RANK_MAPPINGS.put("group.headadmin", ChatUtil.translateHexColorCodes("&#eb2417ʜ&#eb2417ᴇ&#eb2417ᴀ&#eb2417ᴅ&#eb2417ᴀ&#eb2417ᴅ&#eb2417ᴍ&#eb2417ɪ&#eb2417ɴ&8&l"));
        RANK_MAPPINGS.put("group.globaladmin", ChatUtil.translateHexColorCodes("&#eb4141ɢ&#eb4141ʟ&#eb4141ᴏ&#eb4141ʙ&#eb4141ᴀ&#eb4141ʟ&#eb4141ᴀ&#eb4141ᴅ&#eb4141ᴍ&#eb4141ɪ&#eb4141ɴ&8&l"));
        RANK_MAPPINGS.put("group.admin", ChatUtil.translateHexColorCodes("&#eb4731ᴀ&#eb4731ᴅ&#eb4731ᴍ&#eb4731ɪ&#eb4731ɴ&8&l"));
        RANK_MAPPINGS.put("group.jradmin", ChatUtil.translateHexColorCodes("&#ebd619ᴊ&#ebd619ʀ&#ebd619ᴀ&#ebd619ᴅ&#ebd619ᴍ&#ebd619ɪ&#ebd619ɴ&8&l"));
        RANK_MAPPINGS.put("group.moderator", ChatUtil.translateHexColorCodes("&#0c9c23ᴍ&#0c9c23ᴏ&#0c9c23ᴅ&#0c9c23ᴇ&#0c9c23ʀ&#0c9c23ᴀ&#0c9c23ᴛ&#0c9c23ᴏ&#0c9c23ʀ&8&l"));
        RANK_MAPPINGS.put("group.jrmod", ChatUtil.translateHexColorCodes("&#41f83dᴊ&#41f83dʀ&#41f83dᴍ&#41f83dᴏ&#41f83dᴅ&8&l"));
        RANK_MAPPINGS.put("group.helper", ChatUtil.translateHexColorCodes("&#3dc0f8ʜ&#3dc0f8ᴇ&#3dc0f8ʟ&#3dc0f8ᴘ&#3dc0f8ᴇ&#3dc0f8ʀ&8&l"));
        RANK_MAPPINGS.put("group.thelper", ChatUtil.translateHexColorCodes("&#456cf8ᴛ&#456cf8ʜ&#456cf8ᴇ&#456cf8ʟ&#456cf8ᴘ&#456cf8ᴇ&#456cf8ʀ&8&l"));
        RANK_MAPPINGS.put("group.tworca", ChatUtil.translateHexColorCodes("&#ba2eebᴛ&#ba2eebᴡ&#ba2eebᴏ&#ba2eebʀ&#ba2eebᴄ&#ba2eebᴀ&8&l"));
        RANK_MAPPINGS.put("group.partner", ChatUtil.translateHexColorCodes("&#9a24a4ᴘ&#9a24a4ᴀ&#9a24a4ʀ&#9a24a4ᴛ&#9a24a4ɴ&#9a24a4ᴇ&#9a24a4ʀ&8&l"));
        RANK_MAPPINGS.put("group.platyna", ChatUtil.translateHexColorCodes("&#d7dadfᴘ&#d7dadfʟ&#d7dadfᴀ&#d7dadfᴛ&#d7dadfʏ&#d7dadfɴ&#d7dadfᴀ&8&l"));
        RANK_MAPPINGS.put("group.svip", ChatUtil.translateHexColorCodes("&es&6ᴠɪᴘ&8&l"));
        RANK_MAPPINGS.put("group.vip", ChatUtil.translateHexColorCodes("&6ᴠɪᴘ&8&l"));
        SERVER_NAME.put("boxpvp", "&8 &#55ff04&#5cfa0d&#63f617&#6af120&l&#71ed2a&#78e833boxpvp &8");
        SERVER_NAME.put("skypvp", "&8 &#6ae4ff&#5ecafa&#52b0f6&l&#4696f1&#3a7ced&#2e62e8skypvp &8");
        SERVER_NAME.put("lifesteal", "&8 &#fbff23&#f7f820&l&#f3f11e&#efea1b&#ece318&l&#e8dc15&#e4d513&#e0ce10&#dcc70dlifesteal &8");
        SERVER_NAME.put("kitpvp", "&8 &#ff44f8&#ef45f9&#df46fb&#cf48fc&#bf49fe&l&#af4affkitpvp &8");
        SERVER_NAME.put("zombiemod", "&8 &#ff0000&#ed0302&#db0704&#c90a06&#b70e08&l&#a41109&#92140b&#80180d&#6e1b0fzombiemod &8");

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
                    return rankName.isEmpty() ? null : rankName;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        String serverPrefix = SERVER_NAME.containsKey(serverName) ?
                ChatUtil.translateHexColorCodes("&e&l➥")
                        + SERVER_NAME.get(serverName) + "&8»&8 " : "";

        if (serverRanks.isEmpty()) {
            return "brak";
        }

        String ranksWithServer = serverPrefix + String.join("&7,", serverRanks);
        return "" + ranksWithServer;
    }







    public static String getRanksOnServerkomenda(User user, String serverName) {
        Set<String> serverRanks = user.getNodes().stream()
                .filter(node -> node.getType() == NodeType.INHERITANCE)
                .filter(node -> node.getContexts().getAnyValue("server").map(serverName::equalsIgnoreCase).orElse(false))
                .map(node -> {
                    String rankName = RANK_MAPPINGS.getOrDefault(node.getKey(), "");
                    String expiryInfo = node.hasExpiry() && node.getExpiry() != null ?
                            ChatUtil.fixColor( " &fwygasa &8(&a " + (node.getExpiry()) + "&8)").replace("T", " ").replace("Z", " "):
                            ChatUtil.fixColor(" &8(&fwygasa &cNIGDY&8)");
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
            String KitPvPRanks = getRanksOnServerkomenda(user, "kitpvp");
            String ZombieModRanks = getRanksOnServerkomenda(user, "zombiemod");

            LuckPerms api = LuckPermsProvider.get();
            String prefix = user.getCachedData().getMetaData().getPrefix();
            player.sendMessage(ChatUtil.translateHexColorCodes("&fRanga globalna&8: " + prefix));
            player.sendMessage(ChatUtil.fixColor("&7Rangi na trybach: "));
            if (boxPvPRanks.isEmpty() && skyPvPRanks.isEmpty() && lifeStealRanks.isEmpty() && KitPvPRanks.isEmpty() && ZombieModRanks.isEmpty()) {
                player.sendMessage(ChatUtil.fixColor("&fBrak"));
            } else {
                if (!boxPvPRanks.isEmpty()) {
                    player.sendMessage(ChatUtil.translateHexColorCodes("&#55ff04&#5cfa0d&#63f617&#6af120&l&#71ed2a&#78e833boxpvp&f Ranga " + boxPvPRanks + "&7"));
                }
                if (!skyPvPRanks.isEmpty()) {
                    player.sendMessage(ChatUtil.translateHexColorCodes("&#6ae4ff&#5ecafa&#52b0f6&l&#4696f1&#3a7ced&#2e62e8skypvp&f Ranga " + skyPvPRanks + "&7"));
                }
                if (!lifeStealRanks.isEmpty()) {
                    player.sendMessage(ChatUtil.translateHexColorCodes("&#fbff23&#f7f820&l&#f3f11e&#efea1b&#ece318&l&#e8dc15&#e4d513&#e0ce10&#dcc70dlifesteal&f Ranga " + lifeStealRanks + "&7"));
                }
                if (!KitPvPRanks.isEmpty()) {
                    player.sendMessage(ChatUtil.translateHexColorCodes("&#ff44f8&#ef45f9&#df46fb&#cf48fc&#bf49fe&l&#af4affkitpvp&f Ranga " + KitPvPRanks + "&7"));
                }
                if (!ZombieModRanks.isEmpty()) {
                    player.sendMessage(ChatUtil.translateHexColorCodes(" &#ff0000&#ed0302&#db0704&#c90a06&#b70e08&l&#a41109&#92140b&#80180d&#6e1b0fzombiemod&f Ranga " + ZombieModRanks + "&7"));
                }
            }
        }
    }
}