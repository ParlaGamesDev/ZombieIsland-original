package xyz.yarinlevi.zombieisland.classes.custom.customspawns.regions;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.Handler;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types.Mob;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.regions.types.Region;

import java.util.ArrayList;
import java.util.stream.Stream;

public class SpawnerManager {
    @Getter private static final ArrayList<Material> INVALID_MATERIALS = new ArrayList<>();

    private static void injectInvalidBlocks() {
        Stream.of(
                Material.ACACIA_LEAVES,
                Material.BIRCH_LEAVES,
                Material.DARK_OAK_LEAVES,
                Material.JUNGLE_LEAVES,
                Material.OAK_LEAVES,
                Material.SPRUCE_LEAVES,
                Material.ACACIA_LOG,
                Material.BIRCH_LOG,
                Material.DARK_OAK_LOG,
                Material.JUNGLE_LOG,
                Material.OAK_LOG,
                Material.SPRUCE_LOG,
                Material.AIR
        ).forEach(INVALID_MATERIALS::add);
    }

    public static void registerSpawners() {
        injectInvalidBlocks();

        for (Region region : RegionHandler.getRegions().keySet()) {
            Mob mob = RegionHandler.getRegions().get(region);
            final Location[] randomLocation = new Location[1];
            final Location[] spawnLocation = new Location[1];
            final Block[] blockBelow = new Block[1];

            Bukkit.getScheduler().scheduleSyncRepeatingTask(ZombieIsland.getInstance(), new Runnable() {
                @Override
                public void run() {
                    randomLocation[0] = region.getRandomLocation();

                    if(randomLocation[0].isWorldLoaded() && randomLocation[0].getWorld() != null) {
                        spawnLocation[0] = getHighestBlock(randomLocation[0].getWorld(), randomLocation[0]);
                        blockBelow[0] = spawnLocation[0].subtract(0,1,0).getBlock();
                        spawnLocation[0].add(0, 1, 0);

                        if(!blockBelow[0].getBlockData().getMaterial().equals(Material.WATER)) {
                            if (spawnLocation[0].getChunk().getEntities().length <= ZombieIsland.getInstance().getSettings().getMobLimitPerChunk()) {
                                Handler.spawnCustomTieredMob(mob, spawnLocation[0]);
                            }
                        }
                    }
                }
            }, 900L, (region.getDelayTicksBetweenSpawns()));
        }
    }

    /*
    public static void registerSpawners() {
        for (Region region : RegionHandler.getRegions().keySet()) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(ZombieIsland.getInstance(), new Runnable() {
                @Override
                public void run() {
                    spawn(region);
                }
            }, 300L, 15);
        }
    }
     */

    public static void spawn(Region region) {
        Mob mob = RegionHandler.getRegions().get(region);

        Location randomLocation = region.getRandomLocation();

        if(randomLocation.isWorldLoaded() && randomLocation.getWorld() != null) {
            Location spawnLocation = getHighestBlock(randomLocation.getWorld(), randomLocation);

            if (spawnLocation.getChunk().getEntities().length < ZombieIsland.getInstance().getSettings().getMobLimitPerChunk()) {
                Handler.spawnCustomTieredMob(mob, spawnLocation);
            }
        }
    }

    private static Location getHighestBlock(World world, Location location){
        int i = world.getHighestBlockYAt(location);
        int x = location.getBlockX();
        int z = location.getBlockZ();
        while(i>0){
            if(!INVALID_MATERIALS.contains(new Location(world, x, i, z).getBlock().getBlockData().getMaterial()))
                return new Location(world, x, i, z).add(0,1,0);
            i--;
        }
        return new Location(world, x, 1, z);
    }
}