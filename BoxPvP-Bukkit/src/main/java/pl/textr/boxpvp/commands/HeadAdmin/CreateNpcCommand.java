
package pl.textr.boxpvp.commands.HeadAdmin;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import api.Commands.CommandInfo;
import api.Commands.PlayerCommandExecutor;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;

@CommandInfo(name = "createnpc", description = "Tworzy NPC", usage = "/createnpc <typ> <nazwa>", permission = "core.cmd.admin")
public class CreateNpcCommand extends PlayerCommandExecutor {

    @Override
    public boolean onCommand(final Player player, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(player, ChatUtil.fixColor(Main.getPlugin().getConfiguration().usage(this.getUsage())));
        }

        EntityType entityType = EntityType.valueOf(args[0].toUpperCase());
        String npcName = args[1];

        NPC npc = CitizensAPI.getNPCRegistry().createNPC(entityType, npcName);
        npc.spawn(player.getLocation());

        removeNPCFromTabList(npc, player);

        return true;
    }

    private void removeNPCFromTabList(NPC npc, Player player) {
        try {
            Class<?> packetClass = Class.forName("net.minecraft.server." + getServerVersion() + ".PacketPlayOutPlayerInfo");
            Object removePlayerPacket = packetClass.getDeclaredConstructor().newInstance();

            Field actionField = packetClass.getDeclaredField("a");
            actionField.setAccessible(true);
            actionField.set(removePlayerPacket, EnumPlayerInfoAction.REMOVE_PLAYER);

            Field playerField = packetClass.getDeclaredField("b");
            playerField.setAccessible(true);
            playerField.set(removePlayerPacket, player.getUniqueId());

            sendPacket(player, removePlayerPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendPacket(Player player, Object packet) throws Exception {
        String version = getServerVersion();
        Class<?> packetClass = Class.forName("net.minecraft.server." + version + ".Packet");

        Object craftPlayer = player.getClass().getMethod("getHandle").invoke(player);
        Object playerConnection = craftPlayer.getClass().getField("playerConnection").get(craftPlayer);

        playerConnection.getClass().getMethod("sendPacket", packetClass).invoke(playerConnection, packet);
    }

    private String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }
}