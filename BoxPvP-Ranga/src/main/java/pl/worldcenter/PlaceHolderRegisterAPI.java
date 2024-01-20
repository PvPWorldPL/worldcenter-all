package pl.worldcenter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;


import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import pl.worldcenter.commands.RangaCommand;
import pl.worldcenter.util.ChatUtil;

public class PlaceHolderRegisterAPI extends PlaceholderExpansion {


    private RangaCommand rangaCommand;


    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        String boxPvpRanks = rangaCommand.getRanksOnServer(user, "boxpvp");
        String skyPvpRanks = rangaCommand.getRanksOnServer(user, "skypvp");
        String lifeStealRanks = rangaCommand.getRanksOnServer(user, "lifesteal");

            if (identifier.equalsIgnoreCase("boxpvp")) {
                return boxPvpRanks;
            }

            if (identifier.equalsIgnoreCase("skypvp")) {
                return skyPvpRanks;
            }

        if (identifier.equalsIgnoreCase("lifesteal")) {
            return lifeStealRanks;
        }
        return null;
    }






    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }


    @Override
    public String getIdentifier() {
        return "Lobby";
    }


    @Override
    public String getAuthor() {
        return "w97";
    }


    @Override
    public String getVersion() {
        return "1.0";
    }
}
