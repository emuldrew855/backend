package com.ebay.queens.requests.findnonprofit;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "findNonprofitRequest")
public class FindNonProfitRequest {
	public FindNonProfitRequest() {

	}
	public FindNonProfitRequest(SearchFilter searchFilter, PaginationInput paginationInput) {
		this.searchFilter = searchFilter;
		this.paginationInput = paginationInput;
	}

	private SearchFilter searchFilter;

	private PaginationInput paginationInput;

	public PaginationInput getPaginationInput() {
		return paginationInput;
	}

	// Getters
	public SearchFilter getSearchFilter() {
		return searchFilter;
	}

	public void setPaginationInput(PaginationInput paginationInput) {
		this.paginationInput = paginationInput;
	}

	// Setters
	public void setSearchFilter(SearchFilter searchFilter) {
		this.searchFilter = searchFilter;
	}

}
