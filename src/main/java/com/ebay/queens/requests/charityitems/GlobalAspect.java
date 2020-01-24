package com.ebay.queens.requests.charityitems;

public class GlobalAspect {
	private String constraintType;
	private String value;
	public GlobalAspect(String constraintType, String value) {
		this.constraintType = constraintType;
		this.value = value;
	}
	
	// Getters
	public String getConstraintType() {
		return constraintType;
	}
	public String getValue() {
		return value;
	}
	
	// Setters
	public void setConstraintType(String constraintType) {
		this.constraintType = constraintType;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
