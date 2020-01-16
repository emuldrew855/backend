package com.ebay.queens.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
public class ExternalConfig {

    @Value("${developerName}")
    private String developerName;
    
    @Value("${globalId}")
    private String globalId;
    
    @Value("${certName}")
    private String certName;

	// Getters
	public String getDeveloperName() {
		return developerName;
	}
	
    public String getGlobalId() {
		return globalId;
	}
    
    public String getCertName() {
		return certName;
	}
    
	// Setters
	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	
	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}
	
	public void setCertName(String certName) {
		this.certName = certName;
	}

}

