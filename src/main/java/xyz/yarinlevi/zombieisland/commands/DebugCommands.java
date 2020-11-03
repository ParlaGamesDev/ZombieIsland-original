package xyz.yarinlevi.zombieisland.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.Handler;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types.Mob;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.helpers.CustomTiers;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.regions.RegionHandler;

public class DebugCommands implements CommandExecutor {
    //TODO: Replace messages to new system


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("you're not a player go away lol");
            return false;
        } else {
            final Player p = (Player) sender;

            if(args.length != 0) {
                if(args[0].equalsIgnoreCase("spawn")) {
                    String entity = args[1].toUpperCase();
                    int tier = Integer.parseInt(args[2].trim());
                    Mob mob = new Mob(CustomTiers.getTiers().get(tier), null, EntityType.valueOf(entity));

                    Handler.spawnCustomTieredMob(mob, p.getLocation());

                    p.sendMessage(String.format("Spawned debug: %s, tiered: %s", entity, tier));
                } else if (args[0].equalsIgnoreCase("addregion")) {
                    int x = Integer.parseInt(args[1]);
                    int y = Integer.parseInt(args[2]);
                    int z = Integer.parseInt(args[3]);
                    Location loc1 = new Location(p.getWorld(), x,y,z);

                    int x1 = Integer.parseInt(args[4]);
                    int y1 = Integer.parseInt(args[5]);
                    int z1 = Integer.parseInt(args[6]);
                    Location loc2 = new Location(p.getWorld(), x1,y1,z1);

                    RegionHandler.addRegionToData(args[7], loc1, loc2, Integer.parseInt(args[8]), Integer.parseInt(args[9]));
                    p.sendMessage("Saved region to data.");
                } else if (args[0].equalsIgnoreCase("testuuid")) {
                    p.sendMessage(String.valueOf(p.getUniqueId()));
                    p.sendMessage("uuid: " + p.getUniqueId());
                } else if (args[0].equalsIgnoreCase("whereami")) {
                    p.sendMessage("In region? " + RegionHandler.isInRegion(p.getLocation()));
                } else if (args[0].equalsIgnoreCase("test")) {
                    if(args[1].equalsIgnoreCase("placeholders")) {
                        /*p.sendMessage(Utils.newMessageString(p, "&5level: &a%zombieisland_skill.combat.level%"));
                        p.sendMessage(Utils.newMessageString(p, "&6exp: &e%zombieisland_skill.combat.exp%"));
                        p.sendMessage(Utils.newMessageString(p, "&7progression &b%zombieisland_skill.combat.progression% &3percent&1.")); */
                    }
                }
            }
            return true;
        }
    }
}
