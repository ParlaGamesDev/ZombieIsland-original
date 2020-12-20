package xyz.yarinlevi.zombieisland.handlers.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityCombust implements Listener {

    @EventHandler
    public void entityCombustEvent(org.bukkit.event.entity.EntityCombustEvent e) {
        Entity en = e.getEntity();
        if(en instanceof Zombie || en instanceof Skeleton) {
            e.setCancelled(true);
        }
    }
}
