package com.ebay.queens.demo;

import java.util.ArrayList;
import java.util.List;

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
@SpringBootApplication(scanBasePackages = {"com.ebay.queens.demo","com.ebay.queens.demo.resource"})
public class SpringBootMain implements CommandLineRunner {
	@Autowired
	private TokenUtilityClass tokenUtilityClass;
	
	@Bean ResourceConfig resourceConfig() {
	return new ResourceConfig().registerClasses(Version1Api.class, Login.class, SignUp.class, Paypal.class); }

	@Override
	public void run(String... args) throws Exception {
		// test.authenticationToken();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class, args);
	}

}