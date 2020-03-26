package com.ebay.queens.demo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
	private Logger LOGGER; 
	@Autowired
	private UserController userController;

	Login() {
		LOGGER = Utilities.LOGGER;
		LOGGER.addHandler(Utilities.fileHandler);
		LOGGER.info("Login");
	}
	
	/**
	 * API to log in a user to the system
	 * 
	 * @param username - user enters their username 
	 * @param password - user enters their password to gain entry in to the system 
	 * @return - returns the username which is then used to fetch the active user logging in
	 * @throws JAXBException
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
	
	@GET
	@GetMapping("/GetUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@QueryParam("username") String username) {
		LOGGER.info("Get User with: " + username);
		User returnedUser = null;
		for(User user: userController.getAllUsers()) {
			if(user.getUsername().equals(username)) {
				returnedUser = user;
			}
		}
		LOGGER.info("Returned user: " + returnedUser.getUsername());
		return returnedUser;
	}
}
