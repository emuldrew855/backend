package com.ebay.queens.requests;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetItemRequest")
public class GetItemRequest {
	    private RequestCredentials requestCredentials;
	    private String itemID;
	    private String detailLevel;
	    public GetItemRequest() {
	    }
	    public GetItemRequest(RequestCredentials requestCredentials, String itemID, String detailLevel) {
	        this.requestCredentials = requestCredentials;
	        this.itemID = itemID;
	        this.detailLevel = detailLevel;
	    }
	
	    @Override
	    public String toString() {
	        final StringBuffer sb = new StringBuffer("<GetItemRequest xmlns=\\\"urn:ebay:apis:eBLBaseComponents\\\"");
	        sb.append(requestCredentials.toString());
	        sb.append(", name='").append(itemID).append('\'');
	        sb.append(", email='").append(detailLevel).append('\'');
	        sb.append('}');
	        return sb.toString();
	    }
	    
	    // Getters
		public RequestCredentials getRequestCredentials() {
			return requestCredentials;
		}
		public String getItemID() {
			return itemID;
		}
		public String getDetailLevel() {
			return detailLevel;
		}
		
		// Setters 
		public void setRequestCredentials(RequestCredentials requestCredentials) {
			this.requestCredentials = requestCredentials;
		}
		public void setItemID(String itemID) {
			this.itemID = itemID;
		}
		public void setDetailLevel(String detailLevel) {
			this.detailLevel = detailLevel;
		}
	}
