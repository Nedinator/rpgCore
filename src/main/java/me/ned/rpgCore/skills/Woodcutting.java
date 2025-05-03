package me.ned.rpgCore.skills;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

    public void handleWoodcutting(Player player, Material blockType, ItemStack tool) {
        if (tool != null && tool.getType().name().endsWith("_AXE")) {
            int xpGained = getXpForLog(blockType);
            addXp(player, xpGained);
        }
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
}
