package me.ned.rpgCore;

import me.ned.rpgCore.commands.WoodcuttingCommand;
import me.ned.rpgCore.listeners.MiningListener;
import me.ned.rpgCore.listeners.WoodcuttingListener;
import me.ned.rpgCore.skills.Mining;
import me.ned.rpgCore.skills.SkillManager;
import me.ned.rpgCore.skills.Woodcutting;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RPGCore extends JavaPlugin {

    @Override
    public void onEnable() {
        //This was the intellij suggested fix?
        Objects.requireNonNull(this.getCommand("woodcutting")).setExecutor(new WoodcuttingCommand());
        SkillManager.registerSkill(new Woodcutting());
        SkillManager.registerSkill(new Mining());
        getServer().getPluginManager().registerEvents(new WoodcuttingListener(), this);
        getServer().getPluginManager().registerEvents(new MiningListener(), this);
        getLogger().info("RPGCore enabled.");

    }

    @Override
    public void onDisable() {
        getLogger().info("RPGCore disabled.");
    }
}
