package com.ebay.queens.requests;

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
	}
