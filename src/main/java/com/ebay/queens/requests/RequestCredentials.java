package com.ebay.queens.requests;

public class RequestCredentials {
		    private String eBayAuthToken;
		    public RequestCredentials() {
		    }
		    public RequestCredentials(String eBayAuthToken) {
		        this.eBayAuthToken = eBayAuthToken;
		    }
		
		    @Override
		    public String toString() {
		        final StringBuffer sb = new StringBuffer("<requesterCredentials>");
		        sb.append("<eBayAuthToken>").append(eBayAuthToken);
		        sb.append("</eBayAuthToken>");
		        sb.append("</requesterCredentials>");
		        return sb.toString();
		    }
		}
