package com.ebay.queens.requests.getitem;

public class RequestCredentials {
		    private String eBayAuthToken;
		    public RequestCredentials() {
		    }
		    public RequestCredentials(String eBayAuthToken) {
		        this.eBayAuthToken = eBayAuthToken;
		    }
			public String geteBayAuthToken() {
				return eBayAuthToken;
			}
			public void seteBayAuthToken(String eBayAuthToken) {
				this.eBayAuthToken = eBayAuthToken;
			}
		
		}
