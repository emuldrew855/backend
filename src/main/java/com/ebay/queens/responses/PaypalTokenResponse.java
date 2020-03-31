package com.ebay.queens.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "name"})
public class PaypalTokenResponse {
	private String scope;
	private String access_token;
	private String token_type;
	private String app_id;
	private String expires_in;
	private String nonce;

	public String getacccess_token() {
		return access_token;
	}

	public String getapp_id() {
		return app_id;
	}

	public String getexpires_in() {
		return expires_in;
	}

	public String getNonce() {
		return nonce;
	}

	// Getters
	public String getScope() {
		return scope;
	}

	public String gettoken_type() {
		return token_type;
	}

	public void setaccess_token(String access_token) {
		this.access_token = access_token;
	}

	public void setapp_id(String appID) {
		this.app_id = appID;
	}

	public void setexpires_in(String expiresIn) {
		this.expires_in = expiresIn;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	// Setters
	public void setScope(String scope) {
		this.scope = scope;
	}

	public void settoken_type(String tokenType) {
		this.token_type = tokenType;
	}

}
