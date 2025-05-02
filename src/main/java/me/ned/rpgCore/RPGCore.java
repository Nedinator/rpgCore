package me.ned.rpgCore;

import me.ned.rpgCore.listeners.WoodcuttingListener;
import me.ned.rpgCore.skills.SkillManager;
import me.ned.rpgCore.skills.Woodcutting;
import org.bukkit.plugin.java.JavaPlugin;

public final class RPGCore extends JavaPlugin {

    @Override
    public void onEnable() {
        SkillManager.registerSkill(new Woodcutting());
        getServer().getPluginManager().registerEvents(new WoodcuttingListener(), this);
        getLogger().info("RPGCore enabled.");

    }

    @Override
    public void onDisable() {
        getLogger().info("RPGCore disabled.");
    }
}
