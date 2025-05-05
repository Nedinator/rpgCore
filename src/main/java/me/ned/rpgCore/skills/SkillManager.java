package me.ned.rpgCore.skills;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import me.ned.rpgCore.mongo.MongoDBUtil;
import me.ned.rpgCore.sidebar.ScoreboardData;
import org.bson.Document;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkillManager {

    // MongoDB collection for storing player XP data
    private static final MongoCollection<Document> xpCollection = MongoDBUtil.getDatabase().getCollection("playerXP");

    // Map to keep track of registered skills
    private static final Map<String, Skills> registeredSkills = new HashMap<>();

    // Register a skill
    public static void registerSkill(Skills skill) {
        registeredSkills.put(skill.getName(), skill);
    }

    // Get the player's skill level based on the stored XP
    public static int getPlayerSkillLevel(Player player, Skills skill) {
        double xp = getXpFromDatabase(player, skill);
        return XpUtils.getLevelFromXp(xp);
    }

    public static double getPlayerXP(Player player, Skills skill) {
        return getXpFromDatabase(player, skill);
    }

    // Add XP to a skill and handle level-ups
    public static void addXpToSkill(Player player, Skills skill, double xp) {
        // Get the current XP of the player for this skill
        double currentXp = getXpFromDatabase(player, skill);
        int oldLevel = XpUtils.getLevelFromXp(currentXp);

        // Add the XP
        double newXp = currentXp + xp;
        updateXpInDatabase(player, skill, newXp);

        // Get the new level after XP is added
        int newLevel = XpUtils.getLevelFromXp(newXp);

        // If the player leveled up, notify them
        if (newLevel > oldLevel) {
            player.sendMessage("§aLevel up! Your " + skill.getName() + " level is now " + newLevel + "!");
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
        }

        ScoreboardData.setRecentSkill(player.getUniqueId(), skill.getName(), newXp, xp, newLevel);
    }

    // Retrieve XP for a given player and skill from MongoDB
    private static double getXpFromDatabase(Player player, Skills skill) {
        UUID uuid = player.getUniqueId();
        Document query = new Document("playerUUID", uuid.toString())
                .append("skillName", skill.getName());

        Document result = xpCollection.find(query).first();

        // Return XP if found, otherwise return 0
        if (result != null) {
            return result.getDouble("xp");
        }
        return 0.0; // No XP data found, return 0
    }

    // Update the player's XP in the MongoDB database
    private static void updateXpInDatabase(Player player, Skills skill, double xp) {
        UUID uuid = player.getUniqueId();
        Document query = new Document("playerUUID", uuid.toString())
                .append("skillName", skill.getName());
        Document update = new Document("$set", new Document("xp", xp));

        // Update or insert the XP for the player and skill
        xpCollection.updateOne(query, update, new UpdateOptions().upsert(true));
    }

    // Get all registered skills
    public static Collection<Skills> getAllSkills() {
        return registeredSkills.values();
    }
}
