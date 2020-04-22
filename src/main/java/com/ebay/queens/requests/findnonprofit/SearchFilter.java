package com.ebay.queens.requests.findnonprofit;

public class SearchFilter {
	public SearchFilter(String externalID) {
		this.externalID = externalID;
	}

	private String externalID;

	// Getters
	public String getExternalID() {
		return externalID;
	}

	// Setters
	public void setExternalID(String externalID) {
		this.externalID = externalID;
	}

}
