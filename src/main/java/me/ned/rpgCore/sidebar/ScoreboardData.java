package me.ned.rpgCore.sidebar;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardData {

    private static final Map<UUID, String> recentSkillMap = new HashMap<>();

    public static void setRecentSkill(UUID playerId, String skillName) {
        recentSkillMap.put(playerId, skillName);
    }

    public static String getRecentSkill(UUID playerId) {
        return recentSkillMap.getOrDefault(playerId, "None");
    }
}
