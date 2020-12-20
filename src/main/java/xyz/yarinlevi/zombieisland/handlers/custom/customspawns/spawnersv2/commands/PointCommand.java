package xyz.yarinlevi.zombieisland.handlers.custom.customspawns.spawnersv2.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.handlers.custom.customspawns.spawnersv2.Point;

public class PointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage("Invalid arguments");
        } else if (args[0].equalsIgnoreCase("new")) {
            if (args.length == 6) {
                String mobType = args[1];
                int mobLevel = Integer.parseInt(args[2]);

                int[] xyz = new int[3];

                for (int i = 0; i < 3; i++) {
                    xyz[i] = Integer.parseInt(args[i + 3]);
                }

                Location location = new Location(p.getWorld(), xyz[0], xyz[1], xyz[2]);

                Point point = ZombieIsland.getInstance().getMythicMobsHandler().getPointManager().newPoint(mobType, mobLevel, location);

                p.sendMessage("Created a new point with id: " + point.getId());
                return true;
            }
        } else if (args[0].equalsIgnoreCase("save")) {
            ZombieIsland.getInstance().getMythicMobsHandler().getPointManager().save();
            p.sendMessage("Saved data.");
        } else if (args[0].equalsIgnoreCase("register")) {
            if (args.length == 2) {
                int id = Integer.parseInt(args[1]);

                ZombieIsland.getInstance().getMythicMobsHandler().registerPointSpawner(id);

                p.sendMessage("Registered point spawner for point #" + id);
            }
        }
        return true;
    }
}
