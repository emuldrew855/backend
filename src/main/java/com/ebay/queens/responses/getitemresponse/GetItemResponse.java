package com.ebay.queens.responses.getitemresponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetItemResponse", namespace = "urn:ebay:apis:eBLBaseComponents")
public class GetItemResponse {
	private String timestamp;
	private String ack;
	private String version;
	private String build;
	private Item item;

	// Getters
	@XmlElement(name = "Timestamp")
	public String getTimestamp() {
		return timestamp;
	}

	@XmlElement(name = "Ack")
	public String getAck() {
		return ack;
	}

	@XmlElement(name = "Version")
	public String getVersion() {
		return version;
	}

	@XmlElement(name = "Build")
	public String getBuild() {
		return build;
	}

	@XmlElement(name = "Item")
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
