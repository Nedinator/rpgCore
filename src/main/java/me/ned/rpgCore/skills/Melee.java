package me.ned.rpgCore.skills;

import org.bukkit.entity.Player;

public class Melee implements Skills{    @Override
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
        player.sendPlainMessage("You have gained " + amount + " XP in Woodcutting.");
    }

    @Override
    public void onAction(Player player) {

    }
}
