package me.ned.rpgCore.skills;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Farming implements Skills {
    @Override
    public String getName() {
        return "Mining";
    }

    @Override
    public int getLevel(Player player) {
        return SkillManager.getPlayerSkillLevel(player, this);
    }

    @Override
    public void addXp(Player player, double amount) {
        SkillManager.addXpToSkill(player, this, amount);
        player.sendPlainMessage("You have gained " + amount + " XP in Mining.");
    }

    @Override
    public void onAction(Player player) {

    }

    public void handleFarming(Player player, Material blockType) {

    }
}
