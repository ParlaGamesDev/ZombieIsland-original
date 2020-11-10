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
import xyz.yarinlevi.zombieisland.external.nbtapi.NBTAPIHandler;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class PoisonBladeListener implements Listener {
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {

            Player p = (Player) e.getDamager();
            LivingEntity entity = (LivingEntity) e.getEntity();

            ItemStack item = p.getInventory().getItemInMainHand();

            if (NBTAPIHandler.isItemTagExists(item, "sword.poisonblade")) {
                if (AureliumSkillsHandler.meetsFightingRequirement(p, item)) {
                    if (Utils.calculateChance(3)) {
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
                } else {
                    ZombieIsland.getInstance().getMessageHandler().sendMessage(p, "level_too_low");
                    e.setDamage(1.0);
                    e.setCancelled(true);
                }
            }
        }
    }
}
