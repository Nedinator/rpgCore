package me.ned.rpgCore.listeners;

import me.ned.rpgCore.skills.Melee;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MeleeListener implements Listener {

    private final Melee meleeSkill = new Melee();

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player && event.getEntity() instanceof LivingEntity target) {
            meleeSkill.handleMelee(player, target);
        }
    }
}
