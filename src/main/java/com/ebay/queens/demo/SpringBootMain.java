package com.ebay.queens.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.ebay.queens.demo.Version1Api;

@SpringBootApplication
public class SpringBootMain {

    @Bean
    ResourceConfig resourceConfig() {
        return new ResourceConfig().registerClasses(Version1Api.class, Paypal.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class);
    }
}