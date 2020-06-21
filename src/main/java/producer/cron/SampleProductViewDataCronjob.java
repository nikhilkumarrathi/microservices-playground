package producer.cron;

import com.mongodb.client.model.Filters;
import consumer.mongodb.MongoService;
import consumer.vo.ProductViewCount;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.scheduling.annotation.Scheduled;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import producer.vo.Product;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URI;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Flow;

@Requires(env = "producer")
@Singleton
public class SampleProductViewDataCronjob {

    private static final Random random = new Random();
    private static final Logger LOG = LoggerFactory.getLogger(SampleProductViewDataCronjob.class);

    @Inject
    @Client("/")
    HttpClient client;

    @Scheduled(fixedDelay = "5s",initialDelay = "5s")
    public void generateProductView(){

        var product = randomProduct();
        LOG.info("sending: {}", product);
        var request = HttpRequest.POST(URI.create("/products/view"), product);
        client.toBlocking().exchange(request);
    }

    private static Product randomProduct(){
        var randId = random.nextInt(4);

        var product = new Product();

        product.setId(randId+"");
        product.setName("Product "+ (randId+1));
        product.setUrl("doesnt matter");

        return product;
    }
}
