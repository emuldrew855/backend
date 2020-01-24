package com.ebay.queens.requests.charityitems;

import com.ebay.queens.requests.PaginationInput;

public class SearchRequest {
	private String sortOrder;
	private PaginationInput paginationInput;

	public SearchRequest(String sortOrder, PaginationInput paginationInput) {
		this.sortOrder = sortOrder;
		this.paginationInput = paginationInput;
	}

	// Getters
	public String getSortOrder() {
		return sortOrder;
	}

	public PaginationInput getPaginationInput() {
		return paginationInput;
	}
	
	// Setters
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setPaginationInput(PaginationInput paginationInput) {
		this.paginationInput = paginationInput;
	}

}
