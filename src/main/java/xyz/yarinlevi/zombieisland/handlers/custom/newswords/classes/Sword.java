package xyz.yarinlevi.zombieisland.handlers.custom.newswords.classes;

import com.archyx.aureliumskills.skills.Skill;
import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sword {
    @Getter private final ItemStack item;
    @Getter @Setter private int strengthBoost, levelRequired;
    @Getter private final Skill skillRequired;


    public Sword(String itemTag, ItemStack item, int strengthBoost, Skill skillRequired, int levelRequired) {
        this.strengthBoost = strengthBoost;
        this.levelRequired = levelRequired;
        this.skillRequired = skillRequired;

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("strengthBoost", this.strengthBoost);
        nbtItem.setString("ziItem", itemTag);
        nbtItem.setInteger("zombieisland-levelreq-" + this.skillRequired.name(), this.levelRequired);
        this.item = nbtItem.getItem();
    }

    public void give(Player p) {
        p.getInventory().addItem(item);
    }
}
