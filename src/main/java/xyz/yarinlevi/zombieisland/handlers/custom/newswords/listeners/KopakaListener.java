package xyz.yarinlevi.zombieisland.handlers.custom.newswords.listeners;

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
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {

            Player p = (Player) e.getDamager();
            LivingEntity entity = (LivingEntity) e.getEntity();

            ItemStack item = p.getInventory().getItemInMainHand();

            if (NBTAPIHandler.isItemTagExists(item, "sword.kopaka")) {
                if (AureliumSkillsHandler.meetsFightingRequirement(p, item)) {
                    PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, (20 * ZombieIsland.getInstance().getKopakaSlownessDuration()), ZombieIsland.getInstance().getKopakaSlownessAmplifier());
                    entity.addPotionEffect(slowness);
                } else {
                    ZombieIsland.getInstance().getMessageHandler().sendMessage(p, "level_too_low");
                    e.setDamage(1.0);
                    e.setCancelled(true);
                }
            }
        }
    }
}
