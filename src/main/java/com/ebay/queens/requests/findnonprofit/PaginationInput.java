package com.ebay.queens.requests.findnonprofit;

public class PaginationInput {
	public PaginationInput(String pageSize, String pageNumber) {
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}
	private String pageSize;

	private String pageNumber;

	public String getPageNumber() {
		return pageNumber;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

}
