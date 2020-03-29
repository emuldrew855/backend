package com.ebay.queens.demo.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Users")
public class User {
	@Id
	private String id;
	private String username;
	private String password;
	private UserGroup userGroup;
	private ArrayList<UserActions> userActions;
	private String ebayAuthToken; 

	private static UserGroup randomUserGroup() {
	    int pick = new Random().nextInt(2);
	    return UserGroup.values()[pick];
	}
	
	public User(String id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userGroup = randomUserGroup();
	}
	
	
	public User() {
		
	}
	
	// Getters
	public String getId() {
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
	
	public ArrayList<UserActions> getUserActions() {
		return userActions;
	}
	
	public String getEbayAuthToken() {
		return ebayAuthToken;
	}
	
	// Setters
	public void setId(String id) {
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
	
	public void setUserActions(ArrayList<UserActions> userActions) {
		this.userActions = userActions;
	}

	public void setEbayAuthToken(String ebayAuthToken) {
		this.ebayAuthToken = ebayAuthToken;
	}
	
	
	
}
