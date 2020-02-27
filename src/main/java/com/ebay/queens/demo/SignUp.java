package com.ebay.queens.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Path("/signup")
public class SignUp {
private static final Logger LOGGER = LoggerFactory.getLogger(SignUp.class);
	
	SignUp() {
		LOGGER.info("SignUp");
	}


	@GET
	@Path("/RegisterUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User resgiterUser(@QueryParam("username") String username, @QueryParam("password") String password) { 
		LOGGER.info("Sign Up Method" + username + " " + password);
		User newUser = null;
		if(username.equals("username") && password.equals(password)) {
			LOGGER.info("User Signed Up");
			newUser = new User(1,username, password, UserGroup.A);
		}else {
			LOGGER.info("User Not Signed Up");
		}
		
		return newUser;
	}
}
