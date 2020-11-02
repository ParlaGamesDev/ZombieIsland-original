package xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types;

import com.sun.istack.internal.Nullable;
import lombok.Getter;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Mob {
    @Getter private final Tier tier;
    @Nullable @Getter private final ItemStack[] armor;
    @Getter private final EntityType entity;

    public Mob(Tier tier, ItemStack[] armor, EntityType entity) {
        this.tier = tier;

        this.armor = armor;
        this.entity = entity;
    }
}
