package com.ebay.queens.responses.charityitemresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"categoryInfo","matchCount","dedupedMatchCount","searchRefinement","fitmentResponse","paginationResponse",
	"eligibleFeature","extension"})
public class ItemsHolder {
	private Items items;

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

}
