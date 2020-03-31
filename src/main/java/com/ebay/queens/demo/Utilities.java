package com.ebay.queens.demo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

/**
 * Represents a class to manage generic application information and promote code reusability
 */
@Component
public class Utilities {
	public static Logger LOGGER = Logger.getLogger(Utilities.class.getName());
	public static Handler fileHandler  = null;
	private String securityAppName;
	private String globalId;
	private String devName;
	private String certName;
	private String marketplaceId;

	private String paypalAppId;

	final int SITE_ID = 3;
	Utilities() {

	}
	Utilities(String devName, String securityAppName, String globalId, String marketplaceId, String certName, String paypalAppId) {
		this.devName = devName;
		this.securityAppName = securityAppName;
		this.globalId = globalId;
		this.certName = certName;
		this.marketplaceId = marketplaceId;
		this.setPaypalAppId(paypalAppId);
		try {
			fileHandler  = new FileHandler("./BackendLogs.log");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCertName() {
		return certName;
	}

	// Getters
	public String getDevName() {
		return devName;
	}

	public String getGlobalId() {
		return globalId;
	}

	public String getMarktplaceId() {
		return marketplaceId;
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
	public void setDevName(String devName) {
		this.devName = devName;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public void setMarktplaceId(String marktplaceId) {
		this.marketplaceId = marktplaceId;
	}

	public void setPaypalAppId(String paypalAppId) {
		this.paypalAppId = paypalAppId;
	}

	public void setSecurityAppName(String securityAppName) {
		this.securityAppName = securityAppName;
	}

}
