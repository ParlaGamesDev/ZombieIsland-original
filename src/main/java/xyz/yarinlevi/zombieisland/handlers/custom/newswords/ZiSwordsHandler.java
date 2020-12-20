package xyz.yarinlevi.zombieisland.handlers.custom.newswords;

import com.archyx.aureliumskills.skills.Skill;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.handlers.custom.newswords.classes.Sword;
import xyz.yarinlevi.zombieisland.handlers.custom.swords.utils.Utils;
import xyz.yarinlevi.zombieisland.external.nbtapi.NBTAPIHandler;

import java.util.HashMap;

public class ZiSwordsHandler {
    private final HashMap<String, ItemStack> swords = new HashMap<>();
    private final HashMap<String, Sword> newSwords = new HashMap<>();

    public ZiSwordsHandler() {
        ItemStack testItem1 = Utils.createItem(Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()), 1, ZombieIsland.getInstance().getStormBreaker());
        Sword testSword = new Sword("sword.testsword", testItem1, 1000, Skill.FIGHTING, 5);
        newSwords.put("sword.testsword", testSword);

        ItemStack stormbreaker = Utils.createItemWithNBTTag("sword.stormbreaker", Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()), 1, ZombieIsland.getInstance().getStormBreaker());
        stormbreaker = NBTAPIHandler.addStrengthBoost(stormbreaker, 400);
        stormbreaker = NBTAPIHandler.setLevelRequired(stormbreaker, Skill.FIGHTING, 20);

        swords.put("stormbreaker", stormbreaker);

        ItemStack firesword = Utils.createItemWithNBTTag("sword.firesword", Material.getMaterial(ZombieIsland.getInstance().getFireSword_Material()), 1, ZombieIsland.getInstance().getFireSword());
        firesword = NBTAPIHandler.addStrengthBoost(firesword, 250);
        firesword = NBTAPIHandler.setLevelRequired(firesword, Skill.FIGHTING, 15);

        swords.put("firesword", firesword);


        ItemStack kopaka = Utils.createItemWithNBTTag("sword.kopaka", Material.getMaterial(ZombieIsland.getInstance().getKopaka_Material()), 1, ZombieIsland.getInstance().getKopaka());
        kopaka = NBTAPIHandler.addStrengthBoost(kopaka, 50);
        kopaka = NBTAPIHandler.setLevelRequired(kopaka, Skill.FIGHTING, 10);

        swords.put("kopaka", kopaka);

        ItemStack poisonblade = Utils.createItemWithNBTTag("sword.poisonblade", Material.getMaterial(ZombieIsland.getInstance().getPoisonWand_Material()), 1, ZombieIsland.getInstance().getPoisonWand());
        poisonblade = NBTAPIHandler.addStrengthBoost(poisonblade, 650);
        poisonblade = NBTAPIHandler.setLevelRequired(poisonblade, Skill.FIGHTING, 40);

        swords.put("poisonblade", poisonblade);
    }

    public ItemStack getSword(String key) {
        return swords.getOrDefault(key, new ItemStack(Material.AIR));
    }

    public Sword getModularSword(String key) { return newSwords.getOrDefault(key, new Sword( "zi.void", new ItemStack(Material.AIR), 15, Skill.FIGHTING, 15)); }
}