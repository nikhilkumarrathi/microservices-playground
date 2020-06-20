package consumer.serde;

import io.micronaut.configuration.kafka.serde.JsonSerde;
import producer.vo.Product;

public class ProductSerde extends JsonSerde<Product> {
    public ProductSerde() {
        super(Product.class);
    }
}
