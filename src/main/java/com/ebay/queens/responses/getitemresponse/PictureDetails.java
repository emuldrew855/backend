package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

public class PictureDetails {
	private String pictureURL;
	
	// Getters
	@XmlElement(name = "PictureURL")
	public String getPictureURL() {
		return pictureURL;
	}
	
	// Setters
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

}
