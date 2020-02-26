package com.ebay.queens.demo;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

@SpringBootConfiguration
@SpringBootApplication
public class SpringBootMain implements CommandLineRunner {
	
	@Autowired
	private TokenUtilityClass tokenUtilityClass;
	
		
	@PostConstruct
	public void init() {
		
	}
	  @Bean ResourceConfig resourceConfig() {
		  return new ResourceConfig().registerClasses(Version1Api.class, Login.class); }



	@Override
	public void run(String... args) throws Exception {
		// test.authenticationToken();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class);
	}

}