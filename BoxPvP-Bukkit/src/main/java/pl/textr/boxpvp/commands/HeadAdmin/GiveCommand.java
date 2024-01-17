package pl.textr.boxpvp.commands.HeadAdmin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import api.Commands.BaseCommand;
import api.Commands.CommandInfo;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "give", description = "Dawanie przedmiotów graczom", usage = "/give <gracz> <id[:base]> [ilosc]", permission = "core.cmd.admin")
public class GiveCommand extends BaseCommand {

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        final Player p = Bukkit.getPlayer(args[0]);
        final String[] datas = args[1].split(":");
        final Material m = ChatUtil.getMaterial(datas[0]);
        short data = 0;
        if (datas.length > 1) {
            data = Short.parseShort(datas[1]);
        }
        ItemStack item = null;
        if (p == null) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
       
        }
        if (m == null) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cNazwa lub ID przedmiotu jest bledne!");
        }
        if (args.length == 2) {
            item = new ItemStack(m,  data);
        } else if (args.length == 3) {
        	item = new ItemStack(m, ChatUtil.isInteger(args[2]) ? Integer.parseInt(args[2]) : (int) data);
            }
        if (item == null) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cWystapil blad podczas dawania przedmiotu!");
        }
        ChatUtil.giveItems(p, item);
        p.updateInventory();
        ChatUtil.sendMessage(sender, "&7Dales &f" + m.name() + "&8:&f" + data + " &7(&f" + item.getAmount() + "&7) graczowi &f" + p.getName());
        return ChatUtil.sendMessage(sender, "&7Otrzymales &f" + m.name() + "&8:&f" + data + " &7(&f" + item.getAmount());
    }
}
