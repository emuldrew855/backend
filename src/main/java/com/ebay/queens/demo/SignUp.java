package com.ebay.queens.demo;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.queens.demo.model.User;
import com.ebay.queens.demo.model.UserGroup;

@Component
@RestController
@RequestMapping("/signup")
public class SignUp {
private static final Logger LOGGER = LoggerFactory.getLogger(SignUp.class);
	public static List<User> users = new ArrayList<User>();

	SignUp() {
		LOGGER.info("SignUp");
		User userA = new User("1","userA","userA");
		userA.setUserGroup(UserGroup.A);
		User userB = new User("2","userB","userB");
		userB.setUserGroup(UserGroup.B);
		User admin = new User("3","admin","admin");
		admin.setUserGroup(UserGroup.B);
		users.add(userA);
		users.add(userB);
		users.add(admin);
		LOGGER.info("Users" + users);
	}


	@GET
	@PostMapping("/RegisterUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User resgiterUser(@QueryParam("username") String username, @QueryParam("password") String password) { 
		LOGGER.info("Sign Up Method" + username + " " + password);
		User newUser = null;
		if(username.equals("username") && password.equals(password)) {
			LOGGER.info("User Signed Up");
			newUser = new User("1",username, password);
			users.add(newUser);
		}else {
			LOGGER.info("User Not Signed Up");
		}	
		return newUser;
	}
}
