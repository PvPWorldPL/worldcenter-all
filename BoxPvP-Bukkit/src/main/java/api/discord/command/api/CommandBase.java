package api.discord.command.api;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class CommandBase {
    private final String name;
    private final List<String> aliases;

    public CommandBase(String name) {
        this.name = name;
        this.aliases = new ArrayList<>();
    }

    public CommandBase(String name, String... aliases) {
        this.name = name;
        this.aliases = Arrays.asList(aliases);
    }

    public String getName() {
        return this.name;
    }

    public List<String> getAliases() {
        return this.aliases;
    }
}
