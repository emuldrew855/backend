package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

public class PrimaryCategory {
	private String categoryid;
	private String categoryname;
	
	// Getters
	@XmlElement(name = "CategoryID")
	public String getCategoryid() {
		return categoryid;
	}
	@XmlElement(name = "CategoryName")
	public String getCategoryname() {
		return categoryname;
	}
	
	// Setters
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	

}
