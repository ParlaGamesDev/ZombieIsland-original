package xyz.yarinlevi.zombieisland.classes.custom.newswords;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

public class Sword {
    @Getter private final ItemStack swordItem;

    public Sword(ItemStack swordItem) {
        this.swordItem = swordItem;
    }
}
