package xyz.yarinlevi.zombieisland.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.regions.SpawnerManager;
import xyz.yarinlevi.zombieisland.external.nbtapi.NBTAPIHandler;

import java.util.logging.Level;

public class AdminOnlyCommands implements CommandExecutor {
    //TODO: Replace messages to new system

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("register")) {
                if (args[1].equalsIgnoreCase("spawners")) {
                    try {
                        SpawnerManager.registerSpawners();
                        ZombieIsland.getInstance().getLogger().log(Level.INFO, "ZiSpawners were successfully registered.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        ZombieIsland.getInstance().getLogger().log(Level.SEVERE, "Something went wrong while registering spawners!");
                    }
                }
            } else if (args[0].equalsIgnoreCase("force")) {
                if (args[1].equalsIgnoreCase("save")) {
                    ZombieIsland.getInstance().getLogger().info("Forcibly saving data to MySQL (check that the mysql server wouldn't crash !:D )");
                }
            } else if (args[0].equalsIgnoreCase("nbt")) {
                if(sender instanceof Player) {
                    Player p = (Player) sender;
                    if (args[1].equalsIgnoreCase("set")) {
                        ItemStack nbtItem = NBTAPIHandler.addCustomTag(p.getInventory().getItemInMainHand(), args[2], args[3]);
                        p.getInventory().remove(p.getInventory().getItemInMainHand());
                        p.getInventory().addItem(nbtItem);
                    } else if (args[1].equalsIgnoreCase("check")) {
                        p.sendMessage("Status of the NBT tag you requested: " + NBTAPIHandler.isTagExists(p.getInventory().getItemInMainHand(), args[2], args[3]));
                    }
                }
            }
        }

        return false;
    }
}
