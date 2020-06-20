package consumer.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import io.micronaut.context.annotation.Requires;

import javax.inject.Provider;
import javax.inject.Singleton;

@Requires(env = "consumer")
@Singleton
public class MongoDBClientProvider implements Provider<MongoClient> {
    @Override
    public MongoClient get() {
        MongoClientURI uri = new MongoClientURI("mongodb://admin:nimda@dockerhost/?authSource=admin&ssl=false");
        return new MongoClient( uri );
    }
}