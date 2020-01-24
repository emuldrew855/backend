package com.ebay.queens.responses.charityitemresponse;

public class CharityItem {
	private String itemID;
	private String title;
	private String listingSiteId;
	private String brand;
	private Category[] categories;
	private ListingInfo listingInfo;

	public CharityItem(String itemID, String title, String listingSiteId, String brand, Category[] categories,
			ListingInfo listingInfo) {
		this.itemID = itemID;
		this.title = title;
		this.listingSiteId = listingSiteId;
		this.brand = brand;
		this.categories = categories;
		this.listingInfo = listingInfo;
	}

	// Getters
	public String getItemID() {
		return itemID;
	}

	public String getTitle() {
		return title;
	}

	public String getListingSiteId() {
		return listingSiteId;
	}

	public String getBrand() {
		return brand;
	}

	public Category[] getCategories() {
		return categories;
	}

	public ListingInfo getListingInfo() {
		return listingInfo;
	}

	// Setters
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setListingSiteId(String listingSiteId) {
		this.listingSiteId = listingSiteId;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setCategories(Category[] categories) {
		this.categories = categories;
	}

	public void setListingInfo(ListingInfo listingInfo) {
		this.listingInfo = listingInfo;
	}

}
