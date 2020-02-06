package com.ebay.queens.responses.searchitemresponse;


public class SearchItemResponse {
	private String href;
	private String total;
	private String next; 
	private String limit;
	private String offset;
	private ItemSummaries itemSummaries[];

	// Getters
	public ItemSummaries[] getItemSummaries() {
		return itemSummaries;
	}

	public String getOffset() {
		return offset;
	}
	
	public String getLimit() {
		return limit;
	}
	
	public String getHref() {
		return href;
	}

	public String getTotal() {
		return total;
	}

	public String getNext() {
		return next;
	}
	
	// Setters
	public void setOffset(String offset) {
		this.offset = offset;
	}
	
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	public void setHref(String href) {
		this.href = href;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public void setItemSummaries(ItemSummaries[] itemSummaries) {
		this.itemSummaries = itemSummaries;
	}

	
	
}
