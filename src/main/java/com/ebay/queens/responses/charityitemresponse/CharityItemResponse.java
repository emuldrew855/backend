package com.ebay.queens.responses.charityitemresponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"answers","layout","errorMessage"})
public class CharityItemResponse {
	private ItemsHolder items;

	public CharityItemResponse() {

	}

	public ItemsHolder getItems() {
		return items;
	}

	public void setItems(ItemsHolder items) {
		this.items = items;
	}


}
