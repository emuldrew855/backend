package com.ebay.queens.responses.findnonprofitresponse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FindNonProfitResponse {
	private String ack;
	private String version;
	private String timestamp;
	private NonProfit nonProfit[];
	
	// Getters
	public String getAck() {
		return ack;
	}
	public String getVersion() {
		return version;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public NonProfit[] getNonProfit() {
		return nonProfit;
	}
	
	// Setters
	public void setAck(String ack) {
		this.ack = ack;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public void setNonProfit(NonProfit[] nonProfit) {
		this.nonProfit = nonProfit;
	}


}
