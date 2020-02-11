package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	private String autoPay;
	private String buyerProtection; 
	private String buyItNowPrice;
	private Charity charity; 
	private String country; 
	private String description;
	
	// Getters
	@XmlElement(name="AutoPay")
	public String getAutoPay() {
		return autoPay;
	}
	@XmlElement(name="BuyerProtection")
	public String getBuyerProtection() {
		return buyerProtection;
	}
	@XmlElement(name="BuyItNowPrice")
	public String getBuyItNowPrice() {
		return buyItNowPrice;
	}
	@XmlElement(name="Charity")
	public Charity getCharity() {
		return charity;
	}
	@XmlElement(name="Country")
	public String getCountry() {
		return country;
	}
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	
	// Setters
	public void setAutoPay(String autoPay) {
		this.autoPay = autoPay;
	}

	public void setBuyerProtection(String buyerProtection) {
		this.buyerProtection = buyerProtection;
	}

	public void setBuyItNowPrice(String buyItNowPrice) {
		this.buyItNowPrice = buyItNowPrice;
	}

	public void setCharity(Charity charity) {
		this.charity = charity;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setDescription(String description) {
		this.description = description;
	} 

}
