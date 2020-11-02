package xyz.yarinlevi.zombieisland.classes.custom.skills.types;

import lombok.Getter;

import java.util.HashMap;

public class Combat {
    @Getter private static final HashMap<Integer, Integer> levels = new HashMap<>();

    public Combat() {
        levels.put(0, 0);
        levels.put(1, 1000);
        levels.put(2, 5000);
        levels.put(3, 10000);
        levels.put(4, 20000);
        levels.put(5, 40000);
        levels.put(6, 60000);
        levels.put(7, 80000);
        levels.put(8, 100000);
        levels.put(9, 125000);
        levels.put(10, 150000);
        levels.put(11, 200000);
        levels.put(12, 300000);
        levels.put(13, 400000);
        levels.put(14, 500000);
        levels.put(15, 750000);
        levels.put(16, 1000000);
        levels.put(17, 1250000);
        levels.put(18, 1500000);
        levels.put(19, 1750000);
        levels.put(20, 2000000);
    }
}
