package com.ebay.queens.requests.findnonprofit;

public class PaginationInput {
	private String pageSize;
	private String pageNumber;

	public PaginationInput(String pageSize, String pageNumber) {
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}

	public String getPageSize() {
		return pageSize;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

}
