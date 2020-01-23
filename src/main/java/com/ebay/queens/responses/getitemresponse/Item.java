package com.ebay.queens.responses;

public class Item {
	private String autoPay;
	private String buyerProtection; 
	private String buyItNowPrice;
	private Charity charity; 
	private String country; 
	private String description;
	
	public Item(String autoPay, String buyerProtection, String buyItNowPrice, Charity charity, String country,
			String description) {
		this.autoPay = autoPay;
		this.buyerProtection = buyerProtection;
		this.buyItNowPrice = buyItNowPrice;
		this.charity = charity;
		this.country = country;
		this.description = description;
	}
	
	// Getters
	public String getAutoPay() {
		return autoPay;
	}

	public String getBuyerProtection() {
		return buyerProtection;
	}

	public String getBuyItNowPrice() {
		return buyItNowPrice;
	}

	public Charity getCharity() {
		return charity;
	}

	public String getCountry() {
		return country;
	}

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
