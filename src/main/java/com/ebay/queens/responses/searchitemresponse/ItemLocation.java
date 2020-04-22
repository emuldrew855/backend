package com.ebay.queens.responses.searchitemresponse;

public class ItemLocation {
	private String postalCode;
	private String country;

	public String getCountry() {
		return country;
	}

	// Getters
	public String getPostalCode() {
		return postalCode;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	// Setters
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
