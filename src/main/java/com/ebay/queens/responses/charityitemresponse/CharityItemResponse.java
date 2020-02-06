package com.ebay.queens.responses.charityitemresponse;

import java.util.List;

public class CharityItemResponse<T> {
	private List<T> items;
	
	public CharityItemResponse() {
		
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}


}
