package com.ebay.queens.responses.searchitemresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"convertedFromValue","convertedFromCurrency"})
public class Price {
	private String value;
	private String currency;

	public String getCurrency() {
		return currency;
	}

	// Getters
	public String getValue() {
		return value;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	// Setters
	public void setValue(String value) {
		this.value = value;
	}

}
