package com.ebay.queens.responses.charityitemresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"convertedCurrentPrice","timeLeft","localizedTimeLeft","quantityAvailable",
						"quantitySold","maxQuantitySoldLimitExceeded","sellingState","bidCount"})
public class SellingStatus {
	private CurrentPrice currentPrice;

	public CurrentPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(CurrentPrice currentPrice) {
		this.currentPrice = currentPrice;
	} 

}
