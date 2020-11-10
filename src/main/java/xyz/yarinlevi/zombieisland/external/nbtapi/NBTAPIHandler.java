package xyz.yarinlevi.zombieisland.external.nbtapi;

import com.archyx.aureliumskills.skills.Skill;
import com.archyx.aureliumskills.stats.Stat;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.plugin.NBTAPI;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class NBTAPIHandler {
    /**
     * Add a custom tag, specifically to identify an item, key is "ziItem" and value must be string.
     */
    public static ItemStack addCustomTag(ItemStack item, String tag) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("ziItem", tag);
        return nbtItem.getItem();
    }

    /**
     * Add a custom tag, key and value are changeable.
     */
    public static ItemStack addCustomTag(ItemStack item, String key, Object value) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setObject(key, value);
        return nbtItem.getItem();
    }

    public static ItemStack addStrengthBoost(ItemStack item, int value) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("strengthBoost", value);
        return nbtItem.getItem();
    }

    public static int getStrengthBoost(ItemStack item) {
        return new NBTItem(item).getInteger("strengthBoost");
    }

    public static int getLevelRequired(ItemStack item, Skill skill) {
        return new NBTItem(item).getInteger("zombieisland-levelreq-" + skill.name());
    }

    public static ItemStack setLevelRequired(ItemStack item, Skill skill, int level) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("zombieisland-levelreq-" + skill.name(), level);
        return nbtItem.getItem();
    }

    public static ItemStack addCustomTags(ItemStack item, HashMap<String, Object> tags) {
        NBTItem nbtItem = new NBTItem(item);
        tags.forEach(nbtItem::setObject);
        return nbtItem.getItem();
    }

    public static boolean isTagExists(ItemStack item, String key, Object value) {
        NBTItem nbtItem = new NBTItem(item);
        return nbtItem.hasKey(key) && nbtItem.getObject(key, value.getClass()).equals(value);
    }

    public static boolean isItemTagExists(ItemStack item, String tag) {
        NBTItem nbtItem = new NBTItem(item);
        return nbtItem.hasKey("ziItem") && nbtItem.getString("ziItem").equals(tag);
    }
}
