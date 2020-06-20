package analytics.cron;

import io.micronaut.context.annotation.Requires;
import io.micronaut.discovery.DiscoveryClient;
import io.micronaut.discovery.ServiceInstance;
import io.micronaut.health.HealthStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.micronaut.scheduling.annotation.Scheduled;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Requires(env = "analytics")
@Singleton
public class ConsulServiceCronJob {

    private static final Logger LOG = LoggerFactory.getLogger(ConsulServiceCronJob.class);

    @Inject
    private DiscoveryClient discoveryClient;


    @Scheduled(fixedRate = "1m",initialDelay = "3s")
    public void remoteServiceDetailsCheck() {
        discoveryClient.getInstances("consumerapp").subscribe(
            new Subscriber<List<ServiceInstance>>() {
                @Override
                public void onSubscribe(Subscription s) {
                    s.request(1000);
                }

                @Override
                public void onNext(List<ServiceInstance> serviceInstances) {
                    serviceInstances.stream()
                            .filter(s -> s.getHealthStatus().equals(HealthStatus.UP))
                            .forEach(s -> {
                        LOG.info("Service Instance: {}, {}, {},{}",s.getHost(), s.getPort(),s.getInstanceId(),s.getHealthStatus().getName());
                    });
                }
                @Override public void onError(Throwable t) { }
                @Override  public void onComplete() { }
            });
    }
}