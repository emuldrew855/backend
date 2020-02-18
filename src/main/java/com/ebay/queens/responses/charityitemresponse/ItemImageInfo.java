package com.ebay.queens.responses.charityitemresponse;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"galleryImageURL", "secondaryImageURL", "extended"})
public class ItemImageInfo {
	private String primaryImageURL;
	
	@XmlElement(name="primaryImageURL")
	public String getPrimaryImageURL() {
		return primaryImageURL;
	}

	public void setPrimaryImageURL(String primaryImageURL) {
		this.primaryImageURL = primaryImageURL;
	} 
}
