package xyz.yarinlevi.zombieisland.classes.custom.swords;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import xyz.yarinlevi.zombieisland.ZombieIsland;

import java.util.ArrayList;
import java.util.List;

public class ZiSwordsCommand implements CommandExecutor, TabCompleter {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args[0].equalsIgnoreCase("give")) {
                if (args.length == 3) {
                    Player t;
                    if ((t = Bukkit.getPlayer(args[1])) != null) {
                        t.getInventory().addItem(ZombieIsland.getInstance().getZiSwordsHandler().getSword(args[2].toLowerCase()));
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
                        t.getInventory().addItem(ZombieIsland.getInstance().getZiSwordsHandler().getSword(args[2].toLowerCase()));
                        p.sendMessage(String.format("ZiSwords ->> Populated %s's inventory with %s", t.getName(), args[2]));
                        return true;
                    }
                    return false;
                }
                p.getInventory().addItem(ZombieIsland.getInstance().getZiSwordsHandler().getSword(args[1].toLowerCase()));
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
            list.add("PoisonBlade");
            return list;
        }
        return list;
    }
}
