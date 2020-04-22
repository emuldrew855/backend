package com.ebay.queens.responses.charityitemresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"buyItNowPrice","buyItNowConvertedPrice"}) 
public class ListingInfo {
	private String buyItNowAvailable;
	private String bestOfferEnabled;
	private String listedTime;
	private String endTime;
	private String listingType;

	public String getBestOfferEnabled() {
		return bestOfferEnabled;
	}

	// Getters
	public String getBuyItNowAvailable() {
		return buyItNowAvailable;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getListedTime() {
		return listedTime;
	}

	public String getListingType() {
		return listingType;
	}

	public void setBestOfferEnabled(String bestOfferEnabled) {
		this.bestOfferEnabled = bestOfferEnabled;
	}

	// Setters
	public void setBuyItNowAvailable(String buyItNowAvailable) {
		this.buyItNowAvailable = buyItNowAvailable;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setListedTime(String listedTime) {
		this.listedTime = listedTime;
	}

	public void setListingType(String listingType) {
		this.listingType = listingType;
	}

}
