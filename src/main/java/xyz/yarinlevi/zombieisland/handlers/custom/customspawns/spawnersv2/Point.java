package xyz.yarinlevi.zombieisland.handlers.custom.customspawns.spawnersv2;

import io.lumine.xikage.mythicmobs.api.exceptions.InvalidMobTypeException;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import xyz.yarinlevi.zombieisland.ZombieIsland;

public class Point {
    @Getter private final int id;
    private final Location location;
    private final String mob;
    @Getter private final int mobLevel;

    public Point(int id, Location location, String mob, int mobLevel) {
        this.id = id;
        this.location = location;
        this.mob = mob;
        this.mobLevel = mobLevel;
    }

    public Entity spawn() throws InvalidMobTypeException {
        return ZombieIsland.getInstance().getMythicMobsHandler().getBukkitAPIHelper().spawnMythicMob(mob, location, mobLevel);
    }
}
