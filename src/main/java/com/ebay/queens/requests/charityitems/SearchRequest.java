package com.ebay.queens.requests.charityitems;

import com.ebay.queens.requests.findnonprofit.PaginationInput;

public class SearchRequest {
	public SearchRequest(String sortOrder, PaginationInput paginationInput, Constraints constraints) {
		this.sortOrder = sortOrder;
		this.paginationInput = paginationInput;
		this.constraints = constraints;
	}
	private String sortOrder;
	private PaginationInput paginationInput;
	private Constraints constraints;
	private String keyword; 

	// Getters
	public String getSortOrder() {
		return sortOrder;
	}
	
	public Constraints getConstraints() {
		return constraints;
	}

	public PaginationInput getPaginationInput() {
		return paginationInput;
	}
	
	public String getKeyword() {
		return keyword;
	}



	// Setters
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public void setConstraints(Constraints constraints) {
		this.constraints = constraints;
	}

	public void setPaginationInput(PaginationInput paginationInput) {
		this.paginationInput = paginationInput;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
