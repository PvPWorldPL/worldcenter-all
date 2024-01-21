package pl.textr.core.commands.HeadAdmin;

import org.bukkit.command.CommandSender;

import pl.textr.core.LobbyPlugin;
import pl.textr.core.commands.Api.Command;
import pl.textr.core.utils.other.ChatUtil;

public class SlotCommand extends Command
{
    public SlotCommand() {
        super("slot", "ustawianie liczby slotow", "/slot <liczba>", "core.cmd.admin");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, LobbyPlugin.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        if (!ChatUtil.isInteger(args[0])) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cPodana wartosc nie jest liczba!");
       
        }
        final int slot = LobbyPlugin.getPlugin().getConfiguration().slot = Integer.parseInt(args[0]);
        LobbyPlugin.getPlugin().getConfiguration().save();
        return ChatUtil.sendMessage(sender, "&7Ustawiles sloty na &f" + slot);
    }
}
