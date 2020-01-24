package com.ebay.queens.responses.searchitemresponse;

public class Charity {
	private String charityName; 
	private String charityNumber; 
	private String donationPercent;
	private String charityID;
	private String mission; 
	private String logoURL;
	private String status;
	public Charity(String charityName, String charityNumber, String donationPercent, String charityID, String mission,
			String logoURL, String status) {
		this.charityName = charityName;
		this.charityNumber = charityNumber;
		this.donationPercent = donationPercent;
		this.charityID = charityID;
		this.mission = mission;
		this.logoURL = logoURL;
		this.status = status;
	}
	
	// Getters
	public String getCharityName() {
		return charityName;
	}
	public String getCharityNumber() {
		return charityNumber;
	}
	public String getDonationPercent() {
		return donationPercent;
	}
	public String getCharityID() {
		return charityID;
	}
	public String getMission() {
		return mission;
	}
	public String getLogoURL() {
		return logoURL;
	}
	public String getStatus() {
		return status;
	}
	
	// Setters
	public void setCharityName(String charityName) {
		this.charityName = charityName;
	}
	public void setCharityNumber(String charityNumber) {
		this.charityNumber = charityNumber;
	}
	public void setDonationPercent(String donationPercent) {
		this.donationPercent = donationPercent;
	}
	public void setCharityID(String charityID) {
		this.charityID = charityID;
	}
	public void setMission(String mission) {
		this.mission = mission;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
	
	
}
