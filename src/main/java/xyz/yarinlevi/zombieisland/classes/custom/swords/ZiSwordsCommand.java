package xyz.yarinlevi.zombieisland.classes.custom.swords;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.custom.swords.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ZiSwordsCommand implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args[0].equalsIgnoreCase("give")) {
                if (args.length == 3) {
                    Player t;
                    if ((t = Bukkit.getPlayer(args[1])) != null) {
                        switch (args[2].toLowerCase()) {
                            case "stormbreaker":
                                t.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()), 1, ZombieIsland.getInstance().getStormBreaker()));
                                break;
                            case "firesword":
                                t.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getFireSword_Material()), 1, ZombieIsland.getInstance().getFireSword()));
                                break;
                            case "kopaka":
                                t.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getKopaka_Material()), 1, ZombieIsland.getInstance().getKopaka()));
                                break;
                            case "poisonwand":
                                t.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getPoisonWand_Material()), 1, ZombieIsland.getInstance().getPoisonWand()));
                                break;
                        }
                        sender.sendMessage(String.format("ZiSwords ->> Populated %s's inventory with %s", t.getName(), args[2]));
                        return true;
                    }
                    return false;
                }
            } else {
                sender.sendMessage("You are not able to execute this command!");
            }
        } else {
            final Player p = (Player) sender;
            if (args[0].equalsIgnoreCase("give")) {
                if (args.length == 3) {
                    Player t;
                    if ((t = Bukkit.getPlayer(args[1])) != null) {
                        switch (args[2].toLowerCase()) {
                            case "stormbreaker":
                                t.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()), 1, ZombieIsland.getInstance().getStormBreaker()));
                                break;
                            case "firesword":
                                t.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getFireSword_Material()), 1, ZombieIsland.getInstance().getFireSword()));
                                break;
                            case "kopaka":
                                t.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getKopaka_Material()), 1, ZombieIsland.getInstance().getKopaka()));
                                break;
                            case "poisonwand":
                                t.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getPoisonWand_Material()), 1, ZombieIsland.getInstance().getPoisonWand()));
                                break;
                        }
                        p.sendMessage(String.format("ZiSwords ->> Populated %s's inventory with %s", t.getName(), args[2]));
                        return true;
                    }
                    return false;
                }
                switch (args[1].toLowerCase()) {
                    case "stormbreaker":
                        p.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()), 1, ZombieIsland.getInstance().getStormBreaker()));
                        break;
                    case "firesword":
                        p.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getFireSword_Material()), 1, ZombieIsland.getInstance().getFireSword()));
                        break;
                    case "kopaka":
                        p.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getKopaka_Material()), 1, ZombieIsland.getInstance().getKopaka()));
                        break;
                    case "poisonwand":
                        p.getInventory().addItem(Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getPoisonWand_Material()), 1, ZombieIsland.getInstance().getPoisonWand()));
                        break;
                }
            }
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if(args.length == 1) {
            list.add("give");
            return list;
        } else if (args.length == 2 || args.length == 3) {
            list.add("FireSword");
            list.add("StormBreaker");
            list.add("Kopaka");
            list.add("PoisonWand");
            return list;
        }
        return list;
    }
}
