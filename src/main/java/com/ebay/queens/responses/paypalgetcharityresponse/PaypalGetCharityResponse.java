package com.ebay.queens.responses.paypalgetcharityresponse;

public class PaypalGetCharityResponse {
	private GetCharityResult results[];
	private Links links[];
	
	public Links[] getLinks() {
		return links;
	}
	// Getters
	public GetCharityResult[] getResults() {
		return results;
	}
	
	public void setLinks(Links[] links) {
		this.links = links;
	}
	// Setters
	public void setResults(GetCharityResult[] results) {
		this.results = results;
	}


}
