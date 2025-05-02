package me.ned.rpgCore.skills;

import org.bukkit.entity.Player;

public interface Skills {
    String getName();
    int getLevel(Player player);
    void addXp(Player player, double amount);
    void onAction(Player player);
}
