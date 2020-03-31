package com.ebay.queens.responses.findnonprofitresponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "findNonprofitResponse", namespace = "http://www.ebay.com/marketplace/fundraising/v1/services")
public class FindNonProfitResponse {
	private String ack;
	private String version;
	private String timestamp;
	private NonProfit nonProfit;

	// Getters
	public String getAck() {
		return ack;
	}

	@XmlElement(name = "nonprofit")
	public NonProfit getNonProfit() {
		return nonProfit;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getVersion() {
		return version;
	}

	// Setters
	public void setAck(String ack) {
		this.ack = ack;
	}

	public void setNonProfit(NonProfit nonProfit) {
		this.nonProfit = nonProfit;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setVersion(String version) {
		this.version = version;
	}


}
