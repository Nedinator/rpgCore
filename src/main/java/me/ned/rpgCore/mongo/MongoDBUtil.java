package me.ned.rpgCore.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBUtil {
    private static final String URI = "mongodb://localhost:27017";  // MongoDB URI for local setup
    private static MongoClient client;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            client = MongoClients.create(URI);
            database = client.getDatabase("rpgCore"); // Name of the DB
        }
        return database;
    }

    public static void close() {
        if (client != null) {
            client.close();
        }
    }
}

