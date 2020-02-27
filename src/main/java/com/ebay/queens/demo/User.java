package com.ebay.queens.demo;

public class User {
	private int id;
	private String username;
	private String password;
	private UserGroup userGroup;
	
	public User(int id, String username, String password, UserGroup userGroup) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userGroup = userGroup;
	}
	
	
	// Getters
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}
	
	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	} 

	
	
	
}
