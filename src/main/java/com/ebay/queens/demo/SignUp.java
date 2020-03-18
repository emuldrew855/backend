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
	@Autowired
	private UserController userController;
	int userIndex = 3;

	SignUp() throws IOException {
		LOGGER = Utilities.LOGGER;
		LOGGER.info("SignUp");
	}

	@PostMapping("/RegisterUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String resgiterUser(@QueryParam("username") String username, @QueryParam("password") String password) throws IOException { 
		LOGGER.info("Sign Up: Username: " + username + " Password: " + password);
		User newUser = null;
		boolean createNewUser = true;
		for(User activeUser: userController.getAllUsers()) {
			if(username.equals(activeUser.getUsername())) {
				createNewUser = false;
				LOGGER.warning(username + " already exists");
			}	
		}
		if(createNewUser) {
			LOGGER.info("User Signed Up: " + username );
			this.userIndex = this.userIndex + 1;
			newUser = new User(Integer.toString(userIndex),username, password);
			userController.saveUser(newUser);
			return "UserSignedUp";
		}else {
			return "UserExists";
		}
	}
}
