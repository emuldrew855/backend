package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

public class PictureDetails {
	private String pictureURL;
	private String galleryURL;
	
	// Getters
	@XmlElement(name = "PictureURL")
	public String getPictureURL() {
		return pictureURL;
	}
	@XmlElement(name = "GalleryURL")
	public String getGalleryURL() {
		return galleryURL;
	}
	
	// Setters
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public void setGalleryURL(String galleryURL) {
		this.galleryURL = galleryURL;
	}

}
