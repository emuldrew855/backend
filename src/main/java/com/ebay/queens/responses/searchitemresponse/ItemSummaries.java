package com.ebay.queens.responses.searchitemresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "itemAffiliateWebUrl", "categories", "adultOnly", "marketingPrice", "priceDisplayCondition",
		"itemGroupHref", "itemGroupType" ,"bidCount", "currentBidPrice","pickupOptions", "legacyItemId"})
public class ItemSummaries {
	private String itemId;
	private String title;
	private Image image;
	private Price price;
	private String itemHref;
	private Seller seller;
	private String condition;
	private String conditionId;
	private ThumbnailImages thumbnailImages[];
	private ShippingOptions shippingOptions[];
	private String buyingOptions[];
	private String epid;
	private String itemWebUrl;
	private ItemLocation itemLocation;
	private Categories categories;
	private AdditionalImages additionalImages[];

	public AdditionalImages[] getAdditionalImages() {
		return additionalImages;
	}

	public String[] getBuyingOptions() {
		return buyingOptions;
	}

	public Categories getCategories() {
		return categories;
	}

	public String getCondition() {
		return condition;
	}

	public String getConditionId() {
		return conditionId;
	}

	public String getEpid() {
		return epid;
	}

	public Image getImage() {
		return image;
	}

	public String getItemHref() {
		return itemHref;
	}

	// Getters
	public String getItemId() {
		return itemId;
	}

	public ItemLocation getItemLocation() {
		return itemLocation;
	}

	public String getItemWebUrl() {
		return itemWebUrl;
	}

	public Price getPrice() {
		return price;
	}

	public Seller getSeller() {
		return seller;
	}

	public ShippingOptions[] getShippingOptions() {
		return shippingOptions;
	}

	public ThumbnailImages[] getThumbnailImages() {
		return thumbnailImages;
	}

	public String getTitle() {
		return title;
	}

	public void setAdditionalImages(AdditionalImages[] additionalImages) {
		this.additionalImages = additionalImages;
	}

	public void setBuyingOptions(String[] buyingOptions) {
		this.buyingOptions = buyingOptions;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public void setEpid(String epid) {
		this.epid = epid;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setItemHref(String itemHref) {
		this.itemHref = itemHref;
	}

	// Setters
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemLocation(ItemLocation itemLocation) {
		this.itemLocation = itemLocation;
	}

	public void setItemWebUrl(String itemWebUrl) {
		this.itemWebUrl = itemWebUrl;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public void setShippingOptions(ShippingOptions[] shippingOptions) {
		this.shippingOptions = shippingOptions;
	}

	public void setThumbnailImages(ThumbnailImages[] thumbnailImages) {
		this.thumbnailImages = thumbnailImages;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
