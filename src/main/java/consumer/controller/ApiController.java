package consumer.controller;

import com.mongodb.Block;
import com.mongodb.client.model.Filters;
import consumer.mongodb.MongoService;
import consumer.vo.ProductViewCount;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Requires(env = "consumer")
@Controller("/api/product/")
public class ApiController {

    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    @Inject
    MongoService mongoService;

    private final Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            LOG.info(document.toJson());
        }
    };

    @Get(value = "/views")
    public Map<String,List<ProductViewCount>> greet(){
        LOG.info("serving Product Analysis Data");
        int units = 30;
        Date pastDate = new Date(new Date().getTime() - units*60L*1000L);
        var data = StreamSupport.stream(
                mongoService.getProductCollection().find(Filters.gt("time",pastDate)).spliterator()
                ,false)
                    .map(d -> new ProductViewCount(d.getString("name"),d.getDate("time"),d.getLong("views")))
                    .sorted(Comparator.comparingLong(x -> x.getTime().getTime()))
                .collect(Collectors.groupingBy(ProductViewCount::getName,Collectors.toList()));

        LOG.debug("{}",data);
        return data;
    }
}