package com.ebay.queens.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Represents a class to manage retrieving all the external config information from the application.properties file
 */
@Component
@ConfigurationProperties(prefix = "queens.props")
public class ExternalConfig {
	private String developerName;
	private String securityAppName;
	private String certName;
	private String globalId;
	private String paypalAppId; 

	public String getCertName() {
		return certName;
	}

	// Getters
	public String getDeveloperName() {
		return developerName;
	}

	public String getGlobalId() {
		return globalId;
	}
	
	public String getPaypalAppId() {
		return paypalAppId;
	}
	
	public String getSecurityAppName() {
		return securityAppName;
	}


	public void setCertName(String certName) {
		this.certName = certName;
	}

	// Setters
	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public void setPaypalAppId(String paypalAppId) {
		this.paypalAppId = paypalAppId;
	}
	
	public void setSecurityAppName(String securityAppName) {
		this.securityAppName = securityAppName;
	}

}
