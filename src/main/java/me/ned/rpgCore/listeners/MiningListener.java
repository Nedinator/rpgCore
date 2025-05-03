package me.ned.rpgCore.listeners;

import me.ned.rpgCore.skills.Mining;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MiningListener implements Listener {
    private final Mining mining = new Mining();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = block.getType();
        ItemStack tool = player.getActiveItem();

        if (!blockType.name().endsWith("ORE")) return;
        mining.handleMining(player, blockType, tool);

    }

}
