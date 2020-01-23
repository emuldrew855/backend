package com.ebay.queens.responses;

public class SearchItemResponse {
	private String href;
	private String total;
	private String next; 
	private ItemSummaries itemSummaries;

	public SearchItemResponse(String href, String total, String next, ItemSummaries itemSummaries) {
		this.href = href;
		this.total = total;
		this.next = next;
		this.itemSummaries = itemSummaries;
	}
	
	// Getters
	public String getHref() {
		return href;
	}

	public String getTotal() {
		return total;
	}

	public String getNext() {
		return next;
	}

	public ItemSummaries getItemSummaries() {
		return itemSummaries;
	}
	
	// Setters
	public void setHref(String href) {
		this.href = href;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public void setItemSummaries(ItemSummaries itemSummaries) {
		this.itemSummaries = itemSummaries;
	}
	
	
}
