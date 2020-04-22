package com.ebay.queens.responses.paypalcharitysearchresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "charity", "links" })
public class PaypalCharitySearchResponse {
	private String query_id;
	private String page_size;
	private Links links;

	public Links getLinks() {
		return links;
	}

	public String getPage_size() {
		return page_size;
	}

	public String getQuery_id() {
		return query_id;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}

	public void setQuery_id(String query_id) {
		this.query_id = query_id;
	}

}
