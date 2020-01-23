package com.ebay.queens.requests.findnonprofit;

public class PaginationInput {
	private String pageNumber;
	private String pageSize;
	
	public PaginationInput(String pageNumber, String pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	// Getters
	public String getPageNumber() {
		return pageNumber;
	}
	public String getPageSize() {
		return pageSize;
	}
	
	// Setters
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
}
