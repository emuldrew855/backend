package com.ebay.queens.requests.getitem;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetItemRequest")
public class GetItemRequest {
	private RequesterCredentials requesterCredentials;
	private String ItemID;
	private String DetailLevel;
	
	public GetItemRequest(RequesterCredentials requesterCredentials, String ItemID, String DetailLevel) {
		super();
		this.requesterCredentials = requesterCredentials;
		this.ItemID = ItemID;
		this.DetailLevel = DetailLevel;
	}
	
	public GetItemRequest() {
		
	}
	
	// Getters
	public RequesterCredentials getRequesterCredentials() {
		return requesterCredentials;
	}
	@XmlElement(name="ItemID")
	public String getItemID() {
		return ItemID;
	}
	@XmlElement(name="DetailLevel")
	public String getDetailLevel() {
		return DetailLevel;
	}

	// Setters
	public void setRequesterCredentials(RequesterCredentials requesterCredentials) {
		this.requesterCredentials = requesterCredentials;
	}
	public void setItemID(String ItemID) {
		this.ItemID = ItemID;
	}
	public void setDetailLevel(String DetailLevel) {
		this.DetailLevel = DetailLevel;
	}


}
