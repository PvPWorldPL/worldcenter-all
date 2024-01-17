package pl.textr.boxpvp.commands.HeadAdmin;

import org.bukkit.command.CommandSender;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "slot", description = "Ustawianie liczby slotów", usage = "/slot <liczba>", permission = "core.cmd.admin")
public class SlotCommand extends BaseCommand {
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        if (!ChatUtil.isInteger(args[0])) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cPodana wartosc nie jest liczba!");
       
        }
        final int slot = Main.getPlugin().getConfiguration().slot = Integer.parseInt(args[0]);
        
        Main.getPlugin().getConfiguration().save();
        return ChatUtil.sendMessage(sender, "&7Ustawiles sloty na &f" + slot);
    }
}
