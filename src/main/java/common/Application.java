package common;

import io.micronaut.runtime.Micronaut;

public class Application {

    private static final String MICRONAUT_ENVIRONMENTS_SYSTEM_PROP = "micronaut.environments";
    public static final String MICRONAUT_ENVIRONMENTS_ENV_PROP = "MICRONAUT_ENVIRONMENTS";

    public static void main(String[] args) throws Exception {
        if(System.getProperty(MICRONAUT_ENVIRONMENTS_SYSTEM_PROP) != null){
            String env = System.getProperty(MICRONAUT_ENVIRONMENTS_SYSTEM_PROP);
            System.setProperty("logback.configurationFile","logback_"+env+".xml");
        } else if(System.getenv().containsKey(MICRONAUT_ENVIRONMENTS_ENV_PROP)){
            String env = (String)System.getenv().get("MICRONAUT_ENVIRONMENTS");
            System.setProperty("logback.configurationFile","logback_"+env+".xml");
        }
        Micronaut.run(Application.class);
    }
}