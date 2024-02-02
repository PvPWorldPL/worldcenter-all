package pl.worldcenter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;


import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import pl.worldcenter.commands.RangaCommand;

public class PlaceHolderRegisterAPI extends PlaceholderExpansion {




    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        String boxPvpRanks = RangaCommand.getRanksOnServer(user, "boxpvp");
        String skyPvpRanks = RangaCommand.getRanksOnServer(user, "skypvp");
        String lifeStealRanks = RangaCommand.getRanksOnServer(user, "lifesteal");
        String KitPvPRanks = RangaCommand.getRanksOnServer(user, "kitpvp");
        String ZombieModRanks = RangaCommand.getRanksOnServer(user, "zombiemod");

        if (identifier.equalsIgnoreCase("boxpvp")) {
            return boxPvpRanks;
        }

        if (identifier.equalsIgnoreCase("skypvp")) {
            return skyPvpRanks;
        }

        if (identifier.equalsIgnoreCase("lifesteal")) {
            return lifeStealRanks;
        }

        if (identifier.equalsIgnoreCase("kitpvp")) {
            return KitPvPRanks;
        }

        if (identifier.equalsIgnoreCase("ZombieMod")) {
            return ZombieModRanks;
        }

        return "";
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