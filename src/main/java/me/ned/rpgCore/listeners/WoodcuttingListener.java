package me.ned.rpgCore.listeners;

import me.ned.rpgCore.skills.Woodcutting;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class WoodcuttingListener implements Listener {
    private final Woodcutting woodcutting = new Woodcutting();

    @EventHandler
    public void onLogBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material blockType = event.getBlock().getType();
        ItemStack tool = player.getInventory().getItemInMainHand();

        if (!blockType.name().endsWith("_LOG")) return;
        woodcutting.handleWoodcutting(player, blockType, tool);
    }
}
