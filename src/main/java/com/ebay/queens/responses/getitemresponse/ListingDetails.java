package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

public class ListingDetails {
	private String viewItemUrl;
	
	@XmlElement(name ="ViewItemURL")
	public String getViewItemUrl() {
		return viewItemUrl;
	}

	public void setViewItemUrl(String viewItemUrl) {
		this.viewItemUrl = viewItemUrl;
	} 

}
