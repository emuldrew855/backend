package com.ebay.queens.requests.charityitems;

public class CharityItemRequest {
	private SearchRequest searchRequest;
	private String keyword;
	private Constraints constraints;
	private GlobalAspect globalAspect;

	public CharityItemRequest(SearchRequest searchRequest, String keyword, Constraints constraints) {
		this.searchRequest = searchRequest;
		this.keyword = keyword;
		this.constraints = constraints;
	}

	// Getters
	public SearchRequest getSearchRequest() {
		return searchRequest;
	}

	public String getKeyword() {
		return keyword;
	}

	public Constraints getConstraints() {
		return constraints;
	}

	// Setters
	public void setSearchRequest(SearchRequest searchRequest) {
		this.searchRequest = searchRequest;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setConstraints(Constraints constraints) {
		this.constraints = constraints;
	}

}
