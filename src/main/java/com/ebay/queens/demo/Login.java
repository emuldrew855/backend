package com.ebay.queens.demo;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.queens.demo.model.User;
import com.ebay.queens.demo.resource.UserController;
/**
 * Represents a class to handle all login functionality and make connections to mongodb to save & retrieve user details
 */
@Component
@RestController
@RequestMapping("/auth")
public class Login {
	public static User activeUser = new User(); 
	Login() {
		LOGGER = Utilities.LOGGER;
		LOGGER.addHandler(Utilities.fileHandler);
		LOGGER.info("Login");
	}
	
	private Logger LOGGER; 

	@Autowired
	private UserController userController;
	
	/**
	 * This method is to help the front-end set the current active user by returning the given user based off username
	 * 
	 * @param username - user enters their username
	 * @return - returns the given user matching the given username
	 */
	@GET
	@GetMapping("/GetUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@QueryParam("username") String username) {
		LOGGER.info("Get User with: " + username);
		User returnedUser = null;
		for(User user: userController.getAllUsers()) {
			if(user.getUsername().equals(username)) {
				returnedUser = user;
				Login.activeUser = user; 
			}
		}
		LOGGER.info("Active user: " + activeUser.getUsername());
		return returnedUser;
	}
	
	/**
	 * API to log in a user to the system
	 * 
	 * @param username - user enters their username 
	 * @param password - user enters their password to gain entry in to the system 
	 * @return - returns the username which is then used to fetch the active user logging in
	 */
	@GET
	@GetMapping("/LogIn")
	@Produces(MediaType.APPLICATION_JSON)
	public String logIn(@QueryParam("username") String username, @QueryParam("password") String password) {
		LOGGER.info("Log In Method" + username + " " + password);
		String response = "";
		for (User user : userController.getAllUsers()) {
			if (username.equals("admin") && password.equals("admin")) {
				response = "Admin";
			} else {
				if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					response = "Access Granted";
					return username;
				} else {
					response = "NoAccess";
				}
			}
		}
		LOGGER.info("Login Response: " + response);
		return response;
	}
	
	/**
	 * API to sign out the current active user
	 * 
	 * @param username - user enters their username 
	 */
	@PostMapping("/SignOut")
	@Produces(MediaType.APPLICATION_JSON) 
	public void signOut(@QueryParam("username") String username){
		for(User user: userController.getAllUsers()) {
			if(user.getUsername().equals(username)) {
				LOGGER.info("Active User Signed Out!" + user.getUsername());
				Login.activeUser = null; 
			}
		}
	}
	
	
}
