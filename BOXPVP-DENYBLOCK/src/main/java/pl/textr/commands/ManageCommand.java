package pl.textr.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import pl.textr.Main;
import pl.textr.utils.MessageFormatter;

import java.util.List;

public class ManageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("To polecenie może być używane tylko przez graczy.");
            return true;
        }

        String type = args[0].toLowerCase();
        String name = args[1];

        switch (type) {
            case "add":
                if (args.length < 3) {
                    MessageFormatter.sendFormattedMessage(player, "&cPoprawne użycie: /manage add world/block <nazwa>");
                    return true;
                }

                String addType = args[2].toLowerCase();
                switch (addType) {
                    case "world":
                        Main.getPlugin().getConfiguration().Worlds.add(name);
                        MessageFormatter.sendFormattedMessage(player, "&aDodano świat: " + name);
                        break;
                    case "block":
                        try {
                            Main.getPlugin().getConfiguration().allowedBreakBlocks.add(Material.valueOf(name.toUpperCase()));
                            MessageFormatter.sendFormattedMessage(player, "&aDodano blok: " + name);
                        } catch (IllegalArgumentException e) {
                            MessageFormatter.sendFormattedMessage(player, "&cNieprawidłowy materiał: " + name);
                        }
                        break;
                    default:
                        MessageFormatter.sendFormattedMessage(player, "&cNieznany typ: " + addType);
                        break;
                }
                break;

            case "remove":
                if (args.length < 3) {
                    MessageFormatter.sendFormattedMessage(player, "&cPoprawne użycie: /manage remove world/block <nazwa>");
                    return true;
                }

                String removeType = args[2].toLowerCase();
                switch (removeType) {
                    case "world":
                        Main.getPlugin().getConfiguration().Worlds.remove(name);
                        MessageFormatter.sendFormattedMessage(player, "&aUsunięto świat: " + name);
                        break;
                    case "block":
                        try {
                            Main.getPlugin().getConfiguration().allowedBreakBlocks.remove(Material.valueOf(name.toUpperCase()));
                            MessageFormatter.sendFormattedMessage(player, "&aUsunięto blok: " + name);
                        } catch (IllegalArgumentException e) {
                            MessageFormatter.sendFormattedMessage(player, "&cNieprawidłowy materiał: " + name);
                        }
                        break;
                    default:
                        MessageFormatter.sendFormattedMessage(player, "&cNieznany typ: " + removeType);
                        break;
                }
                break;

            case "reload":
                Main.getPlugin().getConfiguration().load();
                MessageFormatter.sendFormattedMessage(player, "&aPrzeładowano konfigurację pluginu.");
                break;

            default:
                MessageFormatter.sendFormattedMessage(player, "&cNieznane polecenie: " + type);
                break;
        }

        return true;
    }
}