package producer.controllers;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.tracing.annotation.NewSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import producer.kafka.ProductProducer;
import producer.vo.Product;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@Requires(env = "producer")
@Controller("/products")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Inject
    ProductProducer productProducer;

    @NewSpan("products.view")
    @Post(value = "/view",consumes = MediaType.APPLICATION_JSON)
    public HttpStatus greet(@Body Product product){
        LOG.info("Received a Product Request: {}", product);
        var  metadata = productProducer.sendProduct("myKey",product);
        LOG.info("Sent to Kafka: {}", metadata);
        return HttpStatus.ACCEPTED;
    }
}