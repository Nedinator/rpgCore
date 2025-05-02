package me.ned.rpgCore.skills;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Woodcutting implements Skills {
    @Override
    public String getName() {
        return "Woodcutting";
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
        // it gets mad if I remove this cause the interface has it >:(
    }

    public void handleWoodcutting(Player player, Material blockType) {
        int requiredLevel = getRequiredLevel(blockType);
        int xpGained = getXpForLog(blockType);

        if (getLevel(player) < requiredLevel) {
            player.sendMessage("§cYou need Woodcutting level " + requiredLevel + " to chop this tree.");
            return;
        }

        addXp(player, xpGained);

        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_WOOD_BREAK, 1.0f, 1.2f);
    }


    private int getXpForLog(Material material) {
        return switch (material) {
            case OAK_LOG -> 25;
            case BIRCH_LOG -> 30;
            case SPRUCE_LOG -> 35;
            case JUNGLE_LOG -> 40;
            case ACACIA_LOG -> 45;
            case DARK_OAK_LOG -> 50;
            default -> 10;
        };
    }

    private int getRequiredLevel(Material material) {
        return switch (material) {
            case BIRCH_LOG -> 15;
            case SPRUCE_LOG -> 30;
            case JUNGLE_LOG -> 45;
            case ACACIA_LOG -> 60;
            case DARK_OAK_LOG -> 75;
            default -> 1;
        };
    }
}
