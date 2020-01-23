package com.ebay.queens.responses;

public class Price {
	private String value;
	private String currency;
	
	public Price(String value, String currency) {
		this.value = value;
		this.currency = currency;
	}
	
	// Getters
	public String getValue() {
		return value;
	}

	public String getCurrency() {
		return currency;
	}
	
	// Setters
	public void setValue(String value) {
		this.value = value;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
