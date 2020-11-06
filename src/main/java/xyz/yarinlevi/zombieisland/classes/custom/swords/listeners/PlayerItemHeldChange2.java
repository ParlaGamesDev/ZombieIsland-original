package xyz.yarinlevi.zombieisland.classes.custom.swords.listeners;

import com.archyx.aureliumskills.skills.Skill;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class PlayerItemHeldChange2 implements Listener {
    
    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItem(event.getPreviousSlot());
        //ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if(item != null && !item.getType().equals(Material.AIR)) {
            if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()))) {
                if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getStormBreaker())) {
                    if (AureliumSkillsHandler.isMeetLevelRequirement(player, Skill.FIGHTING, 5)) {
                        AureliumSkillsHandler.removeModifier(player, "STORMBREAKER");
                    }
                }
            } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getKopaka_Material()))) {
                if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getKopaka())) {
                    if (AureliumSkillsHandler.isMeetLevelRequirement(player, Skill.FIGHTING, 5)) {
                        AureliumSkillsHandler.removeModifier(player, "KOPAKA");
                    }
                }
            } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getFireSword_Material()))) {
                if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getFireSword())) {
                    if (AureliumSkillsHandler.isMeetLevelRequirement(player, Skill.FIGHTING, 5)) {
                        AureliumSkillsHandler.removeModifier(player, "FIRESWORD");
                    }
                }
            } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getPoisonWand_Material()))) {
                if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getPoisonWand())) {
                    if (AureliumSkillsHandler.isMeetLevelRequirement(player, Skill.FIGHTING, 5)) {
                        AureliumSkillsHandler.removeModifier(player, "POISONWAND");
                    }
                }
            }
        }
    }
}
