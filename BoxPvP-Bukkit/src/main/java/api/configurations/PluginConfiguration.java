package api.configurations;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import pl.textr.boxpvp.utils.ChatUtil;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Header("# BoxPvP")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfiguration extends OkaeriConfig {

    @Comment("")
    // PostgreSQL settings
    @Comment("USTAWIENIA postgresql")
    @Comment("#host")
    public String hostpostgresql = "127.0.0.1";
    public int portpostgresql = 5432;
    public String userpostgresql = "root";
    public String passwordpostgresql = "mtk11";
    public String databasepostgresql = "core";
    @Comment("")
    // Redis settings
    @Comment("USTAWIENIA REDIS")
    public String redisurl = "redis://localhost:6379/";
    public String redispassword = "mtk11";
    @Comment("")
    // Warp settings
    @Comment("USTAWIENIA WARP")
    @Comment("USTAWIENIA kopalniapremiumlocation")
    public String KOPALNIAPREMIUM_LOCATION_WORLD = "kopalniapremium";
    public double KOPALNIAPREMIUM_LOCATION_X = -8.535;
    public double KOPALNIAPREMIUM_LOCATION_Y = 120;
    public double KOPALNIAPREMIUM_LOCATION_Z = -3.496;
    public float KOPALNIAPREMIUM_LOCATION_YAW = 1.0f;
    public float KOPALNIAPREMIUM_LOCATION_PITCH = -2.4f;
    @Comment("")
    // Kopalnia location settings
    @Comment("USTAWIENIA kopalnialocation")
    public String KOPALNIA_LOCATION_WORLD = "kopalnia";
    public double KOPALNIA_LOCATION_X = -8.535;
    public double KOPALNIA_LOCATION_Y = 120;
    public double KOPALNIA_LOCATION_Z = -3.496;
    public float KOPALNIA_LOCATION_YAW = 1.0f;
    public float KOPALNIA_LOCATION_PITCH = -2.4f;
    @Comment("")
    // AFK location settings
    @Comment("USTAWIENIA AFK")
    public String AFK_WORLD = "world";
    public double AFK_X = 6.375;
    public double AFK_Y = 65.00;
    public double AFK_Z = 20.359;
    public float AFK_YAW = 145.5f;
    public float AFK_PITCH = -3.9f;
    @Comment("")
    // PVP_LOCATION settings
    @Comment("USTAWIENIA PVP_LOCATION")
    public String PVP_LOCATION_WORLD = "pvp";
    public double PVP_LOCATION_X = 0.5;
    public double PVP_LOCATION_Y = 2;
    public double PVP_LOCATION_Z = 208;
    public float PVP_LOCATION_YAW = 176.8f;
    public float PVP_LOCATION_PITCH = -10.1f;
    @Comment("")
    // SPAWN_LOCATION settings
    @Comment("USTAWIENIA SPAWN_LOCATION")
    public String SPAWN_LOCATION_WORLD = "world";
    public double SPAWN_LOCATION_X = -24;
    public double SPAWN_LOCATION_Y = 37;
    public double SPAWN_LOCATION_Z = 31;
    public float SPAWN_LOCATION_YAW = 0.3f;
    public float SPAWN_LOCATION_PITCH = 1.2f;
    public String boxpvpName = "boxpvp_1";
    public String lobbyname = "lobby";
    public String weebhook = "link";
    public int slot = 500;
    public int ranking = 500;
    public long vouchery = 0L;
    public long turbodrop = 0L;
    public int chatslowmode = 10;
    public boolean debug = true;
    @Comment("")
    // Enable settings
    @Comment("ENABLE")
    public boolean enablecreate = true;
    public boolean enablewhitelist = true;
    public boolean teleportsrodek = true;
    public boolean teleportlobby = true;
    public boolean budowanie = true;
    public boolean ChatEnable = true;
    public boolean VipChatEnable = true;
    public boolean fortuna = true;
    public boolean afk = true;
    public boolean RewardsDiscord = true;
    @Comment("")
    // Perk settings
    @Comment("USTAWIENIA PERKS")
    @Comment("PERK ZDROWIA")
    public double perkZycia_1 = 22.0D;
    public double perkZycia_2 = 24.0D;
    public double perkZycia_3 = 26.0D;
    public double perkZycia_4 = 28.0D;
    @Comment("")
    @Comment("PERK SZYBKOSCI")
    public double perkSzybkosci_1 = 0.10D;
    @Comment("")
    @Comment("PERK WAMPIRYZMU")
    public double perkWampiryzmu_1 = 1.0D;
    public double perkWampiryzmu_2 = 2.0D;
    public double perkWampiryzmu_3 = 3.0D;
    public double perkWampiryzmu_4 = 4.0D;
    @Comment("")
    @Comment("PERK SILY")
    public double perkSily_1 = 4.0D;
    public double perkSily_2 = 4.5D;
    public double perkSily_3 = 5.0D;
    public double perkSily_4 = 6.0D;
    @Comment("")
    @Comment("PERK SZYBKOSCI ATAKU")
    public double perkSzybkosciAtaku_1 = 4.2D;
    public double perkSzybkosciAtaku_2 = 4.25D;
    public double perkSzybkosciAtaku_3 = 4.3D;
    public double perkSzybkosciAtaku_4 = 4.35D;
    public String usage = "&8[&C&l!&8] &7Poprawne uzycie: &r{USAGE}";
    public String fullserver = "&7Serwer jest pelen &rgraczy&7!\n&7Odczekaj chwile i dolacz do nas ponownie!";
    public String whitelistreason = "&cNie jestes na whitelist!";
    // CHAT SETTINGS
    @Comment("")
    @Comment("USTAWIENIA CHATU")
    @Comment("")
    @Comment("# Chatformat - dostepne zmienne   #{PLAYER} - gracz {MESSAGE} - wiadomosc {TAG} - nazwa klanu {POINTS} - punkty ")
    public String ChatFormatwlasciciel = "&8&l[&4&lW&8&l] &4{PLAYER} &8» &4{MESSAGE}";
    public String ChatFormatHeadAdmin = "&8&l[&C&LHA&8&l] &c{PLAYER} &8» &c{MESSAGE}";
    public String ChatFormatAdmin = "&8&l[&c&lA&8&l] &c{PLAYER} &8» &c{MESSAGE}";
    public String ChatFormatModerator = "&8&l[&2&lM&8&l] &2{PLAYER} &8» &a{MESSAGE}";
    public String ChatFormatTHelper = "&8&l[&9&lH&8&l] &9{PLAYER} &8» &b{MESSAGE}";
    public String ChatFormatHelper = "&8&l[&9&lTH&8&l] &9{PLAYER} &8» &b{MESSAGE}";
    public String ChatFormatGlobal = "{TAG} {PREFIX} &7{PLAYER} &8» &7{MESSAGE}";
    public String ChatFormatPlayer = "{TAG} &7{PLAYER} &8» &7{MESSAGE}";
    @Comment("")
    // Other settings
    @Comment("OTHER")
    private  String spawnregion = "srodek";
    private  String elytraregion = "srodek";
    private  Integer turbodropmnoznik = 2;
    private  boolean wyrzucanie = true;
    @Comment("")
    @Comment("USTAWIENIA GENERATORÓW")
    public List<Map<String, Object>> generators = new ArrayList<>();
    // MESSAGE SETTINGS
    @Comment("")
    @Comment("USTAWIENIA WIADOMOSCI")
    private  String errordonthavepermission = "&7Ta komenda nie jest przeznaczona dla ciebie! &8(&f{PERM}&8)";
    @Comment("")
    @Comment("whitelist")
    private  List<String> whitelistnicks = Collections.singletonList("nick");

    
    
 
    public List<Map<String, Object>> getGenerators() {
        return generators;
    }
    

    public void setGenerators(List<Map<String, Object>> generators) {
        this.generators = generators;
    }




    
    public Location getAfkLocation() {
        return new Location(Bukkit.getWorld(AFK_WORLD), AFK_X, AFK_Y, AFK_Z, AFK_YAW, AFK_PITCH);
    }

    public Location getPvpLocation() {
        return new Location(Bukkit.getWorld(PVP_LOCATION_WORLD), PVP_LOCATION_X, PVP_LOCATION_Y, PVP_LOCATION_Z, PVP_LOCATION_YAW, PVP_LOCATION_PITCH);
    }

    public Location getSpawnLocation() {
        return new Location(Bukkit.getWorld(SPAWN_LOCATION_WORLD), SPAWN_LOCATION_X, SPAWN_LOCATION_Y, SPAWN_LOCATION_Z, SPAWN_LOCATION_YAW, SPAWN_LOCATION_PITCH);
    }

    // Setter for setting the spawn location
    public void setSpawnLocation(Location spawnLocation) {
        SPAWN_LOCATION_WORLD = spawnLocation.getWorld().getName();
        SPAWN_LOCATION_X = spawnLocation.getX();
        SPAWN_LOCATION_Y = spawnLocation.getY();
        SPAWN_LOCATION_Z = spawnLocation.getZ();
        SPAWN_LOCATION_YAW = spawnLocation.getYaw();
        SPAWN_LOCATION_PITCH = spawnLocation.getPitch();
    }

    public Location getKopalniapremiumLocation() {
        return new Location(Bukkit.getWorld(KOPALNIAPREMIUM_LOCATION_WORLD), KOPALNIAPREMIUM_LOCATION_X, KOPALNIAPREMIUM_LOCATION_Y, KOPALNIAPREMIUM_LOCATION_Z, KOPALNIAPREMIUM_LOCATION_YAW, KOPALNIAPREMIUM_LOCATION_PITCH);
    }

    public Location getKopalniaLocation() {
        return new Location(Bukkit.getWorld(KOPALNIA_LOCATION_WORLD), KOPALNIA_LOCATION_X, KOPALNIA_LOCATION_Y, KOPALNIA_LOCATION_Z, KOPALNIA_LOCATION_YAW, KOPALNIA_LOCATION_PITCH);
    }


    public double getPerkZycia1() {
        return perkZycia_1;
    }

    public double getPerkZycia2() {
        return perkZycia_2;
    }

    public double getPerkZycia3() {
        return perkZycia_3;
    }

    public double getPerkZycia4() {
        return perkZycia_4;
    }

    public double getPerkSzybkosci1() {
        return perkSzybkosci_1;
    }

    public double getPerkSily1() {
        return perkSily_1;
    }

    public double getPerkSily2() {
        return perkSily_2;
    }

    public double getPerkSily3() {
        return perkSily_3;
    }

    public double getPerkSily4() {
        return perkSily_4;
    }

    public double getPerkWampiryzmu1() {
        return perkWampiryzmu_1;
    }

    public double getPerkWampiryzmu2() {
        return perkWampiryzmu_2;
    }

    public double getPerkWampiryzmu3() {
        return perkWampiryzmu_3;
    }

    public double getPerkWampiryzmu4() {
        return perkWampiryzmu_4;
    }

    public double getPerkSzybkosciAtaku1() {
        return perkSzybkosciAtaku_1;
    }

    public double getPerkSzybkosciAtaku2() {
        return perkSzybkosciAtaku_2;
    }

    public double getPerkSzybkosciAtaku3() {
        return perkSzybkosciAtaku_4;
    }

    public double getPerkSzybkosciAtaku4() {
        return perkSzybkosciAtaku_4;
    }


    public String weebhook() {
        return weebhook;
    }


    public int portpostgresql() {
        return portpostgresql;
    }

    public String userpostgresql() {
        return userpostgresql;
    }

    public String passwordpostgresql() {
        return passwordpostgresql;
    }

    public String namepostgresql() {
        return databasepostgresql;
    }

    public String redisurl() {
        return redisurl;
    }

    public String redispassword() {
        return redispassword;
    }

    public String spawnregion() {
        return spawnregion;
    }

    public String elytraregion() {
        return elytraregion;
    }

    public String boxpvpName() {
        return boxpvpName;
    }

    public String lobbyname() {
        return lobbyname;
    }

    public int slot() {
        return slot;
    }

    public boolean afk() {
        return afk;
    }
    
    public boolean RewardsDiscord() {
        return RewardsDiscord;
    }


    public int ranking() {
        return ranking;
    }

    public long vouchery() {
        return vouchery;
    }

    public long turbodrop() {
        return turbodrop;
    }

    public Integer turbodropmnoznik() {
        return turbodropmnoznik;
    }

    public int chatslowmode() {
        return chatslowmode;
    }

    public boolean debug() {
        return debug;
    }

    public boolean enablecreate() {
        return enablecreate;
    }

    public boolean enablewhitelist() {
        return enablewhitelist;
    }

    public boolean teleportsrodek() {
        return teleportsrodek;
    }

    public boolean teleportlobby() {
        return teleportlobby;
    }

    public boolean wyrzucanie() {
        return wyrzucanie;
    }

    public boolean budowanie() {
        return budowanie;
    }

    public boolean ChatEnable() {
        return ChatEnable;
    }

    public boolean VipChatEnable() {
        return VipChatEnable;
    }

    public String errordonthavepermission() {
        return errordonthavepermission;
    }

    public String fullserver() {
        return fullserver;
    }

    public String usage( String use) {
        return usage.replace("{USAGE}", use);
    }

    public List<String> whitelistnicks() {
        return whitelistnicks;
    }

    public String whitelistreason() {
        return whitelistreason;
    }

    public String ChatFormatGlobal() {
        return ChatFormatGlobal;
    }

    public String ChatFormatHelper() {
        return ChatFormatHelper;
    }

    public String ChatFormatPlayer() {
        return ChatFormatPlayer;
    }

    public String ChatFormatTHelper() {
        return ChatFormatTHelper;
    }

    public String ChatFormatModerator() {
        return ChatFormatModerator;
    }

    public String ChatFormatAdmin() {
        return ChatFormatAdmin;
    }

    public String ChatFormatwlasciciel() {
        return ChatFormatwlasciciel;
    }

    public String ChatFormatHeadAdmin() {
        return ChatFormatHeadAdmin;
    }

    public boolean fortuna() {
        return fortuna;
    }


	


}
