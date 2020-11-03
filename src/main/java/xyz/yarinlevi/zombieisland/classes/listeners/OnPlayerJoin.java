package xyz.yarinlevi.zombieisland.classes.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.yarinlevi.zombieisland.ZombieIsland;

import java.sql.SQLException;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if(p.hasPermission("zombieisland.admin")) {
            ZombieIsland.getInstance().getMessageHandler().sendMessage(p, "admin_version_message");
        }
    }
}
