package xyz.yarinlevi.zombieisland.external.skills;

import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.skills.Skill;
import com.archyx.aureliumskills.stats.Stat;
import org.bukkit.entity.Player;

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
}
