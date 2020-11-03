package xyz.yarinlevi.zombieisland.classes.custom.swords.listeners;

import com.archyx.aureliumskills.skills.Skill;
import com.archyx.aureliumskills.stats.Stat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.custom.swords.utils.Utils;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class PlayerItemHeldChange implements Listener {

    @EventHandler
    public void PlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if(item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()))) {
            if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getStormBreaker())) {
                if (AureliumSkillsHandler.isMeetLevelRequirement(player, Skill.FIGHTING, 5)) {
                    AureliumSkillsHandler.applyModifier(player, "STORMBREAKER", Stat.STRENGTH, 50);
                }
            }
        } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getKopaka_Material()))) {
            if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getKopaka())) {

            }
        } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getFireSword_Material()))) {
            if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getFireSword())) {

            }
        } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getPoisonWand_Material()))) {
            if(item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getPoisonWand())) {
                
            }
        } else {
            ItemStack lastItem = player.getInventory().getItem(event.getPreviousSlot());

            assert lastItem != null;
            if(lastItem.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()))) {
                if (lastItem.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getStormBreaker())) {
                    if (AureliumSkillsHandler.isMeetLevelRequirement(player, Skill.FIGHTING, 5)) {
                        AureliumSkillsHandler.removeModifier(player, "STORMBREAKER");
                    }
                }
            } else if (lastItem.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getKopaka_Material()))) {
                if (lastItem.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getKopaka())) {

                }
            } else if (lastItem.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getFireSword_Material()))) {
                if (lastItem.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getFireSword())) {

                }
            } else if (lastItem.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getPoisonWand_Material()))) {
                if(lastItem.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getPoisonWand())) {

                }
            }
        }
    }
}
