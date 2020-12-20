package xyz.yarinlevi.zombieisland.handlers.custom.swords.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.external.nbtapi.NBTAPIHandler;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class PlayerItemHeldChange2 implements Listener {
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItem(event.getPreviousSlot());
        //ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if(item != null && !item.getType().equals(Material.AIR)) {
            if (NBTAPIHandler.isItemTagExists(item, "sword.stormbreaker")) {
                if (AureliumSkillsHandler.meetsFightingRequirement(player, item)) {
                    AureliumSkillsHandler.removeModifier(player, "STORMBREAKER");
                }
            } else if (NBTAPIHandler.isItemTagExists(item, "sword.kopaka")) {
                if (AureliumSkillsHandler.meetsFightingRequirement(player, item)) {
                    AureliumSkillsHandler.removeModifier(player, "KOPAKA");
                }
            } else if (NBTAPIHandler.isItemTagExists(item, "sword.firesword")) {
                if (AureliumSkillsHandler.meetsFightingRequirement(player, item)) {
                    AureliumSkillsHandler.removeModifier(player, "FIRESWORD");
                }
            } else if (NBTAPIHandler.isItemTagExists(item, "sword.poisonblade")) {
                if (AureliumSkillsHandler.meetsFightingRequirement(player, item)) {
                    AureliumSkillsHandler.removeModifier(player, "POISONWAND");
                }
            }
        }
    }
}
