package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;

public class ProductListingDetails {
	private BrandMPN brandMPN;
	
	@XmlElement(name ="BrandMPN")
	public BrandMPN getBrandMPN() {
		return brandMPN;
	}

	public void setBrandMPN(BrandMPN brandMPN) {
		this.brandMPN = brandMPN;
	} 
	
}
