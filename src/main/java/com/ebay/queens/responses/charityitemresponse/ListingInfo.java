package com.ebay.queens.responses.charityitemresponse;

public class ListingInfo {
	private String buyItNowAvailable;
	private String bestOfferEnabled;
	private String listedTime;
	private String endTime;
	private String listingType;

	public ListingInfo(String buyItNowAvailable, String bestOfferEnabled, String listedTime, String endTime,
			String listingType) {
		this.buyItNowAvailable = buyItNowAvailable;
		this.bestOfferEnabled = bestOfferEnabled;
		this.listedTime = listedTime;
		this.endTime = endTime;
		this.listingType = listingType;
	}

	// Getters
	public String getBuyItNowAvailable() {
		return buyItNowAvailable;
	}

	public String getBestOfferEnabled() {
		return bestOfferEnabled;
	}

	public String getListedTime() {
		return listedTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getListingType() {
		return listingType;
	}

	// Setters
	public void setBuyItNowAvailable(String buyItNowAvailable) {
		this.buyItNowAvailable = buyItNowAvailable;
	}

	public void setBestOfferEnabled(String bestOfferEnabled) {
		this.bestOfferEnabled = bestOfferEnabled;
	}

	public void setListedTime(String listedTime) {
		this.listedTime = listedTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setListingType(String listingType) {
		this.listingType = listingType;
	}

}
