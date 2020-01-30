package com.ebay.queens.requests.findnonprofit;

import javax.xml.bind.annotation.XmlRootElement;

import com.ebay.queens.requests.findnonprofit.PaginationInput;

@XmlRootElement(name="findNonprofitRequest")
public class FindNonProfitRequest {
	private SearchFilter searchFilter;
	private PaginationInput paginationInput;

	public FindNonProfitRequest(SearchFilter searchFilter, PaginationInput paginationInput) {
		this.searchFilter = searchFilter;
		this.paginationInput = paginationInput;
	}
	
	public FindNonProfitRequest() {
		
	}

	// Getters
	public SearchFilter getSearchFilter() {
		return searchFilter;
	}

	public PaginationInput getPaginationInput() {
		return paginationInput;
	}

	// Setters
	public void setSearchFilter(SearchFilter searchFilter) {
		this.searchFilter = searchFilter;
	}

	public void setPaginationInput(PaginationInput paginationInput) {
		this.paginationInput = paginationInput;
	}

}
