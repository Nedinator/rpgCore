package me.ned.rpgCore.commands;

import me.ned.rpgCore.skills.Mining;
import me.ned.rpgCore.skills.SkillManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MiningCommand implements CommandExecutor {
    private final Mining miningSkill = new Mining();

    //IntelliJ was yelling at me to annotate these as @NotNull? I'll look into it if it doesn't work.
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can run this command!");
            return true;
        }

        int level = miningSkill.getLevel(player);
        double xp = SkillManager.getPlayerXP(player, miningSkill);
        player.sendPlainMessage("Your Mining level is: " + level + " and you have " + xp + " experience.");

        return true;
    }
}
