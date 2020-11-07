package xyz.yarinlevi.zombieisland.classes.custom.newswords.listeners;

import com.archyx.aureliumskills.skills.Skill;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.custom.swords.utils.Utils;
import xyz.yarinlevi.zombieisland.external.nbtapi.NBTAPIHandler;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class StormBreakerListener implements Listener {
    ItemStack stormBreakerItem = ZombieIsland.getInstance().getZiSwordsHandler().getSword("stormbreaker");

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {

            Player attacker = (Player) e.getDamager();
            LivingEntity entity = (LivingEntity) e.getEntity();

            ItemStack item = attacker.getInventory().getItemInMainHand();

            if (NBTAPIHandler.isTagExists(item, "sword.stormbreaker")) {
                if (AureliumSkillsHandler.isMeetLevelRequirement(attacker, Skill.FIGHTING, 5)) {
                    if (Utils.calculateChance(3)) {
                        entity.getWorld().strikeLightning(entity.getLocation());
                    }
                }
            }
        }
    }
}
