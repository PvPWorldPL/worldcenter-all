package api.redis.cache.guild;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.data.Clans;
import api.managers.ClanManager;
import api.redis.PacketListener;
import api.redis.packet.guild.UpdateGuildInvitePacket;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

public class UpdateGuildInvitePacketCache implements PacketListener<UpdateGuildInvitePacket> {

    @Override
    public void handle(UpdateGuildInvitePacket packet) {
   final Clans g = ClanManager.getGuild(packet.getGuildTag());
   final String player = packet.getPlayerNameinvite();
   
   Player inviteplayer = Bukkit.getPlayer(player);

   
    	 for (final Player o : g.getOnlineMembers()) {
             ChatUtil.sendMessage(o, "&8[&C&l!&8] &7Zaproszenie do gracza &a" + packet.getPlayerNameinvite() + " &7zostalo wyslane przez &r" + packet.getPlayerName() + "&7!");     	 
    	 }
    	 
    	 
    	 g.getInvites().add(inviteplayer);   
         ChatUtil.sendMessage(inviteplayer.getPlayer(), "&7Zostales zaproszony do klanu: &r" + g.getTag() + "\n &7Aby zaakceptowac wpisz: &r/dolacz " + g.getTag());
       
    	 
         if (Main.getPlugin().getConfiguration().debug) {
             System.out.println(" gracz " + packet.getPlayerName() + " zostal zaproszony do klanu " + packet.getGuildTag()); 
             	 
         }
    }
}   	 
  