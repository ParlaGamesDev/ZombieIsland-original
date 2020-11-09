package xyz.yarinlevi.zombieisland.classes.custom.newswords.listeners;

import com.archyx.aureliumskills.skills.Skill;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.external.nbtapi.NBTAPIHandler;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class KopakaListener implements Listener {
    ItemStack kopakaItem = ZombieIsland.getInstance().getZiSwordsHandler().getSword("kopaka");

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {

            Player attacker = (Player) e.getDamager();
            LivingEntity entity = (LivingEntity) e.getEntity();

            ItemStack item = attacker.getInventory().getItemInMainHand();

            if (NBTAPIHandler.isItemTagExists(item, "sword.kopaka")) {
                if (AureliumSkillsHandler.isMeetLevelRequirement(attacker, Skill.FIGHTING, 5)) {
                    PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, (20 * ZombieIsland.getInstance().getKopakaSlownessDuration()), ZombieIsland.getInstance().getKopakaSlownessAmplifier());
                    entity.addPotionEffect(slowness);
                }
            }
        }
    }
}
