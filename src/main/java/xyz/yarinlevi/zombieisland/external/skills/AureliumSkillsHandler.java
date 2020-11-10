package xyz.yarinlevi.zombieisland.external.skills;

import com.archyx.aureliumskills.AureliumSkills;
import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.skills.Skill;
import com.archyx.aureliumskills.stats.Stat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.external.nbtapi.NBTAPIHandler;

public class AureliumSkillsHandler {
    public static boolean isMeetLevelRequirement(Player player, Skill skill, int level) {
         return (AureliumAPI.getSkillLevel(player, skill) >= level);
    }

    public static void applyModifier(Player player, String modifierName, Stat stat, int value) {
        AureliumAPI.addStatModifier(player, modifierName, stat, value);
    }

    public static void removeModifier(Player player, String modifierName) {
        AureliumAPI.removeStatModifier(player, modifierName);
    }

    public static boolean meetsFightingRequirement(Player player, ItemStack item) {
        return isMeetLevelRequirement(player, Skill.FIGHTING, NBTAPIHandler.getLevelRequired(item, Skill.FIGHTING));
    }

    public static void applyTimedModifier(Player player, String modifierName, Stat stat, int value, int seconds) {
        int actualTime = (seconds * 20);
        applyModifier(player, modifierName, stat, value);
        Bukkit.getScheduler().scheduleSyncDelayedTask(ZombieIsland.getInstance(), new Runnable() {
            @Override
            public void run() {
                removeModifier(player, modifierName);
            }
        }, actualTime);
    }
}
