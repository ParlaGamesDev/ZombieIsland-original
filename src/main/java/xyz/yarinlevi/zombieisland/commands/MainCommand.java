package xyz.yarinlevi.zombieisland.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.yarinlevi.zombieisland.classes.custom.skills.SkillHandler;
import xyz.yarinlevi.zombieisland.classes.custom.skills.types.Combat;
import xyz.yarinlevi.zombieisland.player.User;
import xyz.yarinlevi.zombieisland.player.data.Data;

public class MainCommand extends BaseCommand {

    //TODO: Replace messages to new system

    @CommandAlias("zombieisland")
    @CommandPermission("zombieisland.use")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("you're not a player go away lol");
        } else {
            final Player p = (Player) sender;
            if(args[0].equalsIgnoreCase("stats")) {
                User user = Data.getUser(p);

                int currentCombatEXP = user.getCurrentCombatEXP();
                int currentCombatLevel = SkillHandler.getLevel(Combat.getLevels(), currentCombatEXP);

                float progressCombat = SkillHandler.calculatePercentage(Combat.getLevels(), currentCombatLevel, currentCombatEXP);

                p.sendMessage(String.format("-=-=-=-=- Stats for %s -=-=-=-=-", p.getName()));
                p.sendMessage(String.format("Combat Level: %s Progress to next level: %s/%s (%s", currentCombatLevel, currentCombatEXP, Combat.getLevels().get(currentCombatLevel+1), progressCombat) + "%)");
            }
        }
        return false;
    }
}
