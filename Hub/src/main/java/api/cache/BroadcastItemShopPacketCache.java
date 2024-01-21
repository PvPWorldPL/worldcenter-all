package api.cache;

import org.bukkit.Bukkit;

import api.packet.BroadcastItemShopPacket;
import api.redis.BroadcastType;
import api.redis.PacketListener;
import pl.textr.core.utils.other.ChatUtil;


public class BroadcastItemShopPacketCache implements PacketListener<BroadcastItemShopPacket> {

    public void handle(BroadcastItemShopPacket packet) {
    	
        BroadcastType broadcastType = packet.getType();       
        if (broadcastType == BroadcastType.CHAT_ITEMSHOP_SPONSOR) {
          //  Bukkit.getServer().getOnlinePlayers().forEach(p -> ChatUtil.sendMessage(p, ChatUtil.translateHexColorCodes((packet.getContent()))));
            Bukkit.getServer().broadcastMessage("");
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&7Gracz &r" + packet.getContent() + " &7kupil range &#4d84fb&lS&#4879e6&lP&#436dd2&lO&#3f62bd&lN&#3a56a8&lS&#354b94&lO&#303f7f&lR"));
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&#04fb05D&#0bfb0bz&#12fb12i&#19fb18e&#20fc1ek&#27fc24u&#2efc2bj&#35fc31e&#3cfc37m&#43fc3dy &#4afc44z&#51fd4aa &#58fd50w&#5ffd56s&#66fd5dp&#6dfd63a&#74fd69r&#7bfe6fc&#82fe76i&#89fe7ce &#90fe82s&#97fe88e&#9efe8fr&#a5fe95w&#acff9be&#b3ffa1r&#baffa8a&#c1ffae!"));
            Bukkit.getServer().broadcastMessage("");
           return;     
        }
        if (broadcastType == BroadcastType.CHAT_ITEMSHOP_VIP) {
            Bukkit.getServer().broadcastMessage("");
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&7Gracz &r" + packet.getContent() + " &7kupil range &#fbf700&lV&#d0c800&lI&#a59800&lP"));
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&#04fb05D&#0bfb0bz&#12fb12i&#19fb18e&#20fc1ek&#27fc24u&#2efc2bj&#35fc31e&#3cfc37m&#43fc3dy &#4afc44z&#51fd4aa &#58fd50w&#5ffd56s&#66fd5dp&#6dfd63a&#74fd69r&#7bfe6fc&#82fe76i&#89fe7ce &#90fe82s&#97fe88e&#9efe8fr&#a5fe95w&#acff9be&#b3ffa1r&#baffa8a&#c1ffae!"));
            Bukkit.getServer().broadcastMessage("");        	
           return;	
        }
        if (broadcastType == BroadcastType.CHAT_ITEMSHOP_SVIP) {
            Bukkit.getServer().broadcastMessage("");
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&7Gracz &r" + packet.getContent() + " &7kupil range &#effb0e&lS&#effb13&lV&#f0fb18&lI&#f0fb1d&lP"));
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&#04fb05D&#0bfb0bz&#12fb12i&#19fb18e&#20fc1ek&#27fc24u&#2efc2bj&#35fc31e&#3cfc37m&#43fc3dy &#4afc44z&#51fd4aa &#58fd50w&#5ffd56s&#66fd5dp&#6dfd63a&#74fd69r&#7bfe6fc&#82fe76i&#89fe7ce &#90fe82s&#97fe88e&#9efe8fr&#a5fe95w&#acff9be&#b3ffa1r&#baffa8a&#c1ffae!"));
            Bukkit.getServer().broadcastMessage("");        	
           return;	
        } 
        if (broadcastType == BroadcastType.CHAT_ITEMSHOP_ZWYKLYKLUCZ) {
            Bukkit.getServer().broadcastMessage("");
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&7Gracz &r" + packet.getContent() + " &7kupil &3&lZwykly Klucz &7x&r" + packet.getAmount()));
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&#04fb05D&#0bfb0bz&#12fb12i&#19fb18e&#20fc1ek&#27fc24u&#2efc2bj&#35fc31e&#3cfc37m&#43fc3dy &#4afc44z&#51fd4aa &#58fd50w&#5ffd56s&#66fd5dp&#6dfd63a&#74fd69r&#7bfe6fc&#82fe76i&#89fe7ce &#90fe82s&#97fe88e&#9efe8fr&#a5fe95w&#acff9be&#b3ffa1r&#baffa8a&#c1ffae!"));
            Bukkit.getServer().broadcastMessage("");      	       	
        	return;
        }
        if (broadcastType == BroadcastType.CHAT_ITEMSHOP_RZADKIKLUCZ) {
            Bukkit.getServer().broadcastMessage("");
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&7Gracz &r" + packet.getContent() + " &7kupil &a&lRzadki Klucz &7x&r" + packet.getAmount()));
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&#04fb05D&#0bfb0bz&#12fb12i&#19fb18e&#20fc1ek&#27fc24u&#2efc2bj&#35fc31e&#3cfc37m&#43fc3dy &#4afc44z&#51fd4aa &#58fd50w&#5ffd56s&#66fd5dp&#6dfd63a&#74fd69r&#7bfe6fc&#82fe76i&#89fe7ce &#90fe82s&#97fe88e&#9efe8fr&#a5fe95w&#acff9be&#b3ffa1r&#baffa8a&#c1ffae!"));
            Bukkit.getServer().broadcastMessage("");
        	return;
        }
        if (broadcastType == BroadcastType.CHAT_ITEMSHOP_EPICKIKLUCZ) {
            Bukkit.getServer().broadcastMessage("");
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&7Gracz &r" + packet.getContent() + " &7kupil &5&lEpicki Klucz &7x&r" + packet.getAmount()));
            Bukkit.getServer().broadcastMessage(ChatUtil.translateHexColorCodes("&#04fb05D&#0bfb0bz&#12fb12i&#19fb18e&#20fc1ek&#27fc24u&#2efc2bj&#35fc31e&#3cfc37m&#43fc3dy &#4afc44z&#51fd4aa &#58fd50w&#5ffd56s&#66fd5dp&#6dfd63a&#74fd69r&#7bfe6fc&#82fe76i&#89fe7ce &#90fe82s&#97fe88e&#9efe8fr&#a5fe95w&#acff9be&#b3ffa1r&#baffa8a&#c1ffae!"));
            Bukkit.getServer().broadcastMessage(""); 	
        	return;
        }
    }
    }





