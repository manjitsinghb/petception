package com.petception.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@SpringBootApplication
@PropertySource(value = "classpath:breed.properties")
@ComponentScan(basePackages = "com.petception,app.*")
@ConfigurationProperties
@EnableAspectJAutoProxy
public class Application {

    public static void main(String args[])
    {
        SpringApplication.run(Application.class,args);
    }
}
