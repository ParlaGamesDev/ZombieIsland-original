package xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types;

import lombok.Getter;

public class Tier {
    @Getter private final int tier;
    @Getter private final int baseHealth;

    public Tier(int tier, int baseHealth) {
        this.tier = tier;

        this.baseHealth = baseHealth;
    }

}
