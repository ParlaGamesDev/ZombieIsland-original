package xyz.yarinlevi.zombieisland.classes.custom.newswords;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Sword {
    @Getter private final ItemStack swordItem;
    @Getter private final HashMap<String, Object> options = new HashMap<>();

    public Sword(ItemStack swordItem, HashMap<String, Object> options) {
        this.swordItem = swordItem;
        this.options.putAll(options);

        initOptions();
    }

    private void initOptions() {
        options.forEach((key, value) -> {

        });
    }
}
