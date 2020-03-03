package com.ebay.queens.main.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Users")
public class User {
	@Id
	private int id;
	private String username;
	private String password;
	private UserGroup userGroup;
	
	private static UserGroup randomUserGroup() {
	    int pick = new Random().nextInt(2);
	    return UserGroup.values()[pick];
	}
	
	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userGroup = randomUserGroup();
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
