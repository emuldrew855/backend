package com.ebay.queens.responses.searchitemresponse;

public class ShippingOptions {
	private String shippingCostType;
	private ShippingCost shippingCost;

	public ShippingCost getShippingCost() {
		return shippingCost;
	}

	// Getters
	public String getShippingCostType() {
		return shippingCostType;
	}

	public void setShippingCost(ShippingCost shippingCost) {
		this.shippingCost = shippingCost;
	}

	// Setters
	public void setShippingCostType(String shippingCostType) {
		this.shippingCostType = shippingCostType;
	}
}
