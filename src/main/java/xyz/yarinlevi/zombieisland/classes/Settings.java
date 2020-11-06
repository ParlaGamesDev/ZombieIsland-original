package xyz.yarinlevi.zombieisland.classes;

import lombok.Getter;
import lombok.Setter;

public class Settings {
    //Spawner Settings
    @Getter @Setter private int mobLimitPerChunk, spawnerRate;

    @Getter @Setter private int stormbreakerLevelRequirement, poisonbladeLevelRequirement, fireswordLevelRequirement, kopakaLevelRequirement;
    @Getter @Setter private int stormbreakerStrengthBoost, poisonbladeStrengthBoost, fireswordStrengthBoost, kopakaStrengthBoost;
}
