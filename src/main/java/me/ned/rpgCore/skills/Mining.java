package me.ned.rpgCore.skills;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Mining implements Skills {
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

    }

    public void handleMining(Player player, Material blockType){
        int xpGained = getXpForOre(blockType);
        addXp(player, xpGained);
    }

    private int getXpForOre(Material material) {
        return switch (material) {
            case COAL_ORE, DEEPSLATE_COAL_ORE -> 10;
            case COPPER_ORE, DEEPSLATE_COPPER_ORE -> 15;
            case IRON_ORE, DEEPSLATE_IRON_ORE -> 30;
            case GOLD_ORE, DEEPSLATE_GOLD_ORE -> 40;
            case REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE -> 20;
            case LAPIS_ORE, DEEPSLATE_LAPIS_ORE -> 25;
            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE -> 50;
            case EMERALD_ORE, DEEPSLATE_EMERALD_ORE -> 60;
            case NETHER_QUARTZ_ORE -> 35;
            case NETHER_GOLD_ORE -> 30;
            default -> 1;
        };
    }

}
