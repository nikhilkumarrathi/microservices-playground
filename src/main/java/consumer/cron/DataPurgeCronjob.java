package consumer.cron;

import com.mongodb.client.model.Filters;
import consumer.mongodb.MongoService;
import io.micronaut.context.annotation.Requires;
import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;

@Requires(env = "consumer")
@Singleton
public class DataPurgeCronjob {

    private static final Logger LOG = LoggerFactory.getLogger(DataPurgeCronjob.class);

    @Inject
    MongoService mongoService;

    @Scheduled(fixedDelay = "1m",initialDelay = "5s")
    public void deleteOldData(){
        Date pastDate = new Date(new Date().getTime() - 30*60L*1000L);
        var deleteRes = mongoService.getProductCollection().deleteMany(Filters.lt("time",pastDate));
        LOG.info("Deleted {} rows",deleteRes.getDeletedCount());
    }
}
