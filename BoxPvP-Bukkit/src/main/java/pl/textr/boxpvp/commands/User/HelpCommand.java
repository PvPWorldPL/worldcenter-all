package pl.textr.boxpvp.commands.User;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "help", description = "Komenda do wyświetlania pomocy", usage = "/help", permission = "core.cmd.user", aliases = { "pomoc" })
public class HelpCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player p, final String[] args) {
        ChatUtil.sendMessage(p, "");
        ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes(" &7/&#ff0404g&#fd1009r&#fb1b0ea&#f92713c&#f73218z &#f53e1e[&#f24923n&#f05528i&#ee602dc&#ec6c32k&#ea7737]  &8- &7Sprawdz swoj/kogos ranking"));
        ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes(" &7/&#ff0404l&#fa2111o&#f53e1eb&#ef5a2ab&#ea7737y &8- &7Teleportacja na lobby/spawn"));
        ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes(" &7/&#ff0404klan &8- &7Informacje o klanach"));
        ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes(" &7/&#ff0404kolorowytag &8- &7Dostep do kolorowego klanu"));
        
        ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes(" &7/&#ff0404h&#fe0b07e&#fc120al&#fb1a0ep&#fa2111o&#f82814p &#f72f17<&#f6361aw&#f53e1ei&#f34521a&#f24c24d&#f15327o&#ef5a2am&#ee612do&#ed6931s&#eb7034c&#ea7737> &8- &7Szybka sprawa do administracji"));
        ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes(" &7/&#ff0404r&#fa2111a&#f53e1en&#ef5a2ag&#ea7737i &8- &7Informacje o rangach"));
        ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes(" &7/&#ff0404k&#fd0e09o&#fb190dl&#f92312o&#f72e17r&#f5381bo&#f44320w&#f24d24y&#f05829n&#ee622ei&#ec6d32c&#ea7737k &8- &7Dostep do kolorowego nicku"));
        ChatUtil.sendMessage(p, "");
        return true;
    }
}
