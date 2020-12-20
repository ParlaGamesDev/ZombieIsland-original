package xyz.yarinlevi.zombieisland.handlers.custom.swords.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.yarinlevi.zombieisland.external.nbtapi.NBTAPIHandler;

import java.util.Random;

public class Utils {
    /**
     * Calculates chances.
     * @param max highest number. e.g. 1 in 100
     * @return
     */
    public static boolean calculateChance(int max) {
        Random rand = new Random();
        return rand.nextInt(max)==1;
    }

    public static ItemStack createItem(final Material material, final int amount, final String name) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack createItemWithNBTTag(String nbtTag, final Material material, final int amount, final String name) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);

        item.setItemMeta(meta);

        return NBTAPIHandler.addCustomTag(item, nbtTag);
    }
}
