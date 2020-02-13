package com.ebay.queens.requests.charityitems;

import com.ebay.queens.requests.findnonprofit.PaginationInput;

public class SearchRequest {
	private String sortOrder;
	private PaginationInput paginationInput;
	private Constraints constraints;

	public SearchRequest(String sortOrder, PaginationInput paginationInput, Constraints constraints) {
		this.sortOrder = sortOrder;
		this.paginationInput = paginationInput;
		this.constraints = constraints;
	}

	// Getters
	public String getSortOrder() {
		return sortOrder;
	}

	public PaginationInput getPaginationInput() {
		return paginationInput;
	}

	public Constraints getConstraints() {
		return constraints;
	}

	// Setters
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setPaginationInput(PaginationInput paginationInput) {
		this.paginationInput = paginationInput;
	}

	public void setConstraints(Constraints constraints) {
		this.constraints = constraints;
	}

}
