package com.ebay.queens.requests.getitem;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetItemRequest")
public class GetItemRequest {
	private RequesterCredentials requesterCredentials;
	private String itemID;
	private String DetailLevel;

	public GetItemRequest() {
	}

	public GetItemRequest(RequesterCredentials requesterCredentials, String itemID, String detailLevel) {
		this.requesterCredentials = requesterCredentials;
		this.itemID = itemID;
		this.DetailLevel = detailLevel;
	}

	// Getters
	public RequesterCredentials getRequesterCredentials() {
		return requesterCredentials;
	}

	public String getItemID() {
		return itemID;
	}

	public String getDetailLevel() {
		return DetailLevel;
	}

	// Setters

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public void setRequesterCredentials(RequesterCredentials requesterCredentials) {
		this.requesterCredentials = requesterCredentials;
	}

	public void setDetailLevel(String detailLevel) {
		this.DetailLevel = detailLevel;
	}
}
