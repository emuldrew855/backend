package com.ebay.queens.responses.searchitemresponse;

public class ItemSummaries {
	private String itemID;
	private String title;
	private Image image;
	private Price price; 
	private Seller seller;
	
	public ItemSummaries(String itemID, String title, Image image, Price price, Seller seller) {
		this.itemID = itemID;
		this.title = title;
		this.image = image;
		this.price = price;
		this.seller = seller;
	}
	
	// Getters
	public String getItemID() {
		return itemID;
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

	public Seller getSeller() {
		return seller;
	}
	
	// Setters
	public void setItemID(String itemID) {
		this.itemID = itemID;
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
