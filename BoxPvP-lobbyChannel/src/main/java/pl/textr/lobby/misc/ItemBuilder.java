package pl.textr.lobby.misc;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import pl.textr.lobby.ping.HexTransform;

public class ItemBuilder extends ItemStack {

    private ItemMeta meta;

    public ItemBuilder(Material material, int amount, short durability) {
        this.setType(material);
        this.setAmount(amount);
        this.setDurability(durability);
        this.meta = this.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this(material, amount, (short) 0);
    }

    public ItemBuilder(Material material) {
        this(material, 1, (short) 0);
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.meta.setDisplayName(displayName);
        return this.build();
    }

    public ItemBuilder setLore(String... lore) {
        this.meta.setLore(Arrays.asList(lore));
        return this.build();
    }

    public ItemBuilder setLore(List<String> lore) {
        for (int i = 0; i < lore.size(); i++)
            lore.set(i, HexTransform.translateHexColorCodes(lore.get(i)));
        this.meta.setLore(lore);
        return this.build();
    }

    public ItemBuilder setSkullOwner(String skullOwner) {
        SkullMeta skullMeta = (SkullMeta) this.meta;
        skullMeta.setOwner(skullOwner);
        return this.build();
    }

    public ItemBuilder addGlowEffect() {
        this.meta.addEnchant(Enchantment.DURABILITY, 1, true);
        this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this.build();
    }

    public ItemBuilder build() {
        this.setItemMeta(this.meta);
        return this;
    }

}
