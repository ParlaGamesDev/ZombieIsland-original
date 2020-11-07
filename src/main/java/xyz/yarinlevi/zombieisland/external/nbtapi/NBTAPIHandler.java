package xyz.yarinlevi.zombieisland.external.nbtapi;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class NBTAPIHandler {
    public static ItemStack setCustomTag(ItemStack item, String tag) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("zitag", tag);
        return nbtItem.getItem();
    }

    public static boolean isTagExists(ItemStack item, String tag) {
        NBTItem nbtItem = new NBTItem(item);
        return nbtItem.hasKey("zitag") && nbtItem.getString("zitag").equals(tag);
    }
}
