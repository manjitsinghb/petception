package com.petception.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.petception,app.contoller")
@ConfigurationProperties(value = "server.port=80")
public class Application {

    public static void main(String args[])
    {
        SpringApplication.run(Application.class,args);
    }
}
