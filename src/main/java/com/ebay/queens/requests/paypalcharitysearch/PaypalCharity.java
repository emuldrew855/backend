package com.ebay.queens.requests.paypalcharitysearch;

import javax.xml.bind.annotation.XmlElement;

public class PaypalCharity {
	String mission_area;
	@XmlElement(name ="mission_area")
	public String getmission_area() {
		return mission_area;
	}

	public void setMissionArea(String mission_area) {
		this.mission_area = mission_area;
	}

}
