package com.ebay.queens.requests.getitem;

public class RequesterCredentials {
	public RequesterCredentials() {
	}

	public RequesterCredentials(String eBayAuthToken) {
		this.eBayAuthToken = eBayAuthToken;
	}

	private String eBayAuthToken;

	public String geteBayAuthToken() {
		return eBayAuthToken;
	}

	public void seteBayAuthToken(String eBayAuthToken) {
		this.eBayAuthToken = eBayAuthToken;
	}

}
