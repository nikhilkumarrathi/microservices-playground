package consumer.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.micronaut.context.annotation.Requires;
import org.bson.Document;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Requires(env = "consumer")
@Singleton
public class MongoService {

    @Inject
    private MongoClient mongoClient;

    public MongoDatabase getDB(){
        return mongoClient.getDatabase("db");
    }

    public MongoCollection<Document> getProductCollection(){
        return getDB().getCollection("products");
    }
}
