package xyz.yarinlevi.zombieisland.classes.custom.newswords.listeners;

import com.archyx.aureliumskills.skills.Skill;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.external.nbtapi.NBTAPIHandler;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class FireSwordListener implements Listener {
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {

            Player p = (Player) e.getDamager();
            LivingEntity entity = (LivingEntity) e.getEntity();

            ItemStack item = p.getInventory().getItemInMainHand();

            if (NBTAPIHandler.isItemTagExists(item, "sword.firesword")) {
                if (AureliumSkillsHandler.isMeetLevelRequirement(p, Skill.FIGHTING, 5)) {
                    entity.setFireTicks((ZombieIsland.getInstance().getFireSwordBurn() * 20));
                } else {
                    ZombieIsland.getInstance().getMessageHandler().sendMessage(p, "level_too_low");
                    e.setCancelled(true);
                }
            }
        }
    }
}
