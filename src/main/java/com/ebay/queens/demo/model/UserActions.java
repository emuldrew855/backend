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
	public String getWebPage() {
		return webPage;
	}
	public String getUserAction() {
		return userAction;
	}
	public boolean isEbayButtonClick() {
		return ebayButtonClick;
	}
	
	// Setters
	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}
	public void setUserAction(String userAction) {
		this.userAction = userAction;
	} 
	public void setOrder(int order) {
		this.order = order;
	}
	public void setEbayButtonClick(boolean ebayButtonClick) {
		this.ebayButtonClick = ebayButtonClick;
	}
}
