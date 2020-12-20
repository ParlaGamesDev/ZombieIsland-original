package xyz.yarinlevi.zombieisland.handlers.custom.customspawns;


import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import io.lumine.xikage.mythicmobs.api.exceptions.InvalidMobTypeException;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.handlers.custom.customspawns.spawnersv2.Point;
import xyz.yarinlevi.zombieisland.handlers.custom.customspawns.spawnersv2.PointManager;

public class MythicMobsHandler {
    @Getter private final BukkitAPIHelper bukkitAPIHelper;
    @Getter private final PointManager pointManager;
    private final ZombieIsland instance;

    public MythicMobsHandler(MythicMobs mythicInstance) {
        bukkitAPIHelper = mythicInstance.getAPIHelper();
        pointManager = new PointManager();
        instance = ZombieIsland.getInstance();
    }

    public void registerPointSpawner(int pointId) {
        this.registerPointSpawner(pointManager.getPoint(pointId));
    }

    private void registerPointSpawner(Point point) {
        Bukkit.getScheduler().runTaskTimer(instance, new Runnable() {
            @Override
            public void run() {
                try {
                    Entity entity = point.spawn();
                } catch (InvalidMobTypeException e) {
                    Bukkit.getServer().getLogger().severe(e.getMessage());
                    Bukkit.getServer().getLogger().severe("-----------");
                    e.printStackTrace();
                }
            }
        }, 60L, (5 * 20));
    }
}
