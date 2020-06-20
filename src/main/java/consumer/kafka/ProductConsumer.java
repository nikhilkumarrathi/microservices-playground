package consumer.kafka;
import io.micronaut.configuration.kafka.annotation.*;
import io.micronaut.context.annotation.Requires;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import producer.vo.Product;


@Requires(env = "consumer")
@KafkaListener(offsetReset = OffsetReset.LATEST, groupId = "consumer-0")
public class ProductConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(ProductConsumer.class);

    @Topic("products")
    public void receive(@KafkaKey String key, Product product) {
        LOG.debug("Got Product - {} by {} ",key,product);
    }
}