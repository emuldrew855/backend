package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

public class Charity {
	private String charityName; 
	private String charityNumber; 
	private String donationPercent;
	private String charityID;
	private String mission; 
	private String logoURL;
	private String status;
	
	// Getters
	@XmlElement(name="CharityName")
	public String getCharityName() {
		return charityName;
	}
	@XmlElement(name="CharityNumber")
	public String getCharityNumber() {
		return charityNumber;
	}
	@XmlElement(name="DonationPercent")
	public String getDonationPercent() {
		return donationPercent;
	}
	@XmlElement(name="CharityID")
	public String getCharityID() {
		return charityID;
	}
	@XmlElement(name="Mission")
	public String getMission() {
		return mission;
	}
	@XmlElement(name="LogoURL")
	public String getLogoURL() {
		return logoURL;
	}
	@XmlElement(name="Status")
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
