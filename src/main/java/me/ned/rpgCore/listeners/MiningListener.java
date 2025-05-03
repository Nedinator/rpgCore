package me.ned.rpgCore.listeners;

import me.ned.rpgCore.skills.Mining;
import org.bukkit.Material;
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
        Material blockType = event.getBlock().getType();
        ItemStack tool = player.getInventory().getItemInMainHand();

        if (!blockType.name().endsWith("ORE")) return;
        mining.handleMining(player, blockType, tool);

    }

}
