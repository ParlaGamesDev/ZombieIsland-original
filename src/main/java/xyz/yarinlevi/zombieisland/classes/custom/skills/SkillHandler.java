package xyz.yarinlevi.zombieisland.classes.custom.skills;

import java.util.HashMap;
import java.util.Random;

public class SkillHandler {
    /**
     * Returns current level based on exp
     * @param levels Same hashmap used for the initialization of the skill
     * @param currentEXP Current skill experience
     * @return Skill's level
     */
    public static int getLevel(HashMap<Integer, Integer> levels, int currentEXP) {
        for (int lvl : levels.keySet()) {
            if (levels.get(lvl) <= currentEXP && levels.get(lvl+1) >= currentEXP) {
                return lvl;
            }
        }
        return 20;
    }

    /**
     * Calculates exact percentage per levels
     * @param levels Same hashmap used for the initialization of the skill
     * @param level Current skill level
     * @param currentEXP Current skill experience
     * @return Skill progression percentage
     */
    public static float calculatePercentage(HashMap<Integer, Integer> levels, int level, int currentEXP) {
        if(levels.containsKey(level+1)) {
            return (float) ((100 * currentEXP) / levels.get(level + 1));
        }
        return 100f;
    }

    /**
     * Generates a random ranged-number depending on Tier.
     * @param tier Tier of the kill mob
     * @return Random number
     */
    public static int calculateExpGain(int tier) {
        Random rnd = new Random();
        int r = rnd.nextInt(5) + 2;

        return ((r * tier));
    }
}
