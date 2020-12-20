package xyz.yarinlevi.zombieisland.handlers.custom.swords.listeners;

import com.archyx.aureliumskills.stats.Stat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import xyz.yarinlevi.zombieisland.external.nbtapi.NBTAPIHandler;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class PlayerItemHeldChange implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getInventory().getItem(event.getNewSlot());

        if (item != null && !item.getType().equals(Material.AIR)) {
            if (NBTAPIHandler.isItemTagExists(item, "sword.stormbreaker")) {
                if (AureliumSkillsHandler.meetsFightingRequirement(player, item)) {
                    AureliumSkillsHandler.applyModifier(player, "STORMBREAKER", Stat.STRENGTH, NBTAPIHandler.getStrengthBoost(item));
                }
            } else if (NBTAPIHandler.isItemTagExists(item, "sword.kopaka")) {
                if (AureliumSkillsHandler.meetsFightingRequirement(player, item)) {
                    AureliumSkillsHandler.applyModifier(player, "KOPAKA", Stat.STRENGTH, NBTAPIHandler.getStrengthBoost(item));
                }
            } else if (NBTAPIHandler.isItemTagExists(item, "sword.firesword")) {
                if (AureliumSkillsHandler.meetsFightingRequirement(player, item)) {
                    AureliumSkillsHandler.applyModifier(player, "FIRESWORD", Stat.STRENGTH, NBTAPIHandler.getStrengthBoost(item));
                }
            } else if (NBTAPIHandler.isItemTagExists(item, "sword.poisonblade")) {
                if (AureliumSkillsHandler.meetsFightingRequirement(player, item)) {
                    AureliumSkillsHandler.applyModifier(player, "POISONWAND", Stat.STRENGTH, NBTAPIHandler.getStrengthBoost(item));
                }
            }
        }
    }
}
