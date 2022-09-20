package com.anerkenung.springboot;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.*;


@SpringBootApplication
public class SpringBootExampleApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootExampleApplication.class);
    }
    public static void main(String[] args) throws IOException {

        SpringApplication.run(SpringBootExampleApplication.class, args);


    }

}





