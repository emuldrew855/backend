package com.ebay.queens.requests.paypalcharitysearch;

import javax.xml.bind.annotation.XmlElement;

public class PaypalCharitySearchRequest {
	Charity charity;
	@XmlElement(name="Charity")
	public Charity getCharity() {
		return charity;
	}

	public void setCharity(Charity charity) {
		this.charity = charity;
	}

}
