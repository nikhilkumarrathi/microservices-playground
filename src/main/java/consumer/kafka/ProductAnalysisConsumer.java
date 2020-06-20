package consumer.kafka;

import consumer.mongodb.MongoService;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Requires;
import org.bson.BsonTimestamp;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import producer.vo.Product;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Date;


@Requires(env = "consumer")
@KafkaListener(offsetReset = OffsetReset.LATEST, groupId = "consumer-1")
public class ProductAnalysisConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(ProductAnalysisConsumer.class);

    @Inject
    MongoService mongoService;

    @Topic("products-analysis")
    public void receive(@KafkaKey String key, Long count) {
        LOG.info("Got Product Views - {} -> {} ",key,count);
        mongoService.getProductCollection().insertOne(
                new Document("name",key)
                        .append("views",count)
                        .append("time", new Date())
        );
    }
}