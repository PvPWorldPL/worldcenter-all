package api.configurations;

import java.util.Collections;
import java.util.List;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;


@Header("#Hub")

@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfiguration extends OkaeriConfig {
 
    @Comment("")
    @Comment("")
    @Comment("")
    @Comment("USTAWIENIA REDIS")
    public String redisurl  = "redis://127.0.0.1:6379";  
    public String redispassword  = "mtk11";    
    @Comment("")
    @Comment("OTHER")
    private String spawnregion  = "srodek";
    public int slot = 500;
    public int chatslowmode = 10;
    public boolean debug = true;
    private String ipSocket  = "127.0.0.1";
    private String ServerBoxPvP  = "boxpvp";  
    private int portBoxPvP  = 25583;
    private String ServerSkyGen  = "skygen";  
    private int portSkyGen  = 25582;   
    private String ServerSkyBlock  = "skyblock";  
    private int portSkyBlock  = 25584;
    private String serverAnarchiaSmp = "anarichasmp";  
    private int portAnarchiaSmp  = 25584;
    
    @Comment("")
    @Comment("ENABLE")
    public boolean enablewhitelist = true;
    public boolean ChatEnable = true;
    public boolean VipChatEnable = true;
    @Comment("")
    @Comment("messages permission")
    private String errordonthavepermission  = "&7Ta komenda nie jest przeznaczona dla ciebie! &8(&f{PERM}&8)";   
    public String usage = "&8[&C&l!&8] &7Poprawne uzycie: &r{USAGE}";
    public String fullserver = "&7Serwer jest pelen &rgraczy&7!\n&7Odczekaj chwile i dolacz do nas ponownie!";
    @Comment("")
    @Comment("whitelist")
    private List<String> whitelistnicks = Collections.singletonList("nick");
    public String whitelistreason = "&cNie jestes na whitelist!";
    @Comment("wiadomosc przy kick permisja core.cmd.helper")
    
    @Comment("USTAWIENIA CHATU")
    @Comment("")
    @Comment("# Chatformat - dostepne zmienne   #{PLAYER} #{MESSAGE} ")
    public String ChatFormatwlasciciel = "&8&l[&4&lW&8&l] &4{PLAYER} &8» &4{MESSAGE}";     
    public String ChatFormatHeadAdmin = "&8&l[&C&LHA&8&l] &c{PLAYER} &8» &c{MESSAGE}";  
    public String ChatFormatAdmin = "&8&l[&c&lA&8&l] &c{PLAYER} &8» &c{MESSAGE}";
    public String ChatFormatModerator = "&8&l[&2&lM&8&l] &2{PLAYER} &8» &a{MESSAGE}"; 
    public String ChatFormatTHelper = "&8&l[&9&lH&8&l] &9{PLAYER} &8» &b{MESSAGE}";  
    public String ChatFormatHelper = "&8&l[&9&lTH&8&l] &9{PLAYER} &8» &b{MESSAGE}";    
    public String ChatFormatGlobal = "{GUILD} &7{PLAYER} &8» &7{MESSAGE}";
    public String ChatFormatPlayer = "{GUILD} &7{PLAYER} &8» &7{MESSAGE}";

    public String ActionBarMessage = "start 123456";

    public String odliczanieactionbar = "03/02/2024 20:00:00";

    public String ActionBarMessage() {
        return ActionBarMessage;
    }

    public String odliczanieactionbar() {
        return odliczanieactionbar;
    }


    public String redisurl() {
        return redisurl;
    }  
    
    public String ipsocket() {
        return ipSocket;
    } 
    
    public String serverBoxPvP() {
        return ServerBoxPvP;
    } 
     
    public int portboxpvp() {
        return portBoxPvP;
    } 
    
    
    public String serverSkyGen() {
        return ServerSkyGen;
    } 
    
    public int portSkyGen() {
        return portSkyGen;
    }
    
    public String serverSkyBlock() {
        return ServerSkyBlock;
    } 
    
    public int portSkyBlock() {
        return portSkyBlock;
    }
    
    public String serverserverAnarchiaSmp() {
        return serverAnarchiaSmp;
    } 
    
    public int portAnarchiaSmp() {
        return portAnarchiaSmp;
    }
    
    public String redispassword() {
        return redispassword;
    }
    
    public String spawnregion() {
        return spawnregion;
    }
    
    public int slot() {
        return slot;
    }
    
    public int chatslowmode() {
        return chatslowmode;
    }
    
    public boolean debug() {
        return debug;
    }

    public boolean enablewhitelist() {
        return enablewhitelist;
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
    
    public String usage(final String use) {
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

}
