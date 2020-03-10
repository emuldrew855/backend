package com.ebay.queens.demo.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewOnEbay {
	private HashMap<String, Boolean> userViewedItemOnEbay = new HashMap<String, Boolean>();

	public HashMap<String, Boolean> getUserViewedItemOnEbay() {
		return userViewedItemOnEbay;
	}

	public void setUserViewedItemOnEbay(HashMap<String, Boolean> userViewedItemOnEbay) {
		this.userViewedItemOnEbay = userViewedItemOnEbay;
	}
}
