package com.ebay.queens.responses.paypalgetcharityresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PaypalGetCharityResponse {
	private GetCharityResult results[];
	private Links links[];
	
	// Getters
	public GetCharityResult[] getResults() {
		return results;
	}
	public Links[] getLinks() {
		return links;
	}
	
	// Setters
	public void setResults(GetCharityResult[] results) {
		this.results = results;
	}
	public void setLinks(Links[] links) {
		this.links = links;
	}


}
