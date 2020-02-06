package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="urn:ebay:apis:eBLBaseComponents")
public class GetItemResponse {
	private String timestamp;
	private String ack;
	private String version;
	private String build; 
	private Item item;
	
	// Getters
	public String getTimestamp() {
		return timestamp;
	}
	public String getAck() {
		return ack;
	}
	public String getVersion() {
		return version;
	}
	public String getBuild() {
		return build;
	}
	public Item getItem() {
		return item;
	}
	
	// Setters
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public void setAck(String ack) {
		this.ack = ack;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
}
