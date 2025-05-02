package me.ned.rpgCore.skills;

public class XpUtils {
    public static int getLevelFromXp(double xp) {
        int level = 1;
        double total = 0;
        for (int i = 1; i <= 99; i++) {
            total += Math.floor(i + 300 * Math.pow(2, i / 7.0));
            if (total / 4 > xp) break;
            level = i;
        }
        return level;
    }
}