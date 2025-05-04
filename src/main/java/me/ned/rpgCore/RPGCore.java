package me.ned.rpgCore;

import me.ned.rpgCore.commands.MiningCommand;
import me.ned.rpgCore.commands.WoodcuttingCommand;
import me.ned.rpgCore.listeners.MiningListener;
import me.ned.rpgCore.listeners.WoodcuttingListener;
import me.ned.rpgCore.sidebar.Sidebar;
import me.ned.rpgCore.skills.Mining;
import me.ned.rpgCore.skills.SkillManager;
import me.ned.rpgCore.skills.Woodcutting;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Objects;

public final class RPGCore extends JavaPlugin {

    private BukkitTask sidebarTask;

    @Override
    public void onEnable() {

        //This was the intellij suggested fix?
        Objects.requireNonNull(this.getCommand("woodcutting")).setExecutor(new WoodcuttingCommand());
        Objects.requireNonNull(this.getCommand("mining")).setExecutor(new MiningCommand());
        SkillManager.registerSkill(new Woodcutting());
        SkillManager.registerSkill(new Mining());
        getServer().getPluginManager().registerEvents(new WoodcuttingListener(), this);
        getServer().getPluginManager().registerEvents(new MiningListener(), this);
        getLogger().info("RPGCore enabled.");

        sidebarTask = getServer().getScheduler().runTaskTimer(this, Sidebar.getInstance(), 0, 20);
    }

    @Override
    public void onDisable() {
        getLogger().info("RPGCore disabled.");

        if (sidebarTask != null && !sidebarTask.isCancelled()) {
            sidebarTask.cancel();
        }
    }
}
