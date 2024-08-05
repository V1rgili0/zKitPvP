package it.danilotallaric.zkitpvp.items;

import it.danilotallaric.zkitpvp.utils.ChatUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemMaker implements Supplier<ItemStack> {
    private final ItemStack itemStack;

    private final ItemMeta itemMeta;

    public ItemMeta getItemMeta() {
        return this.itemMeta;
    }

    private final List<String> lore = new ArrayList<>();

    public List<String> getLore() {
        return this.lore;
    }

    public ItemMaker(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemMaker(Material material, String displayName) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName(ChatUtils.getColoredText(displayName));
    }

    public void addLoreLine(String line) {
        this.lore.add(ChatUtils.getColoredText(line));
    }

    public ItemMaker setUnbreakable(boolean unbreakable) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        return this;
    }

    public ItemMaker addEnchant(Enchantment enchantment, int level) {
        this.itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemMaker addFlag(ItemFlag flag) {
        this.itemMeta.addItemFlags(new ItemFlag[] { flag });
        return this;
    }

    public ItemMaker setLore(List<String> lines) {
        lines.forEach(line -> this.lore.add(ChatUtils.getColoredText(line)));
        return this;
    }

    public ItemStack get() {
        this.itemMeta.setLore(this.lore);
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }
}
