package com.ebay.queens.responses.searchitemresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"convertedFromValue","convertedFromCurrency"})
public class ShippingCost {
	private String value;
	private String currency;

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
