package me.ned.rpgCore.commands;

import me.ned.rpgCore.skills.Woodcutting;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WoodcuttingCommand implements CommandExecutor {

    private final Woodcutting woodcuttingSkill = new Woodcutting();

    //IntelliJ was yelling at me to annotate these as @NotNull? I'll look into it if it doesn't work.
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can run this command!");
            return true;
        }

        int level = woodcuttingSkill.getLevel(player);

        player.sendPlainMessage("Your Woodcutting level is: " + level);

        return true;
    }
}