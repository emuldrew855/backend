package com.ebay.queens.responses.searchitemresponse;

public class ItemSummaries {
	private String condition;
	private String conditionId;
	private String itemId;
	private String title;
	private String itemHref; 
	private Image image;
	private Price price; 
	private Seller seller;
	
	// Getters
	public String getCondition() {
		return condition;
	}

	public String getConditionId() {
		return conditionId;
	}
	
	public String getTitle() {
		return title;
	}

	public String getItemId() {
		return itemId;
	}

	public String getItemHref() {
		return itemHref;
	}

	public Image getImage() {
		return image;
	}

	public Price getPrice() {
		return price;
	}

	public Seller getSeller() {
		return seller;
	}
	
	// Setters
	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemHref(String itemHref) {
		this.itemHref = itemHref;
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

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
}
