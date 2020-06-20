package producer.controllers;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.tracing.annotation.NewSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Requires(env = "all")
@Controller("/echo")
public class EchoController {

    private static final Logger LOG = LoggerFactory.getLogger(EchoController.class);

    @Get("/")
    @NewSpan("request.echo")
    public HttpResponse<String> test() {
        LOG.info("Received an Echo Request");
        return HttpResponse.<String>accepted().body("Hello!");
    }
}