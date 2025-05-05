package me.ned.rpgCore.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;

public class Sidebar implements Runnable {

    private static final Sidebar instance = new Sidebar();
    private final Map<UUID, Scoreboard> scoreboardMap = new HashMap<>();

    private Sidebar() {
    }

    public static Sidebar getInstance() {
        return instance;
    }

    private Scoreboard createPlayerScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.displayName(text("RPGCore", GREEN));

        // Initial placeholder scores
        objective.getScore("§aRecently trained:").setScore(2);
        objective.getScore("§fNone").setScore(1);

        player.setScoreboard(scoreboard);
        return scoreboard;
    }

    private void updateScoreboard(Player player) {
        UUID uuid = player.getUniqueId();
        Scoreboard scoreboard = scoreboardMap.computeIfAbsent(uuid, id -> createPlayerScoreboard(player));
        Objective objective = scoreboard.getObjective("test");
        if (objective == null) return;

        for (String entry : scoreboard.getEntries()) {
            scoreboard.resetScores(entry);
        }

        ScoreboardData.SkillInfo info = ScoreboardData.getRecentSkill(uuid);

        int line = 5;
        objective.getScore("§aRecently trained:").setScore(line--);
        objective.getScore("§f" + info.skillName).setScore(line--);
        objective.getScore("§aLevel: §f" + info.level).setScore(line--);
        objective.getScore("§aTotal XP: §f" + (int) info.totalXP).setScore(line--);
        objective.getScore("§aRecent Gain: §f" + (int) info.recentXP).setScore(line);
    }


    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updateScoreboard(player);
        }
    }
}
