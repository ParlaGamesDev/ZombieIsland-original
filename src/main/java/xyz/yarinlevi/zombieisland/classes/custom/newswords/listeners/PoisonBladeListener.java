package xyz.yarinlevi.zombieisland.classes.custom.newswords.listeners;

import com.archyx.aureliumskills.skills.Skill;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.custom.swords.utils.Utils;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class PoisonBladeListener implements Listener {
    ItemStack poisonBladeItem = ZombieIsland.getInstance().getZiSwordsHandler().getSword("poisonblade");

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {

            Player attacker = (Player) e.getDamager();
            LivingEntity entity = (LivingEntity) e.getEntity();

            ItemStack item = attacker.getInventory().getItemInMainHand();

            if (item.equals(poisonBladeItem)) {
                if (AureliumSkillsHandler.isMeetLevelRequirement(attacker, Skill.FIGHTING, 5)) {
                    if(Utils.calculateChance(3)) {
                        PotionEffect effect;
                        if (entity instanceof Zombie || entity instanceof Skeleton) {
                            effect = new PotionEffect(PotionEffectType.HEAL, (20 * ZombieIsland.getInstance().getPoisonWandDuration()), ZombieIsland.getInstance().getPoisonWandAmplifier());
                            entity.addPotionEffect(effect);
                            double dmg = entity.getLastDamage();
                            e.getDamager().sendMessage("applied poison to " + entity.getName() + " dealing " + dmg + " damage.");
                        } else {
                            effect = new PotionEffect(PotionEffectType.HARM, (20 * ZombieIsland.getInstance().getPoisonWandDuration()), ZombieIsland.getInstance().getPoisonWandAmplifier());
                            entity.addPotionEffect(effect);
                            e.getDamager().sendMessage("applied poison to " + entity.getName());
                        }
                    }
                }
            }
        }
    }
}
