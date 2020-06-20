package consumer.kafka;

import consumer.serde.ProductSerde;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
        import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import org.apache.kafka.clients.consumer.ConsumerConfig;
        import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import producer.vo.Product;

import javax.inject.Named;
        import javax.inject.Singleton;
import java.time.Duration;
import java.util.Properties;

@Requires(env = "consumer")
@Factory
public class StreamFactory {

    public static final String STREAM_PRODUCT_ANALYTICS = "products-analytics";
    public static final String INPUT = "products";
    public static final String OUTPUT = "products-analysis";
    public static final String WORD_COUNT_STORE = "product-analysis-store";


    @Singleton
    @Named(STREAM_PRODUCT_ANALYTICS)
    KStream<String, Long> wordCountStream(ConfiguredStreamBuilder builder) {
        // set default serdes
        Properties props = builder.getConfiguration();
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, ProductSerde.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        KStream<String, Product> source = builder
                .stream(INPUT);

        KStream<String, Long> groupedByProductID = source
                .map((key,value) -> new KeyValue<>(value.getName(),value))
                .groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1)))
                .aggregate(
                        ()-> {
                            return 0L;
                        },
                        (key, value, aggregate) -> {
                            return aggregate+1;
                        },
                        Materialized.with(Serdes.String(),Serdes.Long())
                ).toStream((k,v) -> k.key());

        groupedByProductID
                //send to output using specific serdes
                .to(OUTPUT, Produced.with(Serdes.String(), Serdes.Long()));

        return groupedByProductID;
    }

}