package com.ebay.queens.requests.charityitems;

public class CharityItemRequest {
	public CharityItemRequest(SearchRequest searchRequest) {
		this.searchRequest = searchRequest;
	}

	private SearchRequest searchRequest;

	// Getters
	public SearchRequest getSearchRequest() {
		return searchRequest;
	}

	// Setters
	public void setSearchRequest(SearchRequest searchRequest) {
		this.searchRequest = searchRequest;
	}

}
