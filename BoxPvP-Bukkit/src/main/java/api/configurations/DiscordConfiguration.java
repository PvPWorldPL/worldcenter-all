package api.configurations;

import java.util.HashMap;
import java.util.Map;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.annotation.NameModifier;
import eu.okaeri.configs.annotation.NameStrategy;
import eu.okaeri.configs.annotation.Names;

@Header("# BoxPvP")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class DiscordConfiguration extends OkaeriConfig {

    @Comment("Dane Discord ID i nick użytkowników")
    private Map<String, Map<String, String>> users = new HashMap<>();

    public Map<String, Map<String, String>> getUsers() {
        return this.users;
    }
}
