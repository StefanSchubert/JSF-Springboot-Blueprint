package de.bluewhale.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = "de.bluewhale")
public class AppConfig {

    /*
    Usage example: env.getProperty("testbean.name"), In case you need to inject something
    in bean declarations below.
     */
    @Autowired
    Environment env;

    // Required, so that Spring @Value know how to interpret ${}
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
