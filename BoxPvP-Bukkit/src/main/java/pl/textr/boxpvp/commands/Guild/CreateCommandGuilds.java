package pl.textr.boxpvp.commands.Guild;

import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import api.data.Clans;
import api.managers.ClanManager;
import api.redis.packet.guild.UpdateGuildCreatePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "zaloz", description = "Zakładanie Klanu", usage = "/zaloz <tag> <nazwa>", permission = "core.cmd.user")
public class CreateCommandGuilds extends PlayerCommandExecutor {
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (!Main.getPlugin().getConfiguration().enablecreate()) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cZakladanie klanow jest tymczasowo wylaczone!");
        }
        final Clans clans = ClanManager.getGuild(p);
   
        if (clans != null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cPosiadasz juz klan!");
        }
        if (args.length != 2) {
            return ChatUtil.sendMessage(p, Main.getPlugin().getConfiguration().usage(this.getUsage()));
        }
        
        final String tag = args[0].toUpperCase();
        final String name = args[1];
        if (tag.length() > 5 || tag.length() < 2) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cTag klanu musi zawierac 2-5 zankow");
        }
        if (name.length() > 30 || name.length() < 4) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNazwa klanu musi zawierac 2-5 znakow, nazwa 4-30 znakow");
        }
        if (ClanManager.getGuild(tag) != null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cIstenieje juz klan o takim tagu!");
        }
        if (ClanManager.getGuild(name) != null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cIstenieje juz klan o takiej nazwie!");
        }
        if (!ChatUtil.isAlphaNumeric(tag)) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cTag nie moze byc alfanumeryczny!");
        }
        if (!ChatUtil.isAlphaNumeric(name)) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNazwa nie moze byc alfanumeryczna!");
        }

        final Clans g = ClanManager.createGuild(tag.toUpperCase(), name, p);
        UpdateGuildCreatePacket UpdateGuildCreatePacket;
        UpdateGuildCreatePacket = new UpdateGuildCreatePacket(tag, p.getName());
        Main.getPlugin().getRedisService().publishAsync("UpdateGuildCreate", UpdateGuildCreatePacket);  
        ChatUtil.sendTitle(p, ChatUtil.fixColor(""), ChatUtil.fixColor("&8[&C&l!&8] &aZalozyles klan " + g.getTag() + " &8[&C&l!&8]"), 55, 55, 55);
    return true;
    }
}
