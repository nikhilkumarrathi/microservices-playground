package analytics.client;

import consumer.vo.ProductViewCount;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;
import java.util.Map;

@Requires(env = "analytics")
@Client(id = "consumerapp")
public interface ApiClient {

    @Get(value = "/api/product/views")
    public Map<String, List<ProductViewCount>> getViews();

}
