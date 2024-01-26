package api.regions;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.textr.boxpvp.Main;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


public class WorldGuardRegionHelper {

    /**
     * Pobiera obszary, w których obecnie znajduje się gracz.
     *
     * @param playerUUID UUID gracza, którego dotyczy zapytanie.
     * @return Zbiór obszarów chronionych przez WorldGuard, w których obecnie znajduje się gracz.
     */


    @Nonnull
    public static Set<ProtectedRegion> getRegions(UUID playerUUID)
    {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player == null || !player.isOnline())
            return Collections.emptySet();

        RegionQuery query = Main.getPlugin().getRegionContainer().createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()));
        return set.getRegions();
    }

    /**
     * Pobiera nazwy obszarów, w których obecnie znajduje się gracz.
     *
     * @param playerUUID UUID gracza, którego dotyczy zapytanie.
     * @return Zbiór ciągów znaków zawierających nazwy obszarów, w których obecnie znajduje się gracz.
     */

    @Nonnull
    public static Set<String> getRegionsNames(UUID playerUUID)
    {
        return getRegions(playerUUID).stream().map(ProtectedRegion::getId).collect(Collectors.toSet());
    }

    /**
     * Sprawdza, czy gracz znajduje się w jednym lub kilku obszarach.
     *
     * @param playerUUID UUID gracza, którego dotyczy zapytanie.
     * @param regionNames Zbiór obszarów do sprawdzenia.
     * @return Prawda, jeśli gracz jest w (wszystkich) obszarze/obszarach o podanych nazwach.
     */

    public static boolean isPlayerInAllRegions(UUID playerUUID, Set<String> regionNames)
    {
        Set<String> regions = getRegionsNames(playerUUID);
        if(regionNames.isEmpty()) throw new IllegalArgumentException("You need to check for at least one region !");

        return regions.containsAll(regionNames.stream().map(String::toLowerCase).collect(Collectors.toSet()));
    }

    /**
     * Sprawdza, czy gracz znajduje się w jednym lub kilku obszarach.
     *
     * @param playerUUID UUID gracza, którego dotyczy zapytanie.
     * @param regionNames Zbiór obszarów do sprawdzenia.
     * @return Prawda, jeśli gracz jest w (którymkolwiek) z obszarów o podanych nazwach.
     */

    public static boolean isPlayerInAnyRegion(UUID playerUUID, Set<String> regionNames)
    {
        Set<String> regions = getRegionsNames(playerUUID);
        if(regionNames.isEmpty()) throw new IllegalArgumentException("You need to check for at least one region !");
        for(String region : regionNames)
        {
            if(regions.contains(region.toLowerCase()))
                return true;
        }
        return false;
    }

    /**
     * Sprawdza, czy wszyscy gracze online znajdują się w jednym lub kilku obszarach.
     *
     * @param regionNames Zbiór obszarów do sprawdzenia.
     * @return Prawda, jeśli wszyscy gracze online są w (wszystkich) obszarze/obszarach o podanych nazwach.
     */
    public static boolean areAllPlayersInRegions(Set<String> regionNames) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!isPlayerInAnyRegion(player.getUniqueId(), regionNames)) {
                return false;
            }
        }
        return true;
    }




    /**
     * Sprawdza, czy wszyscy gracze online znajdują się w jednym lub kilku obszarach.
     *
     * @param regionNames Lista obszarów do sprawdzenia.
     * @return Prawda, jeśli wszyscy gracze online są w (wszystkich) obszarze/obszarach o podanych nazwach.
     */
    public static boolean areAllPlayersInRegions(String... regionNames) {
        return areAllPlayersInRegions(new HashSet<>(Arrays.asList(regionNames)));
    }

    /**
     * Sprawdza, czy gracz znajduje się w jednym lub kilku obszarach.
     *
     * @param playerUUID UUID gracza, którego dotyczy zapytanie.
     * @param regionName Lista obszarów do sprawdzenia.
     * @return Prawda, jeśli gracz jest w (którymkolwiek) z obszarów o podanych nazwach.
     */

    public static boolean isPlayerInAnyRegion(UUID playerUUID, String... regionName)
    {
        return isPlayerInAnyRegion(playerUUID, new HashSet<>(Arrays.asList(regionName)));
    }

    /**
     * Sprawdza, czy gracz znajduje się w jednym lub kilku obszarach.
     *
     * @param playerUUID UUID gracza, którego dotyczy zapytanie.
     * @param regionName Lista obszarów do sprawdzenia.
     * @return Prawda, jeśli gracz jest w (którymkolwiek) z obszarów o podanych nazwach.
     */

    public static boolean isPlayerInAllRegions(UUID playerUUID, String... regionName)
    {
        return isPlayerInAllRegions(playerUUID, new HashSet<>(Arrays.asList(regionName)));
    }

}
