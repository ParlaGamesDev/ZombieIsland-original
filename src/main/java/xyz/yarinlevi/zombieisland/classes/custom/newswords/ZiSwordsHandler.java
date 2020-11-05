package xyz.yarinlevi.zombieisland.classes.custom.newswords;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.FileManager;

import java.io.File;
import java.util.HashMap;

public class ZiSwordsHandler {
    @Getter private final HashMap<String, Material> swordMaterials = new HashMap<>();
    @Getter private final HashMap<String, Sword> swords = new HashMap<>();

    public ZiSwordsHandler() {
    }


    public Sword getSword(String key) {
        return swords.getOrDefault(key, new Sword(new ItemStack(Material.AIR)));
    }
}