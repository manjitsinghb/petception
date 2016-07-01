package com.petception.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@SpringBootApplication
@ComponentScan("com.petception.*")
@PropertySource("index.properties")
public class Application {

    public static void main(String args[])
    {
        SpringApplication.run(Application.class,args);
    }
}
