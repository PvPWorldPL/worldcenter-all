package pl.textr.connect.lang;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;


@Header("# Connector Proxy")

@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfiguration extends OkaeriConfig {

    @Comment("Whitelist / Admins")
    private List<String> whitelistnicks = Collections.singletonList("nick");
    private List<String> adminsnicks = Collections.singletonList("nick");
    private List<String> slotsnicks = Collections.singletonList("nick");

    @Comment("Other")
    public String whitelistreason = "&cNie jestes na whitelist!";
    public boolean enablewhitelist = true;
    public int slot = 500;
    public String proxyname = "&rproxy01";
    public String message_cantlogin = "&7Nastepne polaczenie mozesz wykonac za &c{TIME}";
    public String message_usage = "&8[&C&l!&8] &7Poprawne uzycie: &r{USAGE}";
    public String message_fullserver = "&7Serwer jest pelen &rgraczy&7!\n&7Odczekaj chwile i dolacz do nas ponownie!";

 
    @Comment("Lista linii wiadomości do wyświetlenia na liście serwerów w ping'u proxy")
    public List<String> proxyPingLines = Arrays.asList(
        " ",
        "§6☆ §7Proxy §r" + proxyname,
        " ",
        "§6☆ §7Strona §rMCYOU.PL",
        "§6☆ §7Fb §rFB.MCYOU.PL",
        "§6☆ §7Discord §rDC.MCYOU.PL",
        "§6☆ §7Obciążenie §r" + "{LOAD}",
        " "
    );

    public int slot() {
        return slot;
    }

    public boolean enablewhitelist() {
        return enablewhitelist;
    }

    public List<String> slotsnicks() {
        return slotsnicks;
    }

    public List<String> adminsnicks() {
        return adminsnicks;
    }

    public String fullserver() {
        return message_fullserver;
    }

    public String usage(final String use) {
        return message_usage.replace("{USAGE}", use);
    }

    public List<String> whitelistnicks() {
        return whitelistnicks;
    }

    public String whitelistreason() {
        return whitelistreason;
    }

    public List<String> getProxyPingLines() {
        return proxyPingLines;
    }


}
