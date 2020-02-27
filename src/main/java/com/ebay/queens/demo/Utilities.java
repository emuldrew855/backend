package com.ebay.queens.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Represents a class to manage application information and promote code
 * reusability
 */
@Component
public class Utilities {
	String securityAppName;
	String globalId;
	String devName;
	String certName;
	String marktplaceId;
	String paypalAuthorizationToken;
	String ebayAuth;

	final int SITE_ID = 3;

	Utilities(String devName, String securityAppName, String globalId, String marketplaceId, String certName,
			String paypalAuth, String ebayAuth) {
		this.devName = devName;
		this.securityAppName = securityAppName;
		this.globalId = globalId;
		this.certName = certName;
		this.marktplaceId = marketplaceId;
		this.paypalAuthorizationToken = paypalAuth;
		this.ebayAuth = ebayAuth;
	}

	Utilities() {

	}

	// Getters
	public String getDevName() {
		return devName;
	}

	public String getGlobalId() {
		return globalId;
	}

	public String getSecurityAppName() {
		return securityAppName;
	}

	public String getCertName() {
		return certName;
	}

	public String getMarktplaceId() {
		return marktplaceId;
	}

	public String getPaypalAuthorizationToken() {
		return paypalAuthorizationToken;
	}

	public String getEbayAuth() {
		return ebayAuth;
	}

	// Setters
	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setSecurityAppName(String securityAppName) {
		this.securityAppName = securityAppName;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public void setMarktplaceId(String marktplaceId) {
		this.marktplaceId = marktplaceId;
	}

	public void setPaypalAuthorizationToken(String paypalAuthorizationToken) {
		this.paypalAuthorizationToken = paypalAuthorizationToken;
	}

	public void setEbayAuth(String ebayAuth) {
		this.ebayAuth = ebayAuth;
	}

}
