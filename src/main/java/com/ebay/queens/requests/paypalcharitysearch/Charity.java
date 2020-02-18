package com.ebay.queens.requests.paypalcharitysearch;

import javax.xml.bind.annotation.XmlElement;

public class Charity {
	PaypalCharity charity;
	@XmlElement(name="Charity")
	public PaypalCharity getCharity() {
		return charity;
	}

	public void setPaypalCharity(PaypalCharity paypalCharity) {
		this.charity = paypalCharity;
	}

}
