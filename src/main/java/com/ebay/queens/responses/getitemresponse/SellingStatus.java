package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

public class SellingStatus {
	private String bidCount; 
	private String currentPrice; 
	private String mimimumToBid; 
	private String quantitySold; 
	private PromotionalSaleDetails promotionalSaleDetails;
	
	// Getters
	@XmlElement(name = "BidCount")
	public String getBidCount() {
		return bidCount;
	}
	@XmlElement(name = "CurrentPrice")
	public String getCurrentPrice() {
		return currentPrice;
	}
	@XmlElement(name = "MinimumToBid")
	public String getMimimumToBid() {
		return mimimumToBid;
	}
	@XmlElement(name = "PromotionalSaleDetails")
	public PromotionalSaleDetails getPromotionalSaleDetails() {
		return promotionalSaleDetails;
	}
	@XmlElement(name = "QuantitySold")
	public String getQuantitySold() {
		return quantitySold;
	}
	
	// Setters
	public void setBidCount(String bidCount) {
		this.bidCount = bidCount;
	}
	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
	public void setMimimumToBid(String mimimumToBid) {
		this.mimimumToBid = mimimumToBid;
	}
	public void setPromotionalSaleDetails(PromotionalSaleDetails promotionalSaleDetails) {
		this.promotionalSaleDetails = promotionalSaleDetails;
	}
	public void setQuantitySold(String quantitySold) {
		this.quantitySold = quantitySold;
	} 
	
	

}
