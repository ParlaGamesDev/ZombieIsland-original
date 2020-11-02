package xyz.yarinlevi.zombieisland.classes.custom.skills.listeners;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.Handler;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types.Mob;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.entities.types.Tier;
import xyz.yarinlevi.zombieisland.classes.custom.skills.SkillHandler;
import xyz.yarinlevi.zombieisland.player.User;
import xyz.yarinlevi.zombieisland.player.data.Data;

public class CombatListener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().isDead()) {
            Entity entity = e.getEntity();
            if(e.getEntity().getKiller() != null) {
                final Player p = (Player) e.getEntity().getKiller();
                User user = Data.getUser(p);

                if (Handler.getTieredSpawnedEntities().containsKey(entity)) {

                    Mob mob = Handler.getTieredSpawnedEntities().get(entity);

                    Tier tier = mob.getTier();

                    int expGained = SkillHandler.calculateExpGain(tier.getTier());

                    user.addCombatEXP(expGained);

                    //p.spigot().sendMessage(ChatMessageType.ACTION_BAR, Utils.newTextComponent("&b+" + expGained + " Combat EXP"));

                    Handler.getTieredSpawnedEntities().remove(entity);
                } else {
                    Location loc = e.getEntity().getLocation();

                    ZombieIsland.getInstance().getLogger().warning("An invalid mob was killed at " + loc.toVector() + " by " + p.getName());

                    //Utils.sendMessage(p, "The mob you have killed is invalid and was reported to the developers.");
                }
            }
        }
    }
}
