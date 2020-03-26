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

	// Getters
	public String getItemId() {
		return itemId;
	}

	public String getTitle() {
		return title;
	}

	public Image getImage() {
		return image;
	}

	public Price getPrice() {
		return price;
	}

	public String getItemHref() {
		return itemHref;
	}

	public Seller getSeller() {
		return seller;
	}

	public String getCondition() {
		return condition;
	}

	public String getConditionId() {
		return conditionId;
	}

	public ThumbnailImages[] getThumbnailImages() {
		return thumbnailImages;
	}

	public ShippingOptions[] getShippingOptions() {
		return shippingOptions;
	}

	public String[] getBuyingOptions() {
		return buyingOptions;
	}

	public String getEpid() {
		return epid;
	}

	public String getItemWebUrl() {
		return itemWebUrl;
	}

	public ItemLocation getItemLocation() {
		return itemLocation;
	}

	public Categories getCategories() {
		return categories;
	}

	public AdditionalImages[] getAdditionalImages() {
		return additionalImages;
	}

	// Setters
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public void setItemHref(String itemHref) {
		this.itemHref = itemHref;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public void setThumbnailImages(ThumbnailImages[] thumbnailImages) {
		this.thumbnailImages = thumbnailImages;
	}

	public void setShippingOptions(ShippingOptions[] shippingOptions) {
		this.shippingOptions = shippingOptions;
	}

	public void setBuyingOptions(String[] buyingOptions) {
		this.buyingOptions = buyingOptions;
	}

	public void setEpid(String epid) {
		this.epid = epid;
	}

	public void setItemWebUrl(String itemWebUrl) {
		this.itemWebUrl = itemWebUrl;
	}

	public void setItemLocation(ItemLocation itemLocation) {
		this.itemLocation = itemLocation;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public void setAdditionalImages(AdditionalImages[] additionalImages) {
		this.additionalImages = additionalImages;
	}

}
