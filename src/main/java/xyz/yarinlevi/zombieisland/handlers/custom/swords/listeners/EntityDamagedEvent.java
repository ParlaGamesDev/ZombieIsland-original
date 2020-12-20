package xyz.yarinlevi.zombieisland.handlers.custom.swords.listeners;

import com.archyx.aureliumskills.skills.Skill;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.handlers.custom.swords.utils.Utils;
import xyz.yarinlevi.zombieisland.external.skills.AureliumSkillsHandler;

public class EntityDamagedEvent implements org.bukkit.event.Listener {
    @EventHandler
    public void onPlayerHit(org.bukkit.event.entity.EntityDamageByEntityEvent e) {
        //Testing.broadcastTest("Test 1");
        if(e.getDamager() instanceof Player) {
            //Testing.broadcastTest("Test 2");

            Player attacker = (Player) e.getDamager();
            LivingEntity entity = (LivingEntity) e.getEntity();
            //Testing.broadcastTest("Test 3");

            ItemStack item = attacker.getInventory().getItemInMainHand();
            //Testing.broadcastTest("Test 4");

            if(item.hasItemMeta()) {
                //Testing.broadcastTest("Test 5");
                assert false;
                //Testing.broadcastTest("Test 6");
                if(item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getStormBreaker_Material()))) {
                    if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getStormBreaker())) {
                        if (AureliumSkillsHandler.isMeetLevelRequirement(attacker, Skill.FIGHTING, 5)) {
                            //Testing.broadcastTest("Test_Stormbreaker 1");
                            if (Utils.calculateChance(3)) {
                                //Testing.broadcastTest("Test_Stormbreaker 2");
                                entity.getWorld().strikeLightning(entity.getLocation());
                                //Testing.broadcastTest("Test_Stormbreaker 3");
                            }
                        }
                        //Testing.broadcastTest("Test_Stormbreaker 4");
                    }
                } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getKopaka_Material()))) {
                    if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getKopaka())) {
                        //Testing.broadcastTest("Test_Kopaka 1");
                        PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, (20 * ZombieIsland.getInstance().getKopakaSlownessDuration()), ZombieIsland.getInstance().getKopakaSlownessAmplifier());
                        //Testing.broadcastTest("Test_Kopaka 2");
                        entity.addPotionEffect(slowness);
                        //Testing.broadcastTest("Test_Kopaka 3");
                    }
                } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getFireSword_Material()))) {
                    if (item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getFireSword())) {
                        //Testing.broadcastTest("Test_FireSword 1");
                        entity.setFireTicks((ZombieIsland.getInstance().getFireSwordBurn() * 20));
                        //Testing.broadcastTest("Test_FireSword 2");
                    }
                } else if (item.getType().equals(Material.getMaterial(ZombieIsland.getInstance().getPoisonWand_Material()))) {
                    if(item.getItemMeta().getDisplayName().equals(ZombieIsland.getInstance().getPoisonWand())) {
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
}
