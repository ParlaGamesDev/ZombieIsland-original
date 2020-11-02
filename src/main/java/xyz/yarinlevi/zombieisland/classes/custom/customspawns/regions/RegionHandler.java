package xyz.yarinlevi.zombieisland.classes.custom.customspawns.regions;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types.Mob;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.helpers.CustomMobs;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.regions.types.Region;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RegionHandler {
    @Getter private static final HashMap<Region, Mob> regions = new HashMap<>();
    @Getter @Setter private static File file;
    @Getter @Setter private static FileConfiguration data;

    public static void addRegionToData(String regionId, Location loc1, Location loc2, int mobId, int delayTicksBetweenSpawns) {
        ConfigurationSection region = data.createSection(regionId);

        region.set(".world", loc1.getWorld());
        region.set(".customMobId", mobId);
        region.set(".delay", delayTicksBetweenSpawns);

        region.set(".x1", loc1.getBlockX());
        region.set(".y1", loc1.getBlockY());
        region.set(".z1", loc1.getBlockZ());

        region.set(".x2", loc2.getBlockX());
        region.set(".y2", loc2.getBlockY());
        region.set(".z2", loc2.getBlockZ());

        save();

        Region rg = new Region(loc1, loc2, delayTicksBetweenSpawns);
        Mob mob = CustomMobs.getMobs().get(mobId);

        regions.put(rg, mob);
    }

    public static void loadRegions() {
        if(!data.getKeys(false).isEmpty()) {
            for (String key : data.getKeys(false)) {
                World world = Bukkit.getWorld(data.getString(key + ".world"));
                int delayTicksBetweenSpawns = data.getInt(".delay");
                Location loc1 = new Location(
                        world,
                        data.getDouble(key + ".x1"),
                        data.getDouble(key + ".y1"),
                        data.getDouble(key + ".z1")
                );
                Location loc2 = new Location(
                        world,
                        data.getDouble(key + ".x2"),
                        data.getDouble(key + ".y2"),
                        data.getDouble(key + ".z2")
                );
                int mobId = data.getInt(key + ".customMobId");

                Mob mob = CustomMobs.getMobs().get(mobId);

                regions.put(new Region(loc1, loc2, delayTicksBetweenSpawns), mob);
            }
        }
    }

    public static boolean isInRegion(Location loc) {
        for (Region region : RegionHandler.getRegions().keySet()) {
            if(region.isInRegion(loc)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the region the entity is in.
     * @return
     */
    public static Region getRegion(Entity entity) {
        for (Region region : regions.keySet()) {
            if(region.isInRegion(entity.getLocation())) {
                return region;
            }
        }
        return null;
    }

    public static void save() {
        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
