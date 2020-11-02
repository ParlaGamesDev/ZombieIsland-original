package xyz.yarinlevi.zombieisland.classes.custom.customspawns.regions.types;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Region {
    @Getter private final Location loc1;
    @Getter private final Location loc2;
    @Getter private final int delayTicksBetweenSpawns;

    public Region(Location loc1, Location loc2, int delayTicksBetweenSpawns) {
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.delayTicksBetweenSpawns = delayTicksBetweenSpawns;
    }

    public Location getRandomLocation() {
        Preconditions.checkArgument(loc1.getWorld() == loc2.getWorld());
        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(loc1.getY(), loc2.getY());
        double minZ = Math.min(loc1.getZ(), loc2.getZ());

        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(loc1.getY(), loc2.getY());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        return new Location(loc1.getWorld(), randomDouble(minX, maxX), randomDouble(minY, maxY), randomDouble(minZ, maxZ));
    }

    public double randomDouble(double min, double max) {
        return min + ThreadLocalRandom.current().nextDouble(Math.abs(max - min + 1));
    }

    public boolean isInRegionHeightDependent(Location loc) {
        return Math.min(loc1.getBlockX(), loc2.getBlockX()) <= loc.getX() &&
                (Math.max(loc1.getBlockX(), loc2.getBlockX())) >= loc.getX() &&
                (Math.min(loc1.getBlockY(), loc2.getBlockY())) <= loc.getY() &&
                (Math.max(loc1.getBlockY(), loc2.getBlockY())) >= loc.getY() &&
                (Math.min(loc1.getBlockZ(), loc2.getBlockZ())) <= loc.getZ() &&
                (Math.max(loc1.getBlockZ(), loc2.getBlockZ())) >= loc.getZ();
    }

    public boolean isInRegion(Location loc) {
        return Math.min(loc1.getBlockX(), loc2.getBlockX()) <= loc.getX() &&
                (Math.max(loc1.getBlockX(), loc2.getBlockX())) >= loc.getX() &&
                (Math.min(loc1.getBlockZ(), loc2.getBlockZ())) <= loc.getZ() &&
                (Math.max(loc1.getBlockZ(), loc2.getBlockZ())) >= loc.getZ();
    }
}
