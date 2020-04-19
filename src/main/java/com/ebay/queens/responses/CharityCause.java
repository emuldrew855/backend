package com.ebay.queens.responses;

public class CharityCause {
	private String name;
	private String image;
	
	public CharityCause(String name, String image) {
		this.name = name;
		this.image = image;
	}
	
	
	// Getters
	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}
	
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
