package com.ebay.queens.responses.charityitemresponse;

public class Category {
	private String id;
	private String name;
	private String leafCategory;
	private String categoryGroup;

	public Category(String id, String name, String leafCategory, String categoryGroup) {
		this.id = id;
		this.name = name;
		this.leafCategory = leafCategory;
		this.categoryGroup = categoryGroup;
	}

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

}
