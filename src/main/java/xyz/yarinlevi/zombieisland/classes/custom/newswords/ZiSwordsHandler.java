package xyz.yarinlevi.zombieisland.classes.custom.newswords;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.custom.swords.utils.Utils;

import java.util.HashMap;

public class ZiSwordsHandler {
    private final HashMap<String, ItemStack> swords = new HashMap<>();

    public ZiSwordsHandler() {
        swords.put("stormbreaker", Utils.createItemWithNBTTag("sword.stormbreaker", Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()), 1, ZombieIsland.getInstance().getStormBreaker()));
        swords.put("firesword", Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getFireSword_Material()), 1, ZombieIsland.getInstance().getFireSword()));
        swords.put("kopaka", Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getKopaka_Material()), 1, ZombieIsland.getInstance().getKopaka()));
        swords.put("poisonblade", Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getPoisonWand_Material()), 1, ZombieIsland.getInstance().getPoisonWand()));
    }

    public ItemStack getSword(String key) {
        return swords.getOrDefault(key, new ItemStack(Material.AIR));
    }
}