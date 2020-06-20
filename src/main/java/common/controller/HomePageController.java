package common.controller;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.net.URI;

@Controller(value = "/")
public class HomePageController {


    @Value("${welcome.url:index.html}")
    private String url;

    @Get
    public HttpResponse<?> redirect(){
        return HttpResponse.redirect(URI.create(url));
    }
}
