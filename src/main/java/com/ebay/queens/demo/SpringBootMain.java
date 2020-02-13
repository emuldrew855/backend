package com.ebay.queens.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@SpringBootApplication
public class SpringBootMain implements CommandLineRunner {

	@Bean
	ResourceConfig resourceConfig() {
		return new ResourceConfig().registerClasses(Version1Api.class, TokenUtilityClass.class, Paypal.class);
	}

	@Override
	public void run(String... args) throws Exception {
		// test.authenticationToken();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class);
	}

}