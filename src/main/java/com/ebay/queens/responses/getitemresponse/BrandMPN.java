package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

public class BrandMPN {
	private String brand;
	private String MPN;
	
	// Getters 
	@XmlElement(name = "Brand")
	public String getBrand() {
		return brand;
	}
	@XmlElement(name = "MPN")
	public String getMPN() {
		return MPN;
	}
	
	// Setters
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setMPN(String mPN) {
		MPN = mPN;
	}
	
}
