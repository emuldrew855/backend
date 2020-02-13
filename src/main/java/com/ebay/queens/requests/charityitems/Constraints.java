package com.ebay.queens.requests.charityitems;

public class Constraints {
	GlobalAspect globalAspect[];

	public Constraints(GlobalAspect[] globalAspect) {
		this.globalAspect = globalAspect;
	}

	public GlobalAspect[] getGlobalAspect() {
		return globalAspect;
	}

	public void setGlobalAspect(GlobalAspect[] globalAspect) {
		this.globalAspect = globalAspect;
	}

}
