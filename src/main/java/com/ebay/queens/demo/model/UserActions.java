package com.ebay.queens.demo.model;

public class UserActions {
	private String webPage; 
	private String userAction;
	private int order;
	boolean ebayButtonClick; 
	
	// Getters
	public int getOrder() {
		return order;
	}
	public String getUserAction() {
		return userAction;
	}
	public String getWebPage() {
		return webPage;
	}
	public boolean isEbayButtonClick() {
		return ebayButtonClick;
	}
	
	public void setEbayButtonClick(boolean ebayButtonClick) {
		this.ebayButtonClick = ebayButtonClick;
	}
	public void setOrder(int order) {
		this.order = order;
	} 
	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}
	// Setters
	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}
}
