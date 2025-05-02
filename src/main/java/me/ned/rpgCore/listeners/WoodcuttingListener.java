package me.ned.rpgCore.listeners;

import me.ned.rpgCore.skills.Woodcutting;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class WoodcuttingListener implements Listener {
    private final Woodcutting woodcutting = new Woodcutting();

    @EventHandler
    public void onLogBreak(BlockBreakEvent event){

        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = block.getType();

        if(!blockType.name().endsWith("_LOG")) return;
        woodcutting.handleWoodcutting(player, blockType);
    }
}
