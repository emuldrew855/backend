package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

public class PromotionalSaleDetails {
	private String originalPrice;
	private String startTime;
	private String endTime;
	
	// Getters
	@XmlElement(name = "OriginalPrice")
	public String getOriginalPrice() {
		return originalPrice;
	}
	@XmlElement(name = "StartTime")
	public String getStartTime() {
		return startTime;
	}
	@XmlElement(name = "EndTime")
	public String getEndTime() {
		return endTime;
	}
	
	// Setters
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	} 

}
