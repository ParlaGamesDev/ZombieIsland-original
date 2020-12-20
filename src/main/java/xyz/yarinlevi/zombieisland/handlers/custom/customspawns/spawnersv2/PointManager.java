package xyz.yarinlevi.zombieisland.handlers.custom.customspawns.spawnersv2;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.handlers.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PointManager {
    private final File file;
    private final FileConfiguration data;
    private int id;

    private final Map<Integer, Point> points = new HashMap<>();

    public PointManager() {
        file = new File(ZombieIsland.getInstance().getDataFolder(),"points.yml");
        data = YamlConfiguration.loadConfiguration(file);

        FileManager.registerData(file, data);

        id = data.getInt("id");

        if (!data.contains("points")) {
            data.createSection("points");
        } else {
            this.loadPoints();
        }

        Bukkit.getScheduler().runTaskTimerAsynchronously(ZombieIsland.getInstance(), new Runnable() {
            @Override
            public void run() {
                save();
            }
        }, 0L, 300L);
    }

    private void loadPoints() {
        ConfigurationSection points = data.getConfigurationSection("points");

        if (points != null) {
            ConfigurationSection pointSection;
            for (String key : points.getKeys(false)) {
                pointSection = points.getConfigurationSection(key);

                World world = Bukkit.getWorld(pointSection.getString("world"));
                Location loc = new Location(
                        world,
                        pointSection.getDouble("x"),
                        pointSection.getDouble("y"),
                        pointSection.getDouble("z")
                );

                int mobLevel = pointSection.getInt("mobLevel");
                String mobType = pointSection.getString("mobType");

                this.points.put(Integer.parseInt(key), new Point(Integer.parseInt(key), loc, mobType, mobLevel));
            }
        }
    }

    public Point getPoint(int id) {
        return this.points.get(id);
    }

    public Point newPoint(String mobType, int mobLevel, Location location) {
        id = id + 1;

        Point point = new Point(id, location, mobType, mobLevel);

        ConfigurationSection pointSection = data.getConfigurationSection("points").createSection(String.valueOf(id));

        pointSection.set("mobType", mobType);
        pointSection.set("mobLevel", mobLevel);
        pointSection.set("world", location.getWorld().getName());
        pointSection.set("x", location.getBlockX());
        pointSection.set("y", location.getBlockY());
        pointSection.set("z", location.getBlockZ());

        data.set("id", id);

        this.points.put(id, point);
        return point;
    }

    public void save() {
        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
