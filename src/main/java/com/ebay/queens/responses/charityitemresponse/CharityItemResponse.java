package com.ebay.queens.responses.charityitemresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"answers","layout","errorMessage"})
public class CharityItemResponse {
	public CharityItemResponse() {

	}

	private ItemsHolder items;

	public ItemsHolder getItems() {
		return items;
	}

	public void setItems(ItemsHolder items) {
		this.items = items;
	}


}
