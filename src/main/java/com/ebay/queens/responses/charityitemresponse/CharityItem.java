package com.ebay.queens.responses.charityitemresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"sellingStatus", "unitPrice","paymentMethod","autoPay","shippingInfo","itemFeature","gtcItem","cbtItem",
	"errorMessage", "adult","returnPolicyInfo","itemLocation","topRatedListing","viewItemURL","watchCount","productInfo","discountPriceInfo",
	"superGalleryImageInfo", "subtitle","smeInfo"})
public class CharityItem {
	private String itemId;
	private String title;
	private String listingSiteId;
	private String brand;
	private Category[] category;
	private ListingInfo listingInfo;
	private ItemImageInfo[] itemImageInfo;
	
	// Getters
	public String getItemId() {
		return itemId;
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
	public Category[] getCategory() {
		return category;
	}
	public ListingInfo getListingInfo() {
		return listingInfo;
	}
	
	public ItemImageInfo[] getItemImageInfo() {
		return itemImageInfo;
	}
	
	// Setters
	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	public void setCategory(Category[] category) {
		this.category = category;
	}
	public void setListingInfo(ListingInfo listingInfo) {
		this.listingInfo = listingInfo;
	}
	public void setItemImageInfo(ItemImageInfo[] itemImageInfo) {
		this.itemImageInfo = itemImageInfo;
	}

	


}
