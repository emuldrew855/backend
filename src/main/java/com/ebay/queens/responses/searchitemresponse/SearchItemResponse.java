package com.ebay.queens.responses.searchitemresponse;

public class SearchItemResponse {
	private String href;
	private String total;
	private String next;
	private String prev;
	private String limit;
	private String offset;
	private ItemSummaries itemSummaries[];

	public String getHref() {
		return href;
	}

	// Getters
	public ItemSummaries[] getItemSummaries() {
		return itemSummaries;
	}

	public String getLimit() {
		return limit;
	}

	public String getNext() {
		return next;
	}

	public String getOffset() {
		return offset;
	}

	public String getPrev() {
		return prev;
	}
	
	public String getTotal() {
		return total;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setItemSummaries(ItemSummaries[] itemSummaries) {
		this.itemSummaries = itemSummaries;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public void setNext(String next) {
		this.next = next;
	}

	// Setters
	public void setOffset(String offset) {
		this.offset = offset;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
