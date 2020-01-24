package com.ebay.queens.responses;

public class PaypalTokenResponse {
	private String scope;
	private String accessToken;
	private String tokenType;
	private String appID;
	private String expiresIn;
	private String nonce;
	
	// Getters
	public String getScope() {
		return scope;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public String getAppID() {
		return appID;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public String getNonce() {
		return nonce;
	}
	
	// Setters
	public void setScope(String scope) {
		this.scope = scope;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	} 
}
