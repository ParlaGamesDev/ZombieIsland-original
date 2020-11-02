package xyz.yarinlevi.zombieisland.classes.custom.customspawns.helpers;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types.Tier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomTiers {
    @Getter @Setter private static File file;
    @Getter @Setter private static FileConfiguration data;

    @Getter private static final HashMap<Integer, Tier> tiers = new HashMap<>();

    public static void loadTiers () {
        ArrayList<String> unloadedTiers = new ArrayList<>(data.getKeys(false));
        if(unloadedTiers.isEmpty()) {
            ZombieIsland.getInstance().getLogger().warning("No mob tier(s) are defined!");
        } else {
            ZombieIsland.getInstance().getLogger().info("Loading mob tier(s)...");
            ConfigurationSection tierSection;
            for (String tierString : unloadedTiers) {
                tierSection = data.getConfigurationSection(tierString);
                if(tierSection != null) {
                    int id = tierSection.getInt("tier_ID");
                    Tier tier = new Tier(id, tierSection.getInt("baseHealth"));
                    tiers.put(id, tier);
                }
            }
            ZombieIsland.getInstance().getLogger().info(String.format("Finished loading %s mob tier(s)!", tiers.size()));
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
