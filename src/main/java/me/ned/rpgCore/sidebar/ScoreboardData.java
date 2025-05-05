package me.ned.rpgCore.sidebar;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardData {

    private static final Map<UUID, SkillInfo> skillMap = new HashMap<>();

    public static void setRecentSkill(UUID playerId, String skillName, double totalXP, double recentGainedXP, int level) {
        skillMap.put(playerId, new SkillInfo(skillName, totalXP, recentGainedXP, level));
    }

    public static SkillInfo getRecentSkill(UUID playerId) {
        return skillMap.getOrDefault(playerId, new SkillInfo("None", 0.0, 0.0, 0));
    }

    public static class SkillInfo {
        public final String skillName;
        public final double totalXP;
        public final double recentXP;
        public final int level;

        public SkillInfo(String skillName, double totalXP, double recentXP, int level) {
            this.skillName = skillName;
            this.totalXP = totalXP;
            this.recentXP = recentXP;
            this.level = level;
        }
    }
}
