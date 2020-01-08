package com.ebay.queens.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:application.properties") 
public class ExternalConfig {
	
	@Autowired
	private Environment env;

	 
	 public String getValue()
	 {
	  return developerName;
	 }

	
	@Value("${app.developerName}")
    private String developerName;
	
	public static void main(String[] args) throws IOException {
		
	}
	
	ExternalConfig() {
		System.out.println("Constructor");
		System.out.println(this.developerName);
	}
	

	
	
}
