package xyz.yarinlevi.zombieisland.classes.custom.customspawns.helpers;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types.Mob;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomMobs {
    @Getter @Setter private static File file;
    @Getter @Setter private static FileConfiguration data;

    @Getter private static final HashMap<Integer, Mob> mobs = new HashMap<>();

    public static void loadMobs () {
        ArrayList<String> unloadedMobs = new ArrayList<>(data.getKeys(false));
        if(unloadedMobs.isEmpty()) {
            ZombieIsland.getInstance().getLogger().warning("No mob(s) are defined!");
        } else {
            ZombieIsland.getInstance().getLogger().info("Loading mob(s)...");
            ConfigurationSection mobSection;
            ConfigurationSection armorSection;
            int tierId;
            ItemStack[] armor = new ItemStack[4];
            EntityType entityType;

            for (String mobString : unloadedMobs) {
                mobSection = data.getConfigurationSection(mobString);
                if(mobSection != null) {
                    tierId = mobSection.getInt("tier");
                    entityType = EntityType.valueOf(mobSection.getString("entity"));

                    if(mobSection.contains("armor")) {
                        for (String key : mobSection.getConfigurationSection("armor").getKeys(false)) {
    
                        }
                    }

                    Mob mob = new Mob(CustomTiers.getTiers().get(tierId), null, entityType);
                    mobs.put(Integer.parseInt(mobString), mob);
                }
            }
            ZombieIsland.getInstance().getLogger().info(String.format("Finished loading %s mobs(s)!", mobs.size()));
        }
    }

    public static void loadMobs2() {
        ArrayList<String> unloadedMobs = new ArrayList<>(data.getKeys(false));
        if(unloadedMobs.isEmpty()) {
            ZombieIsland.getInstance().getLogger().warning("No mob(s) are defined!");
        } else {
            ZombieIsland.getInstance().getLogger().info("Loading mob(s)...");
            ConfigurationSection mobSection;
            ConfigurationSection armorSection;
            int tierId;
            ItemStack[] armor = new ItemStack[4];
            EntityType entityType;

            for (String mobString : unloadedMobs) {
                mobSection = data.getConfigurationSection(mobString);
                if(mobSection != null) {
                    tierId = mobSection.getInt("tier");
                    entityType = EntityType.valueOf(mobSection.getString("entity"));

                    if(mobSection.contains("armor")) {
                        for (String key : mobSection.getConfigurationSection("armor").getKeys(false)) {
                            armorSection = mobSection.getConfigurationSection("armor").getConfigurationSection(key);
                            if(armorSection != null) {
                                ItemStack armorItem = new ItemStack(Material.valueOf(armorSection.getString("item")));
                                ItemMeta meta = armorItem.getItemMeta();

                                if (armorSection.contains("enchantments")) {
                                    for (String enchString : armorSection.getConfigurationSection("enchantments").getKeys(false)) {
                                        assert meta != null;
                                        meta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(enchString)), armorSection.getConfigurationSection("enchantments").getConfigurationSection(enchString).getInt("level"), true);
                                    }
                                    armorItem.setItemMeta(meta);
                                    armor[Integer.parseInt(key)] = armorItem;
                                }
                            }
                        }
                    }
                    Mob mob = new Mob(CustomTiers.getTiers().get(tierId), armor, entityType);
                    mobs.put(Integer.parseInt(mobString), mob);
                }
            }
            ZombieIsland.getInstance().getLogger().info(String.format("Finished loading %s mobs(s)!", mobs.size()));
        }
    }


    public static void save() {
        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
