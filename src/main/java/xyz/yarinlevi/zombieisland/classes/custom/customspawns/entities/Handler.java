package xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types.Mob;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types.Tier;

import java.util.HashMap;

public class Handler {
    @Getter private static final HashMap<Entity, Mob> tieredSpawnedEntities = new HashMap<>();

    public static void spawnCustomTieredMob(Mob mob, Location location) {
        Entity entity = location.getWorld().spawnEntity(location, mob.getEntity());

        if(!entity.isDead()) {
            entity.setCustomName(String.format("[LEVEL:%s] %s", mob.getTier().getTier(), mob.getEntity().name()));
            entity.setCustomNameVisible(true);

            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(mob.getTier().getBaseHealth());
            livingEntity.setHealth(mob.getTier().getBaseHealth());
            if(mob.getArmor() != null) {
                if (mob.getArmor().length != 0) {
                    livingEntity.getEquipment().setArmorContents(mob.getArmor());
                }
            } else {
                ItemStack[] i = new ItemStack[4];
                livingEntity.getEquipment().setArmorContents(i);
            }
            livingEntity.setCanPickupItems(false);
            tieredSpawnedEntities.put(livingEntity, mob);
        }
    }

    public static void spawnTieredMob(EntityType entityType, Tier tier, Location location) {
        Entity entity = location.getWorld().spawnEntity(location, entityType);

        if(!entity.isDead()) {
            entity.setCustomName(String.format("[Tier: %s] %s", tier.getTier(), entityType.getKey()));
            entity.setCustomNameVisible(true);

            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(tier.getBaseHealth());
            livingEntity.setHealth(tier.getBaseHealth());
            livingEntity.setCanPickupItems(false);

            livingEntity.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS);
        }
    }
}
