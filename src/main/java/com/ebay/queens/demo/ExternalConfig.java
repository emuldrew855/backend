package com.ebay.queens.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


//@PropertySource("classpath:application.properties") 
/**
 * Represents a class to hold all the external configuration details to gain access to api's and app configuration 
 */
@ConfigurationProperties(prefix = "app")
public class ExternalConfig {
	 
    private String developerName;

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	
}
