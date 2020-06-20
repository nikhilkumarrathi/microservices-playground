package micronautkafka;

import com.mongodb.Block;
import consumer.mongodb.MongoService;
import io.micronaut.test.annotation.MicronautTest;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import org.bson.Document;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.mongodb.client.model.Filters.*;

//https://mongodb.github.io/mongo-java-driver/3.4/driver/tutorials/perform-write-operations/
@MicronautTest(environments = "test")
public class MongoDBTest {

    private static final Logger LOG = LoggerFactory.getLogger(MongoDBTest.class);

    @Inject
    MongoService mongoService;

    Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            LOG.info(document.toJson());
        }
    };

    @Test
    public void testCollextion() throws Exception {

        var coll = mongoService.getProductCollection();

        // check if the Document Exists
        var existing = coll.find(eq("name","Cafe One"));
        var existingSTream = StreamSupport.stream(existing.spliterator(),false);
        existingSTream.findAny().ifPresent(x -> {
            coll.deleteOne(eq("_id",x.get("_id")));
            LOG.info("Deleted the Existing Object with ID: {}",x.get("_id").toString());
        });

        Document document = new Document("name", "Cafe One")
                .append("contact", new Document("phone", "228-555-0149")
                        .append("email", "cafeconleche@example.com")
                        .append("location", Arrays.asList(-73.92502, 40.8279556)))
                .append("stars", 3)
                .append("categories", Arrays.asList("Bakery", "Coffee", "Pastries"));
        LOG.info("Created new Document");
        coll.insertOne(document);
        var fetchedDoc = coll.find().first();
        LOG.info("Fetched newly Created Document ID: {}", fetchedDoc.get("_id").toString());
        LOG.info("Document: {}",fetchedDoc.toJson());

    }


}
