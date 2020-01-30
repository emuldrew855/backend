package com.ebay.queens.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "queens.props")
public class ExternalConfig {

	private String developerName;

	private String securityAppName;

	private String certName;
	
	private String ebayAuth; 

	// Getters
	public String getEbayAuth() {
		return ebayAuth;
	}
	
	public String getDeveloperName() {
		return developerName;
	}

	public String getSecurityAppName() {
		return securityAppName;
	}

	public String getCertName() {
		return certName;
	}

	// Setters
	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public void setSecurityAppName(String securityAppName) {
		this.securityAppName = securityAppName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}
	
	public void setEbayAuth(String ebayAuth) {
		this.ebayAuth = ebayAuth;
	}

}
