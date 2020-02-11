package com.ebay.queens.responses.findnonprofitresponse;

import javax.xml.bind.annotation.XmlElement;

public class NonProfit {
	private String name;
	private String logoURL;
	private String nonprofitId; 
	private String externalId;
	private String homePageURL;
	
	// Getters
	public String getName() {
		return name;
	}
	public String getLogoURL() {
		return logoURL;
	}
	@XmlElement(name="nonprofitId")
	public String getNonProfitId() {
		return nonprofitId;
	}
	public String getExternalId() {
		return externalId;
	}
	public String getHomePageURL() {
		return homePageURL;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}
	public void setNonProfitId(String nonProfitId) {
		this.nonprofitId = nonProfitId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public void setHomePageURL(String homePageURL) {
		this.homePageURL = homePageURL;
	}

}
