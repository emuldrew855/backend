package com.ebay.queens.requests.charityitems;

public class Constraints {
	public Constraints(GlobalAspect[] globalAspect) {
		this.globalAspect = globalAspect;
	}

	GlobalAspect globalAspect[];

	public GlobalAspect[] getGlobalAspect() {
		return globalAspect;
	}

	public void setGlobalAspect(GlobalAspect[] globalAspect) {
		this.globalAspect = globalAspect;
	}

}
