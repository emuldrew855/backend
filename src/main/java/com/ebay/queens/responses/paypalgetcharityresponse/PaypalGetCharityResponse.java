package com.ebay.queens.responses.paypalgetcharityresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "links" })
public class PaypalGetCharityResponse {
	GetCharityResult results[];

	public GetCharityResult[] getResults() {
		return results;
	}

	public void setResults(GetCharityResult[] results) {
		this.results = results;
	}

}
