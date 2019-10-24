package com.ebay.queens.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringBootMain {

    @Bean
    ResourceConfig resourceConfig() {
        return new ResourceConfig().register(MyJaxRsResource.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class);
    }
}