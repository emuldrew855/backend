package com.ebay.queens.responses.charityitemresponse;

public class Category {
	private String id;
	private String name;
	private String leafCategory;
	private String categoryGroup;
	private String localizedName;

	// Getters
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLeafCategory() {
		return leafCategory;
	}

	public String getCategoryGroup() {
		return categoryGroup;
	}
	
	public String getLocalizedName() {
		return localizedName;
	}

	// Setters
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLeafCategory(String leafCategory) {
		this.leafCategory = leafCategory;
	}

	public void setCategoryGroup(String categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}

}
