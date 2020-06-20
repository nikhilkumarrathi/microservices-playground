package analytics.controller;

import analytics.client.ApiClient;
import consumer.controller.ApiController;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Requires(env = "analytics")
@Controller(value = "/analysis")
public class ChartController {

    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    @Inject
    ApiClient apiClient;

    @Get(value = "/views")
    public List<Map<String,Object>> getViewData(){
        LOG.info("fetching data from API Server");
        var data = apiClient.getViews();
        return data.entrySet().stream()
                .map(e -> Map.of(
                        "label", e.getKey(), "data",
                        e.getValue().stream().map(v -> new Object[]{v.getTime(),v.getViews()}).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

}
