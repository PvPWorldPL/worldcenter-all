package pl.textr.boxpvp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemBuilder {
  private final List<String> lore;
  
  private final ItemStack item;
  
  private final ItemMeta meta;
  private Damageable damageable;

  
  public ItemBuilder(ItemStack item) {
    this.lore = new ArrayList<>();
    this.item = item;
    this.meta = item.getItemMeta();
  }
  
  public ItemBuilder(Material mat) {
    this(mat, 1);
  }
  
  public ItemBuilder(Material mat, int amount) {
    this(mat, amount, (short)0);
  }
  
  public ItemBuilder(Material mat, short data) {
    this(mat, 1, data);
  }
  
  public ItemBuilder(Material mat, int amount, short data) {
      this.lore = new ArrayList<>();
      this.item = new ItemStack(mat, amount);
      this.meta = this.item.getItemMeta();
      this.damageable = (Damageable) this.meta;
      this.damageable.setDamage(data);
      this.item.setItemMeta(this.meta);
  }

  
  public ItemBuilder addLores(String... lores) {
    this.lore.addAll(Arrays.asList(lores));
    return this;
  }
  
  public ItemBuilder addLore(String lore) {
    this.lore.add(lore);
    return this;
  }
  
  public ItemBuilder addEnchantment(Enchantment enchant, int level) {
    this.meta.removeEnchant(enchant);
    this.meta.addEnchant(enchant, level, true);
    return this;
  }
  
  public ItemBuilder setAmount(int amount) {
    this.item.setAmount(amount);
    return this;
  }
  
  public ItemBuilder setTitle(String title) {
    this.meta.setDisplayName(title);
    return this;
  }
  
  public ItemBuilder setColor(Color color) {
    if (!this.item.getType().name().contains("LEATHER_"))
      throw new IllegalArgumentException("Can only dye leather armor!"); 
    ((LeatherArmorMeta)this.meta).setColor(color);
    return this;
  }
  
  public static void fillGui(Inventory inventory) {
    ItemBuilder zolte = (new ItemBuilder(Material.MAGENTA_STAINED_GLASS_PANE, (short)3)).setTitle(ChatUtil.fixColor("&8#")).setGlow();
    ItemBuilder czarne = (new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, (short)15)).setTitle(ChatUtil.fixColor("&8#"));
    int[] yellow = { 
        2, 3, 4, 5, 6, 17, 47, 48, 49, 50, 
        51,27,18, 35,26 };
    int[] black = { 
        0, 1, 7, 8, 9, 17, 36, 44, 45, 46, 
        52, 53 };
    for (int i : yellow)
      inventory.setItem(i, zolte.ToItemStack()); 
    for (int i : black)
      inventory.setItem(i, czarne.ToItemStack()); 
  }
  
  public ItemBuilder setGlow() {
    this.meta.addEnchant(Enchantment.DURABILITY, 1, true);
    this.meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    return this;
  }


  public ItemStack ToItemStack() {
    if (!this.lore.isEmpty())
      this.meta.setLore(this.lore); 
    this.item.setItemMeta(this.meta);
    return this.item;
  }
  public String getTitle() {
    return meta.getDisplayName();
  }

}
