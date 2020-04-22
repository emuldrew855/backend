package com.ebay.queens.responses.charityitemresponse;

public class CurrentPrice {
	private String value;
	private String currencyId;
	
	//Getters
	public String getCurrencyId() {
		return currencyId;
	}
	
	public String getValue() {
		return value;
	}
	
	// Setters
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
