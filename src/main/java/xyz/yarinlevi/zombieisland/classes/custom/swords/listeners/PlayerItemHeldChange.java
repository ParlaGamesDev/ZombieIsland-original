package xyz.yarinlevi.zombieisland.classes.custom.swords.listeners;

import com.archyx.aureliumskills.skills.Skill;
import com.archyx.aureliumskills.stats.Stat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class PlayerItemHeldChange implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getInventory().getItem(event.getNewSlot());

        if (item != null && !item.getType().equals(Material.AIR)) {
            if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()))) {
                if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getStormBreaker())) {
                    if (AureliumSkillsHandler.isMeetLevelRequirement(player, Skill.FIGHTING, 5)) {
                        AureliumSkillsHandler.applyModifier(player, "STORMBREAKER", Stat.STRENGTH, 200);
                    }
                }
            } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getKopaka_Material()))) {
                if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getKopaka())) {
                    AureliumSkillsHandler.applyModifier(player, "KOPAKA", Stat.STRENGTH, 50);
                }
            } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getFireSword_Material()))) {
                if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getFireSword())) {
                    AureliumSkillsHandler.applyModifier(player, "FIRESWORD", Stat.STRENGTH, 150);
                }
            } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getPoisonWand_Material()))) {
                if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getPoisonWand())) {
                    AureliumSkillsHandler.applyModifier(player, "POISONWAND", Stat.STRENGTH, 300);
                }
            }
        }
    }
}
