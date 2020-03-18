package com.ebay.queens.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebay.queens.demo.model.User;
import com.ebay.queens.demo.model.UserGroup;
import com.ebay.queens.demo.resource.UserController;

@RestController
@RequestMapping("/signup")
@Component
public class SignUp {
	private Logger LOGGER;
	public static List<User> users = new ArrayList<User>();
	@Autowired
	private UserController userController;


	SignUp() throws IOException {
		LOGGER = Utilities.LOGGER;
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

	@PostMapping("/RegisterUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User resgiterUser(@QueryParam("username") String username, @QueryParam("password") String password) throws IOException { 
		LOGGER.info("Sign Up Method" + username + " " + password);
		User newUser = null;
		for(User activeUser: userController.getAllUsers()) {
			if(!username.equals(activeUser.getUsername())) {
				LOGGER.info("User Signed Up: " + username );
				newUser = new User("1",username, password);
				users.add(newUser);
				userController.saveUser(newUser);
			}else {
				LOGGER.warning(username + " already exists");
			}	
		}
		return newUser;
	}
}
