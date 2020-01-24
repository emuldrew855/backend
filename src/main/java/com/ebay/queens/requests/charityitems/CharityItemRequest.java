package com.ebay.queens.requests.charityitems;

public class CharityItemRequest {
	private SearchRequest searchRequest;
	private String keyword;
	private Constraints constraints;
	private GlobalAspect globalAspect;

	public CharityItemRequest(SearchRequest searchRequest, String keyword, Constraints constraints,
			GlobalAspect globalAspect) {
		this.searchRequest = searchRequest;
		this.keyword = keyword;
		this.constraints = constraints;
		this.globalAspect = globalAspect;
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

	public GlobalAspect getGlobalAspect() {
		return globalAspect;
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

	public void setGlobalAspect(GlobalAspect globalAspect) {
		this.globalAspect = globalAspect;
	}

}
