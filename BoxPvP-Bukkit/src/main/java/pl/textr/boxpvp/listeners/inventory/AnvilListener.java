package pl.textr.boxpvp.listeners.inventory;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import pl.textr.boxpvp.commands.User.RepairCommand;
import pl.textr.boxpvp.utils.ChatUtil;


public class AnvilListener implements Listener
{
	



  
    

@EventHandler(priority = EventPriority.MONITOR)
  public void onClick1(InventoryClickEvent e) {
    Player player = (Player)e.getWhoClicked();
    if (ChatUtil.fixColor("&eKowadlo").equalsIgnoreCase(e.getView().getTitle())) {
      e.setCancelled(true);
      ItemStack item = e.getCurrentItem();
      if (item != null) {
        if (item.getType() == Material.EXPERIENCE_BOTTLE) {
          
          if (player.getInventory().getItemInMainHand().getType() != Material.AIR && RepairCommand.isTool(is.getType())) {
        	    player.getInventory().getItemInMainHand().setDurability((short)0);        
            player.setLevel(player.getLevel() - 5);
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 10.0F, 5.0F);
            ChatUtil.sendMessage(player, "&7Przedmiot zostal &anaprawiony");
            player.closeInventory();
          } else {
            
            player.closeInventory();
            ChatUtil.sendMessage(player, "&8[&C&l!&8] &cTego przedmiotu nie mozesz naprawic!");
          } 
          return;
        } 
        if (item.getType() == Material.SMITHING_TABLE) {
          if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
            player.closeInventory();
            ChatUtil.sendMessage(player, "&8[&C&l!&8] &cNie masz nic w reku!");
            return;
          } 
          if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
            player.getInventory().getItemInMainHand().setType(Material.DIAMOND_SWORD);
            player.updateInventory();
            ChatUtil.sendMessage(player, "&aPomyslnie zamieniles netherytowy miecz na diamentowy!");
            return;
          } 
          if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_HELMET) {
            player.getInventory().getItemInMainHand().setType(Material.DIAMOND_HELMET);
            player.updateInventory();
            ChatUtil.sendMessage(player, "&aPomyslnie zamieniles netherytowy helm na diamentowy!");
            
            return;
          } 
          if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_CHESTPLATE) {
            player.getInventory().getItemInMainHand().setType(Material.DIAMOND_CHESTPLATE);
            player.updateInventory();
            ChatUtil.sendMessage(player, "&aPomyslnie zamieniles netherytowy napiernik na diamentowy!");
            return;
          } 
          if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_LEGGINGS) {
            player.getInventory().getItemInMainHand().setType(Material.DIAMOND_LEGGINGS);
            player.updateInventory();
            ChatUtil.sendMessage(player, "&aPomyslnie zamieniles netherytowe spodnie na diamentowe!");
            return;
          } 
          if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_BOOTS) {
            player.getInventory().getItemInMainHand().setType(Material.DIAMOND_BOOTS);
            player.updateInventory();
            ChatUtil.sendMessage(player, "&aPomyslnie zamieniles netherytowe buty na diamentowe!");
            return;
          } 
          if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
            player.getInventory().getItemInMainHand().setType(Material.DIAMOND_PICKAXE);
            player.updateInventory();
            ChatUtil.sendMessage(player, "&aPomyslnie zamieniles netherytowy kilof na diamentowy!");
            return;
          } 
          if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_AXE) {
            player.getInventory().getItemInMainHand().setType(Material.DIAMOND_AXE);
            player.updateInventory();
            ChatUtil.sendMessage(player, "&aPomyslnie zamieniles netherytowa skieiere na diamentowa!");
            
            return;
          } 
          player.closeInventory();
          ChatUtil.sendMessage(player, "&8[&C&l!&8] &cPrzedmiot w Twojej rece nie jest netherytowy!");
        }
      }
    } 
  }
}
