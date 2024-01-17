package api.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;

import java.util.List;

public class ZleceniodawcaMenu {

        public static void openZleceniodawcaGUI(Player player) {
            Inventory gui = Bukkit.createInventory(player, 54, ChatUtil.translateHexColorCodes("&#2cfe4e&lZleceniodawca"));

            List<String> zlecenia = Main.getPlugin().getKillContractManager().getActiveContractsForPlayer(player);

            for (int i = 0; i < zlecenia.size(); i++) {
                String zlecenie = zlecenia.get(i);
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                ItemMeta meta = head.getItemMeta();
                meta.setDisplayName(ChatUtil.fixColor("&aZlecenie dla " + zlecenie));

                UserProfile user = UserAccountManager.getUser(player);
                if (user != null && user.getKills() > 0) {
                    meta.setLore(ChatUtil.createList(ChatUtil.translateHexColorCodes(
                            "&7Gracz wykonał twoje zlecenie"
                    )));
                } else {
                    meta.setLore(ChatUtil.createList(ChatUtil.translateHexColorCodes(
                            "&7Zlecenie jest jeszcze wykonywane"
                    )));
                }

                head.setItemMeta(meta);
                gui.setItem(i, head);
            }

            player.openInventory(gui);
        }
    }
