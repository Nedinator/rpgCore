package me.ned.rpgCore.skills;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Melee implements Skills {
    @Override
    public String getName() {
        return "Melee";
    }

    @Override
    public int getLevel(Player player) {
        return SkillManager.getPlayerSkillLevel(player, this);
    }

    public double getXP(Player player) {
        return SkillManager.getPlayerXP(player, this);
    }

    @Override
    public void addXp(Player player, double amount) {
        SkillManager.addXpToSkill(player, this, amount);
    }

    @Override
    public void onAction(Player player) {

    }

    public void handleMelee(Player player, LivingEntity target) {
        if (!target.isDead()) return;
        int xpGained = getXpForCombat(target);
        addXp(player, xpGained);
    }

    private int getXpForCombat(LivingEntity target) {
        return switch (target.getType()) {
            case ZOMBIE, SKELETON, CREEPER, SPIDER, ENDERMAN, BLAZE, WITCH, DROWNED -> 10;
            case GHAST -> 250;
            case ENDER_DRAGON -> 500;
            case COW, SHEEP, PIG, CHICKEN -> 2;
            case VILLAGER -> 5;
            case PLAYER -> 50;
            default -> 0;
        };
    }

}
