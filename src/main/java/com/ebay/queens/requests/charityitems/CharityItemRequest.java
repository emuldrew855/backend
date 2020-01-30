package com.ebay.queens.requests.charityitems;

public class CharityItemRequest {
	private SearchRequest searchRequest;

	public CharityItemRequest(SearchRequest searchRequest) {
		this.searchRequest = searchRequest;
	}

	// Getters
	public SearchRequest getSearchRequest() {
		return searchRequest;
	}

	// Setters
	public void setSearchRequest(SearchRequest searchRequest) {
		this.searchRequest = searchRequest;
	}

}
