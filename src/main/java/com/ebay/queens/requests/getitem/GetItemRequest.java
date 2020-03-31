package com.ebay.queens.requests.getitem;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetItemRequest")
public class GetItemRequest {
	public GetItemRequest() {
		
	}
	public GetItemRequest(RequesterCredentials requesterCredentials, String ItemID, String DetailLevel) {
		super();
		this.requesterCredentials = requesterCredentials;
		this.ItemID = ItemID;
		this.DetailLevel = DetailLevel;
	}
	private RequesterCredentials requesterCredentials;
	
	private String ItemID;
	
	private String DetailLevel;
	
	@XmlElement(name="DetailLevel")
	public String getDetailLevel() {
		return DetailLevel;
	}
	@XmlElement(name="ItemID")
	public String getItemID() {
		return ItemID;
	}
	// Getters
	public RequesterCredentials getRequesterCredentials() {
		return requesterCredentials;
	}

	public void setDetailLevel(String DetailLevel) {
		this.DetailLevel = DetailLevel;
	}
	public void setItemID(String ItemID) {
		this.ItemID = ItemID;
	}
	// Setters
	public void setRequesterCredentials(RequesterCredentials requesterCredentials) {
		this.requesterCredentials = requesterCredentials;
	}


}
