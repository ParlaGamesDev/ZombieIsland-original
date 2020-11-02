package xyz.yarinlevi.zombieisland.player;

import lombok.Getter;

import java.util.UUID;

public class User {
    @Getter
    private final UUID playerUUID;

    public User(UUID playerUUID, int currentCombatEXP) {
        this.playerUUID = playerUUID;
        this.currentCombatEXP = currentCombatEXP;
    }

    // Experience
    @Getter private int currentCombatEXP;

    public void addCombatEXP(int exp) {
        currentCombatEXP = currentCombatEXP + exp;
    }
}
