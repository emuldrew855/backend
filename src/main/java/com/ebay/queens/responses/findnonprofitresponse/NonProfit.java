package com.ebay.queens.responses.findnonprofitresponse;

public class NonProfit {
	private String name;
	private String mission;
	private String logoURL;
	private String nonProfitId; 
	private String externalId;
	private String homePageURL;
	
	// Getters
	public String getName() {
		return name;
	}
	public String getMission() {
		return mission;
	}
	public String getLogoURL() {
		return logoURL;
	}
	public String getNonProfitId() {
		return nonProfitId;
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
	public void setMission(String mission) {
		this.mission = mission;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}
	public void setNonProfitId(String nonProfitId) {
		this.nonProfitId = nonProfitId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public void setHomePageURL(String homePageURL) {
		this.homePageURL = homePageURL;
	}

}
