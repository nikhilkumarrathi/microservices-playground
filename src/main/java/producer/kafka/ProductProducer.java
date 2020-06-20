package producer.kafka;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Requires;
import org.apache.kafka.clients.producer.RecordMetadata;
import producer.vo.Product;


@Requires(env = "producer")
@KafkaClient(id="product-client",
        acks = KafkaClient.Acknowledge.ALL
)
public interface ProductProducer{

    @Topic("products")
    RecordMetadata sendProduct(@KafkaKey String key, Product product);

}