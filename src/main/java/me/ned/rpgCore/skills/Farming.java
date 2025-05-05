package me.ned.rpgCore.skills;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Farming implements Skills {
    @Override
    public String getName() {
        return "Farming";
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

    public void handleFarming(Player player, Material blockType) {

    }
}
