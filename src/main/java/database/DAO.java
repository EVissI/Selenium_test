package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;


public class DAO {
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    public DAO() {
        MongoDBConnection connection = new MongoDBConnection();
        client = connection.getClient();
        database = client.getDatabase("sber_cookie");
        collection = database.getCollection("cookies");
    }
    public String takeValue(String key) {
        Document document = collection.find(new Document(key, new Document("$exists", true))).first();
        if (document != null) {
            return document.getString(key);
        }
        return null;
    }
    public void putValue(String key, String value) {
        collection.deleteOne(new Document(key, new Document("$exists", true)));
        Document document = new Document(key, value);
        collection.insertOne(document);
    }

}
