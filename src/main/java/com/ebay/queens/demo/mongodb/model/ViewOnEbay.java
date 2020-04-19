package com.ebay.queens.demo.mongodb.model;

public class ViewOnEbay {
	public ViewOnEbay() {
		// TODO Auto-generated constructor stub
	} 
	public ViewOnEbay(UserGroup userGroup, boolean viewedOnEbay) {
		this.userGroup = userGroup;
		this.viewedOnEbay = viewedOnEbay;
	}
	
	
	private UserGroup userGroup;
	
	private boolean viewedOnEbay;

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
