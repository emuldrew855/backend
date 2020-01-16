package com.ebay.queens.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.ebay.queens.demo.Version1Api;

@SpringBootApplication
public class SpringBootMain implements CommandLineRunner {
	
	@Autowired
    private ExternalConfig externalConfig;
	
    @Bean
    ResourceConfig resourceConfig() {
        return new ResourceConfig().registerClasses(Version1Api.class, Paypal.class);
    }
    @Override
    public void run(String... args) throws Exception {
    	// TODO Auto-generated method stub
    	System.out.println("RUN METHOD");
    	System.out.println(externalConfig.getDeveloperName());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class);
    }
     

}