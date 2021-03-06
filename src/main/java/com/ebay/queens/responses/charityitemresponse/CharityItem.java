package com.ebay.queens.responses.charityitemresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"unitPrice","paymentMethod","autoPay","shippingInfo","itemFeature","gtcItem","cbtItem",
	"errorMessage", "adult","returnPolicyInfo","itemLocation","topRatedListing","viewItemURL","watchCount","productInfo","discountPriceInfo",
	"superGalleryImageInfo", "subtitle","smeInfo","fitmentInfo","multiVariationListingInfo"})
public class CharityItem {
	private String itemId;
	private String title;
	private String listingSiteId;
	private String brand;
	private Category[] category;
	private ListingInfo listingInfo;
	private ItemImageInfo[] itemImageInfo;
	private SellingStatus sellingStatus;
	
	public String getBrand() {
		return brand;
	}
	public Category[] getCategory() {
		return category;
	}
	// Getters
	public String getItemId() {
		return itemId;
	}
	public ItemImageInfo[] getItemImageInfo() {
		return itemImageInfo;
	}
	public ListingInfo getListingInfo() {
		return listingInfo;
	}
	public String getListingSiteId() {
		return listingSiteId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setCategory(Category[] category) {
		this.category = category;
	}
	// Setters
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public void setItemImageInfo(ItemImageInfo[] itemImageInfo) {
		this.itemImageInfo = itemImageInfo;
	}
	public void setListingInfo(ListingInfo listingInfo) {
		this.listingInfo = listingInfo;
	}
	public void setListingSiteId(String listingSiteId) {
		this.listingSiteId = listingSiteId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public SellingStatus getSellingStatus() {
		return sellingStatus;
	}
	public void setSellingStatus(SellingStatus sellingStatus) {
		this.sellingStatus = sellingStatus;
	}

	


}
