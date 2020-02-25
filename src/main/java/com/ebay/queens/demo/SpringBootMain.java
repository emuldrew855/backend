package com.ebay.queens.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

@SpringBootConfiguration
@SpringBootApplication
public class SpringBootMain implements CommandLineRunner {
/*	
	@Bean
	ResourceConfig resourceConfig() {
		return new ResourceConfig().registerClasses(TokenUtilityClass.class, Version1Api.class, Paypal.class);
	}*/
	
	 public TokenUtilityClass returnTokenUtilityClass () {
        return new TokenUtilityClass();
    }
	
    @DependsOn("TokenUtilityClass")
    public Paypal eventPublisherBean () {
        return new Paypal();
    }

    public Version1Api eventListenerBean () {
        return new Version1Api();
    }

	@Override
	public void run(String... args) throws Exception {
		// test.authenticationToken();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class);
	}

}