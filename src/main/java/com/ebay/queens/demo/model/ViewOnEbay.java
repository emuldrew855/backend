package com.ebay.queens.demo.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewOnEbay {
	private UserGroup userGroup; 
	private boolean viewedOnEbay;
	
	
	public ViewOnEbay(UserGroup userGroup, boolean viewedOnEbay) {
		this.userGroup = userGroup;
		this.viewedOnEbay = viewedOnEbay;
	}
	
	public ViewOnEbay() {
		// TODO Auto-generated constructor stub
	}

	// Getters
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public boolean isViewedOnEbay() {
		return viewedOnEbay;
	}
	
	// Setters
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public void setViewedOnEbay(boolean viewedOnEbay) {
		this.viewedOnEbay = viewedOnEbay;
	}
	
	
}
