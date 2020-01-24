package com.ebay.queens.requests;

public class PaginationInput {
	private String pageNumber;
	private String pageSize;
	private String entriesPerPage;
	private String totalEntries;
	private String totalPages;
	
	public PaginationInput(String pageNumber, String pageSize, String entriesPerPage, String totalEntries,
			String totalPages) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.entriesPerPage = entriesPerPage;
		this.totalEntries = totalEntries;
		this.totalPages = totalPages;
	}
	
	// Getters
	public String getPageNumber() {
		return pageNumber;
	}
	public String getPageSize() {
		return pageSize;
	}
	public String getEntriesPerPage() {
		return entriesPerPage;
	}
	public String getTotalEntries() {
		return totalEntries;
	}
	public String getTotalPages() {
		return totalPages;
	}
	
	// Setters
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public void setEntriesPerPage(String entriesPerPage) {
		this.entriesPerPage = entriesPerPage;
	}
	public void setTotalEntries(String totalEntries) {
		this.totalEntries = totalEntries;
	}
	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
