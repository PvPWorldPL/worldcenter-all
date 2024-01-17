package pl.textr.boxpvp.commands.Admin;


import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.EnchantUtil;

@CommandInfo(name = "enchant", description = "nadawanie zaklec przedmiotom", usage = "/enchant [enchant] [poziom]", permission = "core.cmd.admin")
public class EnchantCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
            
        }
        final ItemStack item = p.getInventory().getItemInMainHand();
        final String enchantmentName = args[0];
        final Enchantment enchant = EnchantUtil.get(enchantmentName);
        if (enchant == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNie znaleziono podanego enchantu!");
        }
        int level = enchant.getMaxLevel();
        if (args.length == 2) {
            level = Integer.parseInt(args[1]);
        }
        item.addUnsafeEnchantment(enchant, level);
        
        return ChatUtil.sendMessage(p, "&7Enchant &f" + enchant.getKey().getKey().toLowerCase().replace("_", " ") + " &7został dodany do przedmiotu w Twojej ręce!");
    }
}
