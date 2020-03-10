package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("Description")
public class Item {
	private String autoPay;
	private String buyerProtection;
	private String buyItNowPrice;
	private Charity charity;
	private String country;
	private String startPrice; 
	private String conditionDescription;
	private String title;
	private PrimaryCategory primarycategory;
	private String startprice;
	private PictureDetails pictureDetails;
	private ListingDetails listingDetails;
	private SellingStatus sellingStatus; 
	private String quantity; 
	private ProductListingDetails productListingDetails; 
	
	// Getters
	@XmlElement(name = "AutoPay")
	public String getAutoPay() {
		return autoPay;
	}

	@XmlElement(name = "BuyerProtection")
	public String getBuyerProtection() {
		return buyerProtection;
	}

	@XmlElement(name = "BuyItNowPrice")
	public String getBuyItNowPrice() {
		return buyItNowPrice;
	}

	@XmlElement(name = "Charity")
	public Charity getCharity() {
		return charity;
	}

	@XmlElement(name = "Country")
	public String getCountry() {
		return country;
	}
	
	@XmlElement(name = "StartPrice")
	public String getStartPrice() {
		return startPrice;
	}
	
	@XmlElement(name = "ConditionDescription")
	public String getConditionDescription() {
		return conditionDescription;
	}
	@XmlElement(name = "Title")
	public String getTitle() {
		return title;
	}
	
	@XmlElement(name = "PrimaryCategory")
	public PrimaryCategory getPrimarycategory() {
		return primarycategory;
	}
	@XmlElement(name = "StartPrice")
	public String getStartprice() {
		return startprice;
	}
	@XmlElement(name = "PictureDetails")
	public PictureDetails getPictureDetails() {
		return pictureDetails;
	}
	
	@XmlElement(name ="ListingDetails")
	public ListingDetails getListingDetails() {
		return listingDetails;
	}
	
	@XmlElement(name ="SellingStatus")
	public SellingStatus getSellingStatus() {
		return sellingStatus;
	}
	
	@XmlElement(name ="ProductListingDetails")
	public ProductListingDetails getProductListingDetails() {
		return productListingDetails;
	}
	
	@XmlElement(name ="Quantity")
	public String getQuantity() {
		return quantity;
	}

	// Setters
	public void setAutoPay(String autoPay) {
		this.autoPay = autoPay;
	}

	public void setBuyerProtection(String buyerProtection) {
		this.buyerProtection = buyerProtection;
	}

	public void setBuyItNowPrice(String buyItNowPrice) {
		this.buyItNowPrice = buyItNowPrice;
	}

	public void setCharity(Charity charity) {
		this.charity = charity;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}

	public void setConditionDescription(String conditionDescription) {
		this.conditionDescription = conditionDescription;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrimarycategory(PrimaryCategory primarycategory) {
		this.primarycategory = primarycategory;
	}

	public void setStartprice(String startprice) {
		this.startprice = startprice;
	}

	public void setPictureDetails(PictureDetails pictureDetails) {
		this.pictureDetails = pictureDetails;
	}

	public void setListingDetails(ListingDetails listingDetails) {
		this.listingDetails = listingDetails;
	}

	public void setSellingStatus(SellingStatus sellingStatus) {
		this.sellingStatus = sellingStatus;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setProductListingDetails(ProductListingDetails productListingDetails) {
		this.productListingDetails = productListingDetails;
	}


}
