package com.ebay.queens.responses.findnonprofitresponse;

import javax.xml.bind.annotation.XmlElement;

public class NonProfit {
	private String name;
	private String logoURL;
	private String nonprofitId;
	private String externalId;
	private String homePageURL;

	public String getExternalId() {
		return externalId;
	}

	public String getHomePageURL() {
		return homePageURL;
	}

	public String getLogoURL() {
		return logoURL;
	}

	// Getters
	public String getName() {
		return name;
	}

	@XmlElement(name = "nonprofitId")
	public String getNonProfitId() {
		return nonprofitId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public void setHomePageURL(String homePageURL) {
		this.homePageURL = homePageURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setNonProfitId(String nonProfitId) {
		this.nonprofitId = nonProfitId;
	}

}
