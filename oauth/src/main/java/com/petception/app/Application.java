package com.petception.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by manjtsingh on 7/14/2016.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.petception.*")
@PropertySource(value = {"classpath:dao.properties","classpath:application.properties"})
@ConfigurationProperties
@EnableCaching
public class Application {

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class,args);
    }

}
