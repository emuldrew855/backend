package com.ebay.queens.responses.charityitemresponse;

public class CharityItemResponse {
	private CharityItem[] charityItems;

	public CharityItemResponse(CharityItem[] charityItems) {
		this.charityItems = charityItems;
	}
	// Getters
	public CharityItem[] getCharityItems() {
		return charityItems;
	}
	
	// Setters
	public void setCharityItems(CharityItem[] charityItems) {
		this.charityItems = charityItems;
	} 
	
	
}
