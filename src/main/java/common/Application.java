package common;

import io.micronaut.runtime.Micronaut;

public class Application {

    private static final String ENVIRONMENT_PROP = "micronaut.environments";

    public static void main(String[] args) throws Exception {
        if(System.getProperty("micronaut.environments") != null){
            String env = System.getProperty("micronaut.environments");
            System.setProperty("logback.configurationFile","logback_"+env+".xml");
        } else if(System.getenv().containsKey("MICRONAUT_ENVIRONMENTS")){
            String env = (String)System.getenv().get("MICRONAUT_ENVIRONMENTS");
            System.setProperty("logback.configurationFile","logback_"+env+".xml");
        }
        Micronaut.run(Application.class);
    }
}