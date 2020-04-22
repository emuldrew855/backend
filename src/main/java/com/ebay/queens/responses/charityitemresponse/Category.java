package com.ebay.queens.responses.charityitemresponse;

public class Category {
	private String id;
	private String name;
	private String leafCategory;
	private String categoryGroup;
	private String localizedName;

	public String getCategoryGroup() {
		return categoryGroup;
	}

	// Getters
	public String getId() {
		return id;
	}

	public String getLeafCategory() {
		return leafCategory;
	}

	public String getLocalizedName() {
		return localizedName;
	}
	
	public String getName() {
		return name;
	}

	public void setCategoryGroup(String categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

	// Setters
	public void setId(String id) {
		this.id = id;
	}

	public void setLeafCategory(String leafCategory) {
		this.leafCategory = leafCategory;
	}

	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}

	public void setName(String name) {
		this.name = name;
	}

}
